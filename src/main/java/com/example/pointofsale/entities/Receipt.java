package com.example.pointofsale.entities;

import jakarta.persistence.*;

import java.time.*;
import java.util.Collection;

@Entity
public class Receipt {

    private Long id;
    private String cashier_name;
    private LocalDateTime date;
    private String payment_method;
    private Double total;
    private Collection<ReceiptProductsItem> products;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long receipt_id) {
        this.id = receipt_id;
    }

    @Basic
    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }


    @Basic
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    @Basic
    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    @Basic
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @OneToMany(mappedBy = "receipt")
    public Collection<ReceiptProductsItem> getProducts() {
        return products;
    }

    public void setProducts(Collection<ReceiptProductsItem> products) {
        this.products = products;
    }
}
