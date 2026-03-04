package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.UserAnime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAnimeRepository extends JpaRepository<UserAnime, Long>, JpaSpecificationExecutor<UserAnime> {

    @Query("FROM UserAnime ua WHERE ua.anime.id = :id AND ua.user.id = :userId")
    Optional<UserAnime> getAnimeFromUsersList(Long id, Long userId);

}
