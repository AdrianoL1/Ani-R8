package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Long> {
}
