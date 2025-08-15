package com.example.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST) // Sets the default HTTP status for this exception
public class InvalidRequestDataException extends RuntimeException {

    private final List<String> errors;

    public InvalidRequestDataException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }
}