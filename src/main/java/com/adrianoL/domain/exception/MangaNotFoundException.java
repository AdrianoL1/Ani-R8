package com.adrianoL.domain.exception;

public class MangaNotFoundException extends RuntimeException {
    public MangaNotFoundException(String message) {
        super(message);
    }
}
