package com.example.pointofsale.common;

public class NotificationDto {
    Long id;
    String modified_or_added_account_username;
    String who_modified_or_added_account_username;
    String action;

    public NotificationDto(Long id, String modified_or_added_account_username, String who_modified_or_added_account_username, String action) {
        this.id = id;
        this.modified_or_added_account_username = modified_or_added_account_username;
        this.who_modified_or_added_account_username = who_modified_or_added_account_username;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModified_or_added_account_username() {
        return modified_or_added_account_username;
    }

    public void setModified_or_added_account_username(String modified_or_added_account_username) {
        this.modified_or_added_account_username = modified_or_added_account_username;
    }

    public String getWho_modified_or_added_account_username() {
        return who_modified_or_added_account_username;
    }

    public void setWho_modified_or_added_account_username(String who_modified_or_added_account_username) {
        this.who_modified_or_added_account_username = who_modified_or_added_account_username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
