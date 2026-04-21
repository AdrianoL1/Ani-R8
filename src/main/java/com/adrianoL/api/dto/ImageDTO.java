package com.adrianoL.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ImageDTO {

    String filename;
    String contentType;
    long fileSize;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ImageDTO imageDTO)) return false;
        return getFileSize() == imageDTO.getFileSize() && Objects.equals(getFilename(), imageDTO.getFilename()) && Objects.equals(getContentType(), imageDTO.getContentType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilename(), getContentType(), getFileSize());
    }
}
