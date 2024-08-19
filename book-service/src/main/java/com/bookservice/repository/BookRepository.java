package com.bookservice.repository;

import com.bookservice.dataModels.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> {

    public List<Book> findByAuthorId(String authorId);
}
