package com.adrianoL.domain.exception;

public class AnimeNotFoundException extends RuntimeException {
    public AnimeNotFoundException(String message) {
        super(message);
    }
}
