package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MangaRepository extends JpaRepository<Manga, Long>, JpaSpecificationExecutor<Manga> {
}
