package com.adrianoL.domain.repository;

import com.adrianoL.domain.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("from Role r where r.name = :name")
    Optional<Role> findRoleByName(Role.Value name);
}
