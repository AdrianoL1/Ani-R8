package com.adrianoL.domain.model;

import com.adrianoL.domain.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false ,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String chapters;

    @Column(nullable = false)
    private String volumes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String publishedFrom;

    @Column(nullable = false)
    private String publishedTo;

    @Column(nullable = false)
    private String author;

    @ManyToMany()
    @JoinTable(
            name = "manga_genre",
            joinColumns = @JoinColumn(name = "manga_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manga manga)) return false;
        return Objects.equals(getId(), manga.getId()) && Objects.equals(getTitle(), manga.getTitle()) && Objects.equals(getDescription(), manga.getDescription()) && Objects.equals(getChapters(), manga.getChapters()) && Objects.equals(getVolumes(), manga.getVolumes()) && getStatus() == manga.getStatus() && Objects.equals(getPublishedFrom(), manga.getPublishedFrom()) && Objects.equals(getPublishedTo(), manga.getPublishedTo()) && Objects.equals(getAuthor(), manga.getAuthor()) && Objects.equals(getGenres(), manga.getGenres());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getChapters(), getVolumes(), getStatus(), getPublishedFrom(), getPublishedTo(), getAuthor(), getGenres());
    }
}
