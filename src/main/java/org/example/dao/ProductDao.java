package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.Product;

import java.util.List;

public class ProductDao {
    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product find(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void save(Product product) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Long productId) {
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, productId);
            if (product != null) {
                entityManager.remove(product);
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Product update(Product product) {
        try {
            entityManager.getTransaction().begin();
            Product updated = entityManager.merge(product);
            entityManager.getTransaction().commit();
            return updated;
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

    }

    public List<Product> findByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.name = :name", Product.class
        );
        query.setParameter("name", name);
        return query.getResultList();
    }

    public Product findWithValues(Long productId) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p " +
                "LEFT JOIN FETCH p.valueList " +
                "WHERE p.id = :id", Product.class
        );
        query.setParameter("id", productId);
        return query.getSingleResult();
    }

    public Product findWithCategoryAndValues(Long id) {
        TypedQuery<Product> query = entityManager.createQuery(
                "SELECT p FROM Product p " +
                "JOIN FETCH p.category " +
                "LEFT JOIN FETCH p.valueList " +
                "WHERE p.id = :id", Product.class
        );
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.category.id = :categoryId", Product.class
        ).setParameter("categoryId", categoryId).getResultList();
    }
}
