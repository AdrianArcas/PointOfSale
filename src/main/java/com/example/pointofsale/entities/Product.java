package com.example.pointofsale.entities;

import jakarta.persistence.*;

@Entity
public class Product {

    private Long product_id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;
    private Integer tva;


    @Id
    @GeneratedValue
    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Basic
    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private ProductPhoto photo;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public ProductPhoto getPhoto(){ return photo;}

    public void setPhoto(ProductPhoto photo){
        this.photo = photo;
    }
}
