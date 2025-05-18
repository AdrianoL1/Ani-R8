package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u LEFT JOIN FETCH u.roles where u.username = :username")
    Optional<User> findUserByUsername(String username);


    @Query("from User u LEFT JOIN FETCH u.roles where u.email = :email")
    Optional<User> findUserByEmail(String email);
}
