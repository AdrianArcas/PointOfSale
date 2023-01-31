package com.example.pointofsale.common;

import com.example.pointofsale.entities.ReceiptProductsItem;

import java.time.LocalDateTime;
import java.util.Collection;

public class ReceiptDto {
    private Long id;
    private String cashier_name;
    private LocalDateTime date;

    private Double total;

    public ReceiptDto(Long id, String cashier_name, LocalDateTime date,  Double total) {
        this.id = id;
        this.cashier_name = cashier_name;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getCashier_name() {
        return cashier_name;
    }

    public LocalDateTime getDate() {
        return date;
    }


    public Double getTotal() {
        return total;
    }
}
