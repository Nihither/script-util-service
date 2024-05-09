package com.nihither.scriptutilservice.repositories;

import com.nihither.scriptutilservice.models.ERole;
import com.nihither.scriptutilservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
