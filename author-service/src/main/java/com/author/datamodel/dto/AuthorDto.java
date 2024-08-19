package com.author.datamodel.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private String name;
    private String biography;
}
