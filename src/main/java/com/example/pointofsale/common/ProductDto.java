package com.example.pointofsale.common;

import com.example.pointofsale.entities.Category;

public class ProductDto {
    private Long product_id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;
    private Integer tva;

    public ProductDto(Long product_id, String name, Double price, Integer quantity, Category category, Integer tva) {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.tva = tva;
    }

    public String getName() {
        return name;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getTva() {
        return tva;
    }

}
