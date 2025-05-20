package com.adrianoL.api.dto;

import com.adrianoL.domain.model.Anime;
import com.adrianoL.domain.model.enums.UserAnimeStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserAnimeDTO {

    private UserAnimeStatus status;
    private int personalRating;
    private int episodesWatched;
    private Anime anime;

    public UserAnimeDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAnimeDTO that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getEpisodesWatched() == that.getEpisodesWatched() && getStatus() == that.getStatus() && Objects.equals(getAnime(), that.getAnime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getPersonalRating(), getEpisodesWatched(), getAnime());
    }
}
