package com.adrianoL.domain.exception;

public class AnimeNotFoundException extends ResourceNotFoundException {

    public AnimeNotFoundException(Long id) {
        super("Anime with ID: %d not found".formatted(id));
    }

    public AnimeNotFoundException(Long id, Throwable cause) {
        super("Anime with ID: %d not found".formatted(id), cause);
    }
}
