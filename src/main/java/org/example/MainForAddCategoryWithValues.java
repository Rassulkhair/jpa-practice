package org.example;

import jakarta.persistence.*;
import org.example.dao.CategoryDao;
import org.example.service.CategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class MainForAddCategoryWithValues {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        CategoryService categoryService = new CategoryService(categoryDao);

        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Введите название категории------");
        String categoryName = scanner.nextLine();

        System.out.print("Введите названия характеристик (через запятую и пробел): ");
        String optionInput = scanner.nextLine();
        List<String> options = Arrays.stream(optionInput.split(","))
                .map(String::trim)
                .toList();

        categoryService.createCategoryWithOptions(categoryName, options);

        em.close();
        factory.close();
    }
}
