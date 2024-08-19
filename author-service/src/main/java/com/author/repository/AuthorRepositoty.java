package com.author.repository;


import com.author.datamodel.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepositoty extends MongoRepository<Author,String> {
}
