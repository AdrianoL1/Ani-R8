package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
