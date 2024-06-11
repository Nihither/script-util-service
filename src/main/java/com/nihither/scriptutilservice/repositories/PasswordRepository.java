package com.nihither.scriptutilservice.repositories;

import com.nihither.scriptutilservice.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("SELECT p FROM pass p WHERE title ilike %?1")
    List<Password> findAllByContaining(String search);

    @Query("UPDATE pass set rmv = true WHERE id = ?1")
    int setRmvById(Long id);
}
