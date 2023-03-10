package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.entities.Product;
import com.example.pointofsale.entities.Receipt;
import com.example.pointofsale.entities.ReceiptProductsItem;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
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

    Long recipeID;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Long recipeID) {
        this.recipeID = recipeID;
    }

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

    public void addQuantity(ProductDto p, Long quantity){
        Product product=entityManager.find(Product.class, p.getProduct_id());
        product.setQuantity((int) (product.getQuantity()+quantity));
    }

    public void returnProducts(List<Long> productIds) {
        Receipt receipt =entityManager.find(Receipt.class,this.recipeID);
        List receiptProductsItem=new ArrayList<>();

        for (ProductDto p: this.IdsToQuantity.keySet()){

            for (Long productId: productIds) {

                if(productId.equals(p.getProduct_id())){
                     receiptProductsItem=entityManager.createQuery
                            ("SELECT r FROM ReceiptProductsItem r WHERE r.product_id=:id and r.receipt.id=:receiptId and r.quantity=:quantity")
                            .setParameter("id", productId)
                            .setParameter("receiptId",this.recipeID)
                            .setParameter("quantity",this.IdsToQuantity.get(p))
                            .getResultList();

                     Product product =entityManager.find(Product.class,p.getProduct_id());
                     product.setQuantity((int) (product.getQuantity()+this.IdsToQuantity.get(p)));
                     receipt.setTotal(receipt.getTotal()-this.IdsToQuantity.get(p)*p.getPrice());

                }

            }
            entityManager.remove(receiptProductsItem.get(0));

        }

    }
}



