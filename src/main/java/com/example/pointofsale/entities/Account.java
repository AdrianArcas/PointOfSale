package com.example.pointofsale.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Account {

    private Long account_id;

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    @Id
    @GeneratedValue
    public Long getAccount_id() {
        return account_id;
    }

    private String role;

    @Basic
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    private String username;

    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String email;

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    private String password;

    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private Integer is_active;

    @Basic
    public Integer getIs_active() {
        return is_active;
    }

    public void setIs_active(Integer is_active) {
        this.is_active = is_active;
    }


    private String user_photo;

    @Basic
    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
}
