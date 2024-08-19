package com.bookservice.dataModels.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "book_store")
@Builder
public class Book {
    @Id
    private String book_id;
    private String title;
    private String genre;
    private String authorId;
}
