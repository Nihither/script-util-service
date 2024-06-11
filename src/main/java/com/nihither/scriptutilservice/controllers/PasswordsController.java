package com.nihither.scriptutilservice.controllers;

import com.nihither.scriptutilservice.models.Password;
import com.nihither.scriptutilservice.repositories.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PasswordsController {

    @Autowired
    private PasswordRepository passwordRepository;

    @Description("Password ID search")
    @GetMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getPasswordById(@PathVariable("id") Long id) {
        Optional<Password> password = passwordRepository.findById(id);

        if (password.isPresent()) {
            return new ResponseEntity<>(password.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Password with id=" + id + " not found", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/passwords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllPasswordsByContaining(@RequestParam() String searchString) {
        try {
            List<Password> passwords = new ArrayList<Password>();
            passwords.addAll(passwordRepository.findAllByContaining(searchString));
            if (passwords.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(passwords, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Description("Create password")
    @PostMapping("/passwords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createPassword(Password password) {
        password.setCreation_time(new Date());
        password.setLast_modification_time(new Date());
        password.setRmv(false);
        try {
            passwordRepository.save(password);
            return new ResponseEntity<>("Password successfully saved into DB", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(String.valueOf(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Description("Update password")
    @PutMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePass(@PathVariable("id") Long id, @RequestBody Password password) {
        Optional<Password> optPass = passwordRepository.findById(id);
        return null;
    }

    @Description("Delete password")
    @DeleteMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> setPassRmv(@PathVariable("id") Long id) {
        try {
            int result = passwordRepository.setRmvById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find password with id=" + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Password with id=" + id + " successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
