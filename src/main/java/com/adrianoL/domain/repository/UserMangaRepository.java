package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.UserManga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMangaRepository extends JpaRepository<UserManga, Long>, JpaSpecificationExecutor<UserManga> {

    @Query("FROM UserManga um WHERE um.manga.id = :id AND um.user.id = :userId")
    Optional<UserManga> getMangaFromUsersList(Long id, Long userId);
}
