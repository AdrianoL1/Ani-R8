package com.adrianoL.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long fileSize;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Image image)) return false;
        return Objects.equals(getId(), image.getId()) && Objects.equals(getFilename(), image.getFilename()) && Objects.equals(getContentType(), image.getContentType()) && Objects.equals(getFileSize(), image.getFileSize());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFilename(), getContentType(), getFileSize());
    }
}
