package com.example.pointofsale.entities;

import jakarta.persistence.*;

@Entity
public class Account {

    private Long account_id;
    private String username;
    private String email;
    private String password;
    private Boolean is_active;

    private AccountPhoto photo;

    @Id
    @GeneratedValue
    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }


    @Basic
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public AccountPhoto getPhoto(){ return photo;}

    public void setPhoto(AccountPhoto photo){
        this.photo = photo;
    }
}
