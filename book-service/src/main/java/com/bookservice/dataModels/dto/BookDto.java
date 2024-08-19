package com.bookservice.dataModels.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Data
@Builder
public class BookDto {
    private String title;
    private String genre;
    private String authorId;
}
