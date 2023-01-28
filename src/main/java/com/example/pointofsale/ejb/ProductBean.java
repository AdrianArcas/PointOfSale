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

import java.util.ArrayList;
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
        newProduct.setPrice(price + (double) (tva + 100 / 100) *price);
        entityManager.persist(newProduct);
    }

    public ProductDto findProductById(Long productId) {
        LOG.info("findById");

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
}
