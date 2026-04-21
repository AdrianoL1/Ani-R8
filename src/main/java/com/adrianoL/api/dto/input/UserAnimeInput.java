package com.adrianoL.api.dto.input;

import com.adrianoL.config.validation.EnumPattern;
import com.adrianoL.domain.model.enums.UserAnimeStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserAnimeInput {

    @EnumPattern(enumClass = UserAnimeStatus.class)
    private String status;

    @NotNull
    private Long animeId;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private int personalRating;

    @NotNull
    @PositiveOrZero
    private int episodesWatched;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAnimeInput that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getEpisodesWatched() == that.getEpisodesWatched() && getStatus() == that.getStatus() && Objects.equals(getAnimeId(), that.getAnimeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getPersonalRating(), getEpisodesWatched(), getAnimeId());
    }
}
