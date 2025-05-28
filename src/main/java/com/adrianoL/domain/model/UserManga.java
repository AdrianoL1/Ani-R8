package com.adrianoL.domain.model;

import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.model.enums.UserMangaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "manga_id"})})
public class UserManga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserMangaStatus status;

    @Column(name = "personal_rating", nullable = false)
    private int personalRating;

    @Column(name = "chapters_read", nullable = false)
    private int chaptersRead;

    @Column(name = "volumes_read", nullable = false)
    private int volumesRead;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "manga_id", nullable = false)
    private Manga manga;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserManga userManga)) return false;
        return getPersonalRating() == userManga.getPersonalRating() && getChaptersRead() == userManga.getChaptersRead() && getVolumesRead() == userManga.getVolumesRead() && Objects.equals(getId(), userManga.getId()) && getStatus() == userManga.getStatus() && Objects.equals(getUser(), userManga.getUser()) && Objects.equals(getManga(), userManga.getManga());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getPersonalRating(), getChaptersRead(), getVolumesRead(), getUser(), getManga());
    }
}
