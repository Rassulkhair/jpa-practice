package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Value;

public class ValueDao {
    private final EntityManager entityManager;

    public ValueDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Value update(Value value) {
        try {
            entityManager.getTransaction().begin();
            Value updated = entityManager.merge(value);
            entityManager.getTransaction().commit();
            return updated;
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
