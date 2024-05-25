package com.adrianoL.api.dto.input;

import com.adrianoL.domain.model.enums.UserMangaStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserMangaInput {

    private UserMangaStatus status;
    private Long mangaId;
    private int personalRating;
    private int chaptersRead;
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
