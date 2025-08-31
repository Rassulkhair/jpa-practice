package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Option;

import java.util.List;

public class OptionDao {
    private final EntityManager entityManager;


    public OptionDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Option> findByCategoryId(Long categoryId) {
        return entityManager.createQuery(
                        "SELECT o FROM Option o WHERE o.category.id = :categoryId", Option.class
                )
                .setParameter("categoryId", categoryId)
                .getResultList();
    }
}
