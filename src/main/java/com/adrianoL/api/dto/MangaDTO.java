package com.adrianoL.api.dto;

import com.adrianoL.domain.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class MangaDTO implements Serializable {

    public MangaDTO(){}

    private Long id;
    private String title;
    private String description;
    private String chapters;
    private String volumes;
    private Status status;
    private String publishedFrom;
    private String publishedTo;
    private String author;
    private Set<GenreDTO> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MangaDTO mangaDTO)) return false;
        return Objects.equals(getId(), mangaDTO.getId()) && Objects.equals(getTitle(), mangaDTO.getTitle()) && Objects.equals(getDescription(), mangaDTO.getDescription()) && Objects.equals(getChapters(), mangaDTO.getChapters()) && Objects.equals(getVolumes(), mangaDTO.getVolumes()) && getStatus() == mangaDTO.getStatus() && Objects.equals(getPublishedFrom(), mangaDTO.getPublishedFrom()) && Objects.equals(getPublishedTo(), mangaDTO.getPublishedTo()) && Objects.equals(getAuthor(), mangaDTO.getAuthor()) && Objects.equals(getGenres(), mangaDTO.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getChapters(), getVolumes(), getStatus(), getPublishedFrom(), getPublishedTo(), getAuthor(), getGenres());
    }
}
