package com.adrianoL.domain.model;

import com.adrianoL.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false ,columnDefinition = "TEXT")
    private String description;

    @Column(name = "total_episodes", nullable = false)
    private String totalEpisodes;

    @Column(nullable = false)
    private String airedFrom;

    @Column(nullable = false)
    private String airedTo;

    @Column(nullable = false)
    private String author;

    @ManyToMany()
    @JoinTable(
            name = "anime_genre",
            joinColumns = @JoinColumn(name = "anime_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anime anime)) return false;
        return Objects.equals(getId(), anime.getId()) && Objects.equals(getTitle(), anime.getTitle()) && getStatus() == anime.getStatus() && Objects.equals(getDescription(), anime.getDescription()) && Objects.equals(getTotalEpisodes(), anime.getTotalEpisodes()) && Objects.equals(getAiredFrom(), anime.getAiredFrom()) && Objects.equals(getAiredTo(), anime.getAiredTo()) && Objects.equals(getAuthor(), anime.getAuthor()) && Objects.equals(getGenres(), anime.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getStatus(), getDescription(), getTotalEpisodes(), getAiredFrom(), getAiredTo(), getAuthor(), getGenres());
    }
}
