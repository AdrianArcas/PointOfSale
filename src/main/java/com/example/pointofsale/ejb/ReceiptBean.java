package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.common.ReceiptDto;
import com.example.pointofsale.entities.Product;
import com.example.pointofsale.entities.Receipt;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ReceiptBean {
    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<ReceiptDto> findAllReceipts() {
        LOG.info("findAllReceipts");
        try {
            TypedQuery<Receipt> typedQuery = entityManager.createQuery("SELECT r FROM Receipt r", Receipt.class);
            List<Receipt> receipts = typedQuery.getResultList();
            return copyReceiptsToDto(receipts);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<ReceiptDto> copyReceiptsToDto(List<Receipt> receipts) {

        List<ReceiptDto> receiptDtoList = new ArrayList<>();

        for (Receipt r : receipts) {
            receiptDtoList.add(new ReceiptDto(r.getId(), r.getCashier_name(), r.getDate(), r.getPayment_method(), r.getTotal()));
        }
        return receiptDtoList;
    }

    public ReceiptDto findReceiptById(Long receiptId) {
        LOG.info("findReceiptById");

        Receipt receipt = entityManager.find(Receipt.class, receiptId);

        ReceiptDto receiptDto = new ReceiptDto(receipt.getId(), receipt.getCashier_name(), receipt.getDate(), receipt.getPayment_method(), receipt.getTotal());

        return receiptDto;
    }
}
