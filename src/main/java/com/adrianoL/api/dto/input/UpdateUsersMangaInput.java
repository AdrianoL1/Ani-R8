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
public class UpdateUsersMangaInput {

    @EnumPattern(enumClass = UserMangaStatus.class)
    private String status;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    private int personalRating;

    @NotNull
    @PositiveOrZero
    private int volumesRead;

    @NotNull
    @PositiveOrZero
    private int chaptersRead;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUsersMangaInput that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getVolumesRead() == that.getVolumesRead() && getChaptersRead() == that.getChaptersRead() && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getPersonalRating(), getVolumesRead(), getChaptersRead());
    }
}
