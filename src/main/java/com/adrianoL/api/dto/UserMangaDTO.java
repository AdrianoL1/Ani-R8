package com.adrianoL.api.dto;

import com.adrianoL.domain.model.enums.UserMangaStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserMangaDTO {

    private UserMangaStatus status;
    private int personalRating;
    private int chaptersRead;
    private int volumesRead;
    private MangaDTO manga;

    public UserMangaDTO() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMangaDTO that)) return false;
        return getPersonalRating() == that.getPersonalRating() && getChaptersRead() == that.getChaptersRead() && getVolumesRead() == that.getVolumesRead() && getStatus() == that.getStatus() && Objects.equals(getManga(), that.getManga());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getPersonalRating(), getChaptersRead(), getVolumesRead(), getManga());
    }
}
