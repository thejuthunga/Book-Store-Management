package com.bookservice.exception;

import com.client.response.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseStructure getNullException(NullPointerException e){
        ResponseStructure response = ResponseStructure.builder()
                .data(e.getMessage())
                .message("Id Not Found")
                .status(HttpStatus.NOT_FOUND)
                .timeStamp(LocalDateTime.now().toString())
                .build();
        return response;
    }

}
