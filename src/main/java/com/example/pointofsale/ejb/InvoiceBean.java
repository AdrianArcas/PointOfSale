package com.example.pointofsale.ejb;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Stateful
@SessionScoped
public class InvoiceBean implements Serializable {
    Set<Long> productIds=new HashSet<>();


    public Set<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<Long> productIds) {
        this.productIds = productIds;
    }


}
