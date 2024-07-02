package com.nihither.scriptutilservice.models.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String group_name;
    private String title;
    private String username;
    @NotBlank
    private String password;
    private String url;
    private String notes;
    private Date creation_time;
    private Date last_modification_time;
    private Date expiry_time;
    private boolean rmv;

    public Password() {
    }

    public Password(String group_name, String title, String username, String password, String url,
                    String notes, Date creation_time, Date last_modification_time, Date expiry_time, boolean rmv) {
        this.group_name = group_name;
        this.title = title;
        this.username = username;
        this.password = password;
        this.url = url;
        this.notes = notes;
        this.creation_time = creation_time;
        this.last_modification_time = last_modification_time;
        this.expiry_time = expiry_time;
        this.rmv = rmv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group_name;
    }

    public void setGroup(String group) {
        this.group_name = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }

    public Date getLast_modification_time() {
        return last_modification_time;
    }

    public void setLast_modification_time(Date last_modification_time) {
        this.last_modification_time = last_modification_time;
    }

    public Date getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Date expiry_time) {
        this.expiry_time = expiry_time;
    }

    public boolean isRmv() {
        return rmv;
    }

    public void setRmv(boolean rmv) {
        this.rmv = rmv;
    }
}
