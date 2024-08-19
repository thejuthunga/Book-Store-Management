package com.author.service;

import com.author.datamodel.dto.AuthorDto;
import com.author.datamodel.entity.Author;

import java.util.List;

public interface AuthorService {

    public Author createAuthor(AuthorDto authorDto);

    public Author getAuthorById(String authorId);

    public Author updateAuthor(Author author);

    public String deleteAuthor(String authorId);

    public List<Author> getAllAuthor();
}
