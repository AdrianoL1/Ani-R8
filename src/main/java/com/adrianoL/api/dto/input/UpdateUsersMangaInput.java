package com.adrianoL.api.dto.input;

import com.adrianoL.domain.model.enums.UserMangaStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UpdateUsersMangaInput {

    private UserMangaStatus status;
    private int personalRating;
    private int volumesRead;
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
