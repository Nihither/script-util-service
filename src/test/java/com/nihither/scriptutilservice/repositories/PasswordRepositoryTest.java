package com.nihither.scriptutilservice.repositories;

import com.nihither.scriptutilservice.models.dao.Password;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class PasswordRepositoryTest {

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void create() {
        Password newPass = new Password("test_group", "test_title", "test_username", "qwerty123456", "some_test_url",
                "test_notes", new Date(), new Date(), new Date(), false);
        Password createdPass = passwordRepository.saveAndFlush(newPass);
        assertThat(entityManager.find(Password.class, createdPass.getId())).isEqualTo(newPass);
    }
}
