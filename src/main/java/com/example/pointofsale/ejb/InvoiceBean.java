package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.entities.Product;
import com.example.pointofsale.entities.Receipt;
import com.example.pointofsale.entities.ReceiptProductsItem;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.*;

@Stateful
@SessionScoped
public class InvoiceBean implements Serializable {

    @PersistenceContext
    EntityManager entityManager;
    HashMap<ProductDto, Long> IdsToQuantity = new HashMap<ProductDto, Long>();


    public HashMap<ProductDto, Long> getIdsToQuantity() {
        return IdsToQuantity;
    }

    public void setIdsToQuantity(HashMap<ProductDto, Long> idsToQuantity) {
        IdsToQuantity = idsToQuantity;
    }

    public Set<ProductDto> getProductIds() {
        return IdsToQuantity.keySet();
    }

    public void addQuantityAndID(ProductDto product, Long quantity) {

        this.IdsToQuantity.put(product, quantity);
    }

    public Double calcTotal(HashMap<ProductDto, Long> p) {
        ProductDto key;
        Double total = new Double(0);
        Double intermediar;
        for (Map.Entry<ProductDto, Long> entry : p.entrySet()) {
            key = entry.getKey();
            Long value = entry.getValue();
            intermediar = key.getPrice()*value;
            total += intermediar;
        }
        return total;
    }
    public void ResetInvoiceSell(){

        for (ProductDto p: this.IdsToQuantity.keySet()){
            Product product=entityManager.find(Product.class, p.getProduct_id());
            product.setQuantity((int) (product.getQuantity()+this.IdsToQuantity.get(p)));
        }
        this.IdsToQuantity.clear();


    }

    public void ResetInvoiceReturn(){

        this.IdsToQuantity.clear();
    }

    public void substractQuantity(ProductDto p, Long quantity){
        Product product=entityManager.find(Product.class, p.getProduct_id());
        product.setQuantity((int) (product.getQuantity()-quantity));
    }
    public void createReceiptProductsItems(){
        List<Receipt> receipt= entityManager.createQuery("SELECT r FROM Receipt r order by r.id desc  ", Receipt.class).getResultList() ;
        for (ProductDto p: this.IdsToQuantity.keySet()){
            ReceiptProductsItem newReceiptProductItem = new ReceiptProductsItem();

            newReceiptProductItem.setProduct_id(Math.toIntExact(p.getProduct_id()));
            newReceiptProductItem.setQuantity(Math.toIntExact(this.IdsToQuantity.get(p)));
            newReceiptProductItem.setReceipt(receipt.get(0));
            entityManager.persist(newReceiptProductItem);
        }


    }
}



