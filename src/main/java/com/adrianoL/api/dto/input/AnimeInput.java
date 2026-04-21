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
public class AnimeInput {

    @NotBlank
    private String title;

    @EnumPattern(enumClass = Status.class)
    private String status;

    @NotBlank
    private String description;

    @NotBlank
    private String totalEpisodes;

    @NotBlank
    private String airedFrom;

    @NotBlank
    private String airedTo;

    @NotBlank
    private String author;

    @NotNull
    @Size(min = 1)
    private Set<UpdateResourceGenreInput> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimeInput that)) return false;
        return Objects.equals(getTitle(), that.getTitle()) && getStatus() == that.getStatus() && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getTotalEpisodes(), that.getTotalEpisodes()) && Objects.equals(getAiredFrom(), that.getAiredFrom()) && Objects.equals(getAiredTo(), that.getAiredTo()) && Objects.equals(getAuthor(), that.getAuthor()) && Objects.equals(getGenres(), that.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getStatus(), getDescription(), getTotalEpisodes(), getAiredFrom(), getAiredTo(), getAuthor(), getGenres());
    }
}
