package com.example.pointofsale.ejb;

import com.example.pointofsale.common.ProductDto;
import com.example.pointofsale.common.ProductPhotoDto;
import com.example.pointofsale.entities.Category;
import com.example.pointofsale.entities.Product;
import com.example.pointofsale.entities.ProductPhoto;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
@Stateless
public class ProductBean {
    private static final Logger LOG = Logger.getLogger(ProductBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<ProductDto> findAllProducts(){
        LOG.info("findAllProducts");
        try{
            TypedQuery<Product> typedQuery = entityManager.createQuery("SELECT p FROM Product p", Product.class);
            List<Product> products = typedQuery.getResultList();
            return copyProductsToDto(products);

        }catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<ProductDto> copyProductsToDto(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product p : products){
            productDtoList.add(new ProductDto(p.getProduct_id(), p.getName(), p.getPrice(), p.getQuantity(), p.getCategory(), p.getTva()));

        }
        return productDtoList;
    }

    public void createProduct(String name, Double price,  Integer quantity, Long categoryId, Integer tva){
        LOG.info("createProduct");
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setQuantity(quantity);
        Category category = entityManager.find(Category.class, categoryId);
        newProduct.setCategory(category);
        newProduct.setTva(tva);
        BigDecimal bd = BigDecimal.valueOf((((double)tva + 100 )/ 100) *price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        newProduct.setPrice(bd.doubleValue());
        entityManager.persist(newProduct);
    }

    public ProductDto findProductById(Long productId) {
        LOG.info("findProductById");

        Product product = entityManager.find(Product.class, productId);

        ProductDto productDto = new ProductDto(product.getProduct_id(), product.getName(), product.getPrice(), product.getQuantity(), product.getCategory(), product.getTva());

        return productDto;
    }

    public void addPhotoToProduct(Long productId, String filename, String fileType, byte[] fileContent) {

        LOG.info("addPhotoToProduct");
        ProductPhoto photo = new ProductPhoto();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        Product product = entityManager.find(Product.class, productId);
        if (product.getPhoto() != null) {
            entityManager.remove(product.getPhoto());
        }
        product.setPhoto(photo);
        photo.setProduct(product);
        entityManager.persist(photo);
    }

    public ProductPhotoDto findPhotoByProductId(Integer productId) {
        List<ProductPhoto> photos = entityManager
                .createQuery("SELECT p FROM ProductPhoto p where p.product.product_id = :id", ProductPhoto.class)
                .setParameter("id", productId)
                .getResultList();
        if (photos.isEmpty()) {
            return null;
        }
        ProductPhoto photo = photos.get(0); // the first element
        return new ProductPhotoDto(photo.getId(), photo.getFilename(), photo.getFileType(),
                photo.getFileContent());
    }

    public List<ProductDto>findProductsByCategory(Long categoryId) {
        LOG.info("findProductsByCategory");
        List<Product> productList = entityManager.createQuery( "SELECT p FROM Product p WHERE p.category.category_id = :id", Product.class)
                .setParameter("id", categoryId)
                .getResultList();
        return copyProductsToDto(productList);
    }

    public Long getCategoryId(String category) {
        List<Category> Id = entityManager.createQuery("SELECT c FROM Category c where c.name = :name", Category.class)
                .setParameter("name", category)
                .getResultList();
        Long categoryId = Id.get(0).getCategory_id();
        return categoryId;
    }

    public void deleteProductById(Long productId) {
        LOG.info("deleteProductById");
        Product product = entityManager.find(Product.class, productId);
        entityManager.remove(product);
    }

    public void updateProduct(Long productId, String name, Double price, Integer quantity, Long category, Integer tva) {
        LOG.info("updateProduct");
        Product product = entityManager.find(Product.class, productId);
        product.setName(name);
        product.setQuantity(quantity);
        Category categoryId = entityManager.find(Category.class, category);
        product.setCategory(categoryId);
        product.setTva(tva);
        product.setPrice(((((double)tva + 100 )/ 100) *price));
    }
    public Collection<String> findProductnamesByIds(Collection<Long> productIds){
        List<String> productnames=
                entityManager.createQuery("SELECT p.name FROM Product p WHERE p.product_id IN :productIds", String.class)
                        .setParameter("productIds", productIds).getResultList();
        return productnames;
    }
}
