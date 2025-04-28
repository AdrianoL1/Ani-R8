package com.adrianoL.domain.exception;

public class MangaNotFoundException extends ResourceNotFoundException {
    public MangaNotFoundException(Long id) {
        super("Manga with ID: %d not found".formatted(id));
    }

    public MangaNotFoundException(Long id, Throwable cause) {
        super("Manga with ID: %d not found".formatted(id), cause);
    }
}
