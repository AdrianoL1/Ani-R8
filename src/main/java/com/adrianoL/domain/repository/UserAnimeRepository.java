package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.UserAnime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnimeRepository extends JpaRepository<UserAnime, Long> {

    @Query("FROM UserAnime ua WHERE ua.user.username = :username")
    List<UserAnime> findAllUserEntriesByUsername(String username);

    @Modifying
    @Query("DELETE FROM UserAnime ua WHERE ua.anime.id = :id AND ua.user.id = :userId")
    void deleteAnimeFromUserList(Long id, Long userId);
}
