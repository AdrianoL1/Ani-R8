package com.adrianoL.api.dto.input;

import com.adrianoL.config.validation.EnumPattern;
import com.adrianoL.domain.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class MangaInput {

    @NotBlank
    private String title;

    @EnumPattern(enumClass = Status.class)
    private String status;

    @NotBlank
    private String description;

    @NotBlank
    private String chapters;

    @NotBlank
    private String volumes;

    @NotBlank
    private String publishedFrom;

    @NotBlank
    private String publishedTo;

    @NotBlank
    private String author;

    @NotNull
    @Size(min = 1, message = "Manga must have at least 1 genre")
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
