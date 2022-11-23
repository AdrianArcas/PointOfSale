package com.example.pointofsale.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.*;

@Entity
public class Receipt {

    private Long receipt_id;
    public void setReceipt_id(Long receipt_id) {
        this.receipt_id = receipt_id;
    }

    @Id
    @GeneratedValue
    public Long getReceipt_id() {
        return receipt_id;
    }

    private String cashier_name;

    @Basic
    public String getCashier_name() {
        return cashier_name;
    }

    public void setCashier_name(String cashier_name) {
        this.cashier_name = cashier_name;
    }

    private LocalDateTime date;

    @Basic
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private String payment_method;

    @Basic
    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    private Double total;

    @Basic
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
