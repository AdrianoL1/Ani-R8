package com.adrianoL.domain.exception;

public class ImageNotFoundException extends ResourceNotFoundException {
    public ImageNotFoundException(Long id) {
        super("Image with ID: %d not found!".formatted(id));
    }

    public ImageNotFoundException(Long id, Throwable cause) {
        super("Image with ID: %d not found!".formatted(id), cause);
    }
}
