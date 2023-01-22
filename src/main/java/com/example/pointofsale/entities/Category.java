package com.example.pointofsale.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Category {
    private Long category_id;
    private String name;
    private Collection<Product> products;

    @Id
    @GeneratedValue
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @OneToMany(mappedBy = "category")
    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
