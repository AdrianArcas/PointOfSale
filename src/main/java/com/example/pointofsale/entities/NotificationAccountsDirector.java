package com.example.pointofsale.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class NotificationAccountsDirector {
    private Long notification_id;
    private Long modified_or_added_account_id;
    private Long who_modified_or_added_id;
    private String action;


    @Id
    @GeneratedValue
    public Long getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Long notification_id) {
        this.notification_id = notification_id;
    }

    @Basic
    public Long getModified_or_added_account_id() {
        return modified_or_added_account_id;
    }

    public void setModified_or_added_account_id(Long modified_or_added_account_id) {
        this.modified_or_added_account_id = modified_or_added_account_id;
    }

    @Basic
    public Long getWho_modified_or_added_id() {
        return who_modified_or_added_id;
    }

    public void setWho_modified_or_added_id(Long who_modified_or_added_id) {
        this.who_modified_or_added_id = who_modified_or_added_id;
    }

    @Basic
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
