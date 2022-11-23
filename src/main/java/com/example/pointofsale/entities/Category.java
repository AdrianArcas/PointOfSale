package com.example.pointofsale.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
    private Long category_id;

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    @Id
    @GeneratedValue
    public Long getCategory_id() {
        return category_id;
    }

    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
