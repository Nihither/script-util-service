package com.nihither.scriptutilservice.services.impl;

import com.nihither.scriptutilservice.models.dao.Password;
import com.nihither.scriptutilservice.repositories.PasswordRepository;
import com.nihither.scriptutilservice.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class PasswordServiceImpl implements PasswordService {

    @Autowired
    PasswordRepository passwordRepository;

    @Override
    public Password getById(Long id) {
        return passwordRepository.findById(id).get();
    }

    @Override
    public List<Password> getAllByContaining(String searchString) {
        return passwordRepository.findAllByContaining(searchString);
    }

    @Override
    public Password addPassword(Password password) {
        password.setCreation_time(new Date());
        password.setLast_modification_time(new Date());
        password.setRmv(false);
        return passwordRepository.saveAndFlush(password);
    }

    @Override
    public Password update(Long id, Password password) {
        Password oldPass = getById(id);
        oldPass.setGroup(password.getGroup());
        oldPass.setTitle(password.getTitle());
        oldPass.setUsername(password.getUsername());
        oldPass.setPassword(password.getPassword());
        oldPass.setUrl(password.getUrl());
        oldPass.setNotes(password.getNotes());
        password.setLast_modification_time(new Date());
        password.setRmv(false);
        return passwordRepository.saveAndFlush(oldPass);
    }

    @Override
    public int setRmv(Long id) {
        return passwordRepository.setRmvById(id);
    }
}
