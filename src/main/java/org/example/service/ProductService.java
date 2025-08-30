package org.example.service;

import org.example.dao.ProductDao;
import org.example.model.Category;
import org.example.model.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void create(String name, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);

        productDao.save(product);
    }

    public Product findById(Long id) {
        return productDao.find(id);
    }

    public List<Product> findByName(String name) {
        return productDao.findByName(name);
    }

    public void deleteById(Long id) {
        productDao.delete(id);
    }

    public Product update(Product product) {
        return productDao.update(product);
    }

    public Product findWithValues(Long id) {
        return productDao.findWithValues(id);
    }

    public Product findWithCategoryAndValues(Long id) {
        return productDao.findWithCategoryAndValues(id);
    }
}
