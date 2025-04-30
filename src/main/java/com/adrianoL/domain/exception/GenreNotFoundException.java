package com.adrianoL.domain.exception;

public class GenreNotFoundException extends ResourceNotFoundException {
    public GenreNotFoundException(Long id) {
        super("Genre with ID: %d not found".formatted(id));
    }

    public GenreNotFoundException(Long id, Throwable cause) {
        super("Genre with ID: %d not found".formatted(id), cause);
    }
}
