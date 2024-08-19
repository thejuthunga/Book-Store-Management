package com.bookservice.controller;

import com.bookservice.dataModels.dto.BookDto;
import com.bookservice.dataModels.entity.Book;
import com.bookservice.service.BookService;
import com.client.response.ResponseStructure;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book/")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @CircuitBreaker(name = "authorCircuitBreaker",fallbackMethod = "authorFallBack")
    public ResponseEntity<ResponseStructure> createBook(@RequestBody BookDto bookDto){
        Book book = bookService.createBook(bookDto);
        ResponseStructure response = ResponseStructure.builder()
                .data(book)
                .message("Successfully Saved")
                .status(HttpStatus.CREATED)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure> authorFallBack(BookDto bookDto,Exception e){
        ResponseStructure response = ResponseStructure.builder()
                .data("Author Service is Unavailable")
                .message(e.getMessage())
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response,HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping
    public ResponseEntity<Book> getBookById(@RequestParam String bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseStructure> updateBook(@RequestBody Book book){
        ResponseStructure response = ResponseStructure.builder()
                .data(bookService.updateBook(book))
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam String bookId){
        return new ResponseEntity<>(bookService.deleteBook(bookId),HttpStatus.OK);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<ResponseStructure> getAllbookByAuthor(@PathVariable String authorId){
        ResponseStructure response = ResponseStructure.builder()
                .data(bookService.getAllBookByAuthor(authorId))
                .message(HttpStatus.OK.getReasonPhrase())
                .status(HttpStatus.OK)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
//CopyOnWriteArrayList,CopyOnWriteArraySet,ConcurrentHashMap