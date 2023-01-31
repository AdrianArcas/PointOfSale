package com.example.pointofsale.common;

import com.example.pointofsale.entities.Receipt;

public class ReceiptProductsItemDto {
    private Long receipt_product_item_id;

    private Receipt receipt;

    private Integer product_id;
    private Integer quantity;

    public Long getReceipt_product_item_id() {
        return receipt_product_item_id;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ReceiptProductsItemDto(Long receipt_product_item_id, Receipt receipt, Integer product_id, Integer quantity) {
        this.receipt_product_item_id = receipt_product_item_id;
        this.receipt = receipt;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}
