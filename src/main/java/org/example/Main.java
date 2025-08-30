package org.example;

import jakarta.persistence.*;
import org.example.dao.CategoryDao;
import org.example.model.Category;
import org.example.service.CategoryService;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        CategoryService categoryService = new CategoryService(categoryDao);

        System.out.println("-----CREATE------");
        categoryService.createCategory("Наушники");

        System.out.println("-----FIND------");
        Category category1 = categoryService.findById(3L);
        System.out.println("Найдена категория: " + category1.getName() + "Id: " + category1.getId());


        System.out.println("-----UPDATE------");
        category1.setName("Наушники и гарнитура");
        categoryService.update(category1);
        Category updated = categoryService.findById(3L);
        System.out.println("После обновления: " + updated.getName());


        System.out.println("----FIND BY NAME------");
        List<Category> categories = categoryService.findByName("Наушники и гарнитура");
        for (Category c : categories) {
            System.out.println("Найдена категория: " + c.getId() + " - " + c.getName());
        }

        System.out.println("-----DELETE------");
        categoryService.deleteById(3L);
        Category deleted = categoryService.findById(3L);
        System.out.println();
        System.out.println("После удаления (должно быть null): " + deleted);


        em.close();
        factory.close();
    }
}
