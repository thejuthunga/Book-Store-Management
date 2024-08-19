package com.bookservice.service;

import com.bookservice.dataModels.dto.BookDto;
import com.bookservice.dataModels.entity.Book;

import java.util.List;

public interface BookService {

    public Book createBook(BookDto book);

    public Book getBookById(String bookId);

    public Book updateBook(Book book);

    public String deleteBook(String bookId);

    public List<Book> getAllBookByAuthor(String authorId);
}
