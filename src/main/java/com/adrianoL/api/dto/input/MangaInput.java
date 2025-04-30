package com.adrianoL.api.dto.input;

import com.adrianoL.domain.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class MangaInput {

    private String title;
    private Status status;
    private String description;
    private String chapters;
    private String volumes;
    private String publishedFrom;
    private String publishedTo;
    private String author;
    private Set<UpdateResourceGenreInput> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MangaInput that)) return false;
        return Objects.equals(getTitle(), that.getTitle()) && getStatus() == that.getStatus() && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getChapters(), that.getChapters()) && Objects.equals(getVolumes(), that.getVolumes()) && Objects.equals(getPublishedFrom(), that.getPublishedFrom()) && Objects.equals(getPublishedTo(), that.getPublishedTo()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getGenres(), that.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getStatus(), getDescription(), getChapters(), getVolumes(), getPublishedFrom(), getPublishedTo(), getAuthor(), getGenres());
    }
}
