package com.adrianoL.api.dto;

import com.adrianoL.domain.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class AnimeDTO implements Serializable {

    private Long id;
    private String title;
    private Status status;
    private String description;
    private String totalEpisodes;
    private String airedFrom;
    private String airedTo;
    private String author;
    private Set<GenreDTO> genres;

    public AnimeDTO(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimeDTO animeDTO)) return false;
        return Objects.equals(getId(), animeDTO.getId()) && Objects.equals(getTitle(), animeDTO.getTitle()) && getStatus() == animeDTO.getStatus() && Objects.equals(getDescription(), animeDTO.getDescription()) && Objects.equals(getTotalEpisodes(), animeDTO.getTotalEpisodes()) && Objects.equals(getAiredFrom(), animeDTO.getAiredFrom()) && Objects.equals(getAiredTo(), animeDTO.getAiredTo()) && Objects.equals(getAuthor(), animeDTO.getAuthor()) && Objects.equals(getGenres(), animeDTO.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getStatus(), getDescription(), getTotalEpisodes(), getAiredFrom(), getAiredTo(), getAuthor(), getGenres());
    }
}
