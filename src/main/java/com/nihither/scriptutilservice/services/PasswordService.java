package com.nihither.scriptutilservice.services;

import com.nihither.scriptutilservice.models.dao.Password;

import java.util.List;

public interface PasswordService {

    Password getById(Long id);
    List<Password> getAllByContaining(String searchString);
    Password addPassword(Password password);
    Password update(Long id, Password password);
    int setRmv(Long id);
}
