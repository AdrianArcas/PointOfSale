package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.entities.Product;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Stateful
@SessionScoped
public class InvoiceBean implements Serializable {
/*    Set<Long> productIds=new HashSet<>();*/
    HashMap<ProductDto, Long> IdsToQuantity = new HashMap<ProductDto, Long>();

    public HashMap<ProductDto, Long> getIdsToQuantity() {
        return IdsToQuantity;
    }


    public Set<ProductDto> getProductIds() {
        return IdsToQuantity.keySet();
    }

    public void addQuantityAndID(ProductDto product, Long quantity){
        this.IdsToQuantity.put(product,quantity);
    }




}
