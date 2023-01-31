package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.common.ReceiptDto;
import com.example.pointofsale.common.ReceiptProductsItemDto;
import com.example.pointofsale.entities.Product;
import com.example.pointofsale.entities.Receipt;
import com.example.pointofsale.entities.ReceiptProductsItem;
import jakarta.ejb.EJBException;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class ReceiptProductsItemBean {
    private static final Logger LOG = Logger.getLogger(ReceiptProductsItemBean.class.getName());
   @Inject
   InvoiceBean invoiceBean;
   @Inject
   ProductBean productBean;
    @PersistenceContext
    EntityManager entityManager;

    public List<ReceiptProductsItemDto> findAllReceiptProducts() {
        LOG.info("findAllReceiptsProducts");
        try {
            TypedQuery<ReceiptProductsItem> typedQuery = entityManager.createQuery("SELECT r FROM ReceiptProductsItem r", ReceiptProductsItem.class);
            List<ReceiptProductsItem> receipts = typedQuery.getResultList();
            receipts.get(0).getReceipt().getId();
            return copyReceiptProductsToDto(receipts);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
    private List<ReceiptProductsItemDto> copyReceiptProductsToDto(List<ReceiptProductsItem> receipts) {

        List<ReceiptProductsItemDto> receiptDtoList = new ArrayList<>();

        for (ReceiptProductsItem r : receipts) {
            receiptDtoList.add(new ReceiptProductsItemDto(r.getReceipt_product_item_id(),r.getReceipt(), r.getProduct_id(), r.getQuantity()));
        }
        return receiptDtoList;
    }
   /* public ReceiptProductsItemDto findProductsByRecepitID(Long receiptId) {
        LOG.info("findProductsByRecepitID");
        try {
            List<Long> productIds =
                    entityManager.createQuery("SELECT p.product_id FROM Product p WHERE p.name = :name", Long.class)
                            .setParameter("name", productName).getResultList();
            Product product = entityManager.find(Product.class, productIds.get(0));
            ProductDto productDto = new ProductDto(product.getProduct_id(), product.getName(), product.getPrice(), product.getQuantity(), product.getCategory(), product.getTva());
            return productDto;
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }*/
    public HashMap<ProductDto,Long> getProductsByReceiptId(Long id){
        HashMap<ProductDto,Long> productsQuantity = new HashMap<>();
       List<ReceiptProductsItemDto> allPaidProducts = findAllReceiptProducts();
       for(ReceiptProductsItemDto p : allPaidProducts){
           if(p.getReceipt().getId() == id){
               productsQuantity.put(productBean.findProductById(Long.valueOf(p.getProduct_id())),Long.valueOf(p.getQuantity()));
           }
       }
      return  productsQuantity;
    }
}
