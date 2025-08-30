package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.model.Category;

import java.util.List;

public class CategoryDao {
    private final EntityManager entityManager;

    public CategoryDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Category find(Long id) {
        return entityManager.find(Category.class, id);
    }

    public void save(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(Long categoryId) {
        try {
            entityManager.getTransaction().begin();
            Category category = entityManager.find(Category.class, categoryId);
            if (category != null) {
                entityManager.remove(category);
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Category update(Category category) {
        try {
            entityManager.getTransaction().begin();
            Category updated = entityManager.merge(category);
            entityManager.getTransaction().commit();
            return updated;
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

    }
    public List<Category> findByName(String name){
        TypedQuery<Category> query = entityManager.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name", Category.class
        );
        query.setParameter("name", name);
        return query.getResultList();
    }
}
