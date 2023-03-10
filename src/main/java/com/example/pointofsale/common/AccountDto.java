package com.example.pointofsale.common;

public class AccountDto {
    private Long account_id;
    private String username;
    private String email;
    private String password;
    private Boolean is_active;

    public AccountDto(Long account_id, String username, String email, String password, Boolean is_active) {
        this.account_id = account_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_active = is_active;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
}
