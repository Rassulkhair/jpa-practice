package org.example.service;

import org.example.dao.ProductDao;
import org.example.model.Category;
import org.example.model.Option;
import org.example.model.Product;
import org.example.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public void createProductWithValues(Category category, String productName, Double productPrice, Map<Option, String> optionValues) {
        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        product.setCategory(category);

        List<Value> values = new ArrayList<>();
        optionValues.forEach((option, valueName) -> {
            Value value = new Value();
            value.setName(valueName);
            value.setOption(option);
            value.setProduct(product);
            values.add(value);
        });

        product.setValueList(values);

        productDao.save(product);
    }


}
