package com.client.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStructure {
    private String message;
    private HttpStatus status;
    private Object data;
    private String timeStamp;
}
