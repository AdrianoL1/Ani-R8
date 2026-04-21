package com.adrianoL.api.dto.input;

import com.adrianoL.config.validation.EnumPattern;
import com.adrianoL.domain.model.enums.UserMangaStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserMangaInput {

    @EnumPattern(enumClass = UserMangaStatus.class)
    private String status;

    @NotNull
    private Long mangaId;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private int personalRating;

    @NotNull
    @PositiveOrZero
    private int chaptersRead;

    @NotNull
    @PositiveOrZero
    private int volumesRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMangaInput that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getChaptersRead() == that.getChaptersRead() && getVolumesRead() == that.getVolumesRead() && getStatus() == that.getStatus() && Objects.equals(getMangaId(), that.getMangaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMangaId(), getPersonalRating(), getChaptersRead(), getVolumesRead());
    }
}
