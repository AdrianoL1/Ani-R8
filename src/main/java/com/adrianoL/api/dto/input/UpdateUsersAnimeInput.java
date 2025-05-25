package com.adrianoL.api.dto.input;

import com.adrianoL.domain.model.enums.UserAnimeStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UpdateUsersAnimeInput {

    private UserAnimeStatus status;
    private int personalRating;
    private int episodesWatched;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUsersAnimeInput that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getEpisodesWatched() == that.getEpisodesWatched() && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getPersonalRating(), getEpisodesWatched());
    }
}
