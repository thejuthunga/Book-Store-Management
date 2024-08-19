package com.bookservice.service.impl;

import com.author.datamodel.entity.Author;
import com.bookservice.dataModels.dto.BookDto;
import com.bookservice.dataModels.entity.Book;
import com.bookservice.repository.BookRepository;
import com.bookservice.service.BookService;
import com.client.author.AuthorClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorClient authorClient;

    private final MongoTemplate mongoTemplate;

    @Override
    public Book createBook(BookDto bookdto) {

        Author author = authorClient.getAuthorById(bookdto.getAuthorId());
        if (ObjectUtils.isEmpty(author)) {
            throw new NullPointerException("Author not found");
        }
        Book book = Book.builder()
                .book_id(UUID.randomUUID().toString())
                .genre(bookdto.getGenre())
                .title(bookdto.getTitle())
                .authorId(author.getId())
                .build();

        List<String> books = author.getBookname();
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book.getTitle());
        author.setBookname(books);
        book = bookRepository.save(book);
        authorClient.updateAuthor(author);

        return book;
    }

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).
                orElseThrow(() -> new NullPointerException("Book not Found"));
    }

    @Override
    public Book updateBook(Book book) {
        Update update = new Update();
        if (!StringUtils.isEmpty(book.getTitle())) {
            update.set("title", book.getTitle());
        }
        if (!StringUtils.isEmpty(book.getGenre())) {
            update.set("genre", book.getGenre());
        }
        if (!StringUtils.isEmpty(book.getAuthorId())) {
            update.set("authorId", book.getAuthorId());
        }
        Criteria criteria = Criteria.where("book_id").is(book.getBook_id());
        return mongoTemplate.findAndModify(new Query(criteria), update, Book.class);
    }

    @Override
    public String deleteBook(String bookId) {
        bookRepository.delete(getBookById(bookId));
        return "Successfully deleted";
    }

    @Override
    public List<Book> getAllBookByAuthor(String authorId) {
        Author author = authorClient.getAuthorById(authorId);
        if (ObjectUtils.isEmpty(author)) {
            throw new NullPointerException("Author not found");
        }
        return bookRepository.findByAuthorId(author.getId());
    }
}
