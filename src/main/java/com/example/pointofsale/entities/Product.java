package com.example.pointofsale.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

    private Long product_id;
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Id
    @GeneratedValue
    public Long getProduct_id() {
        return product_id;
    }
    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Double price;

    @Basic
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private Integer quantity;

    @Basic
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private Integer category;

    @Basic
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    private Integer tva;

    @Basic
    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    private String photo;

    @Basic
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
