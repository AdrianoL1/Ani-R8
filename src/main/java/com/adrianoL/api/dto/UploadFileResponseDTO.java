package com.adrianoL.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponseDTO {

    String fileName;
    String fileType;
    long fileSize;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UploadFileResponseDTO that)) return false;
        return getFileSize() == that.getFileSize() && Objects.equals(getFileName(), that.getFileName()) && Objects.equals(getFileType(), that.getFileType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getFileType(), getFileSize());
    }
}
