package com.author.controller;

import com.author.datamodel.dto.AuthorDto;
import com.author.datamodel.dto.ResponseStructure;
import com.author.datamodel.entity.Author;
import com.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<ResponseStructure> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorService.createAuthor(authorDto);
        ResponseStructure response = ResponseStructure.builder()
                .data(author)
                .message("Successfully Saved")
                .status(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{authorid}")
    public Author getAuthorById(@PathVariable String authorid) {
        Author author = authorService.getAuthorById(authorid);
//        ResponseStructure response = ResponseStructure.builder()
//                .data(author)
//                .message("Found")
//                .status(HttpStatus.FOUND)
//                .timeStamp(LocalDateTime.now().toString())
//                .build();
        return author;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure> updateAuthor(@RequestBody Author author) {
        author = authorService.updateAuthor(author);
        ResponseStructure response = ResponseStructure.builder()
                .data(author)
                .message("Successfully updated")
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseStructure> deleteAuthor(@RequestParam String authorId) {
        ResponseStructure response = ResponseStructure.builder()
                .data(authorService.deleteAuthor(authorId))
                .message(HttpStatus.OK.toString())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getallauthor")
    public ResponseEntity<ResponseStructure> getAllAuthor() {
        ResponseStructure response = ResponseStructure.builder()
                .data(authorService.getAllAuthor())
                .message(HttpStatus.OK.toString())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
