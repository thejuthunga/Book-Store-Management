package com.author.service.impl;

import com.author.datamodel.dto.AuthorDto;
import com.author.datamodel.entity.Author;
import com.author.service.AuthorService;
import com.author.repository.AuthorRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoty authorRepositoty;

    private final MongoTemplate mongoTemplate;

    @Override
    public Author createAuthor(AuthorDto authorDto) {
        Author author = Author.builder()
                .id(UUID.randomUUID().toString())
                .biography(authorDto.getBiography())
                .name(authorDto.getName())
                .build();
        return authorRepositoty.save(author);
    }

    @Override
    public Author getAuthorById(String authorId) {
        return authorRepositoty.findById(authorId).orElseThrow(() -> new NullPointerException());
    }

    @Override
    public Author updateAuthor(Author author) {
        Update update=new Update();
        update.set("name",author.getName());
        update.set("biography",author.getBiography());

        // Adding each book name to the list
        for (String bookName : author.getBookname()) {
            update.addToSet("bookname", bookName);
        }

        Criteria criteria = Criteria.where("id").is(author.getId());
        return mongoTemplate.findAndModify(new Query(criteria), update, Author.class);
    }

    @Override
    public String deleteAuthor(String authorId) {
        Author author = getAuthorById(authorId);
        authorRepositoty.delete(author);
        return "Deleted";
    }

    @Override
    public List<Author> getAllAuthor() {
       return authorRepositoty.findAll();
    }
}