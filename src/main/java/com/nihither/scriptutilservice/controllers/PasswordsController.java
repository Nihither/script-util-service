package com.nihither.scriptutilservice.controllers;

import com.nihither.scriptutilservice.models.dao.Password;
import com.nihither.scriptutilservice.models.payloads.MessageResponse;
import com.nihither.scriptutilservice.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PasswordsController {

    @Autowired
    private PasswordService passwordService;


    @Description("Password ID search")
    @GetMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getPasswordById(@PathVariable("id") Long id) {
        Password password = passwordService.getById(id);
        return new ResponseEntity<>(password, HttpStatus.OK);
    }

    @Description("Find all passwords by search string")
    @GetMapping("/passwords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAllPasswordsByContaining(@RequestParam() String searchString) {
        List<Password> passwords = passwordService.getAllByContaining(searchString);
        return new ResponseEntity<>(passwords, HttpStatus.OK);
    }

    @Description("Create password")
    @PostMapping("/passwords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createPassword(Password password) {
        passwordService.addPassword(password);
        return new ResponseEntity<>(new MessageResponse("Successfully added."), HttpStatus.CREATED);
    }

    @Description("Update password")
    @PutMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updatePass(@PathVariable("id") Long id, @RequestBody Password password) {
        passwordService.update(id, password);
        return new ResponseEntity<>(new MessageResponse("Updated successfully."), HttpStatus.OK);
    }

    @Description("Delete password")
    @DeleteMapping("/passwords/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> setPassRmv(@PathVariable("id") Long id) {
        int result = passwordService.setRmv(id);
        if (result == 0) {
            return new ResponseEntity<>("Cannot find password with id=" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Password with id=" + id + " successfully deleted", HttpStatus.OK);
    }
}
