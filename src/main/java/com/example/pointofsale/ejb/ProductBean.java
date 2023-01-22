package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.entities.Product;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductBean {
    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public Product findAllProducts(){
        LOG.info("findAllProducts");
        try{
            TypedQuery<Product> typedQuery = entityManager.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> product = typedQuery.getResultList();
            return product.get(0);

        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }
}
