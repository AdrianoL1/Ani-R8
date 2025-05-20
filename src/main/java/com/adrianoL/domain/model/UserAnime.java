package com.adrianoL.domain.model;

import com.adrianoL.api.dto.UserDTO;
import com.adrianoL.domain.model.auth.User;
import com.adrianoL.domain.model.enums.UserAnimeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "anime_id"})})
public class UserAnime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserAnimeStatus status;

    @Column(name = "personal_rating", nullable = false)
    private int personalRating;

    @Column(name = "episodes_watched", nullable = false)
    private int episodesWatched;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    private Anime anime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAnime userAnime)) return false;
        return getPersonalRating() == userAnime.getPersonalRating() && getEpisodesWatched() == userAnime.getEpisodesWatched() && Objects.equals(getId(), userAnime.getId()) && getStatus() == userAnime.getStatus() && Objects.equals(getUser(), userAnime.getUser()) && Objects.equals(getAnime(), userAnime.getAnime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getPersonalRating(), getEpisodesWatched(), getUser(), getAnime());
    }
}
