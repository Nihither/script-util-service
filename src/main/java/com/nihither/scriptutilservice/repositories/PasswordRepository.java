package com.nihither.scriptutilservice.repositories;

import com.nihither.scriptutilservice.models.dao.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query(value = "SELECT * FROM passwords p WHERE lower(p.title) like lower(?1)",
            nativeQuery = true)
    List<Password> findAllByContaining(String search);

    @Modifying
    @Query(value = "UPDATE passwords p set p.rmv = true WHERE p.id = ?1",
            nativeQuery = true)
    int setRmvById(Long id);
}
