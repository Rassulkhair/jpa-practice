package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.CategoryDao;
import org.example.dao.OptionDao;
import org.example.dao.ProductDao;
import org.example.model.Category;
import org.example.model.Option;
import org.example.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainForAddProduct {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        OptionDao optionDao = new OptionDao(em);
        ProductDao productDao = new ProductDao(em);
        ProductService productService = new ProductService(productDao);

        Scanner scanner = new Scanner(System.in);

        List<Category> categoryList = categoryDao.findAll();
        System.out.println("Список категорий");
        for (Category category : categoryList) {
            System.out.println(category.getId() + ".  " + category.getName());
        }

        System.out.println("Выберите категорию (id): ");
        Long categoryId = Long.parseLong(scanner.nextLine());
        Category category = categoryDao.find(categoryId);

        System.out.print("Введите название товара: ");
        String productName = scanner.nextLine();

        System.out.print("Введите стоимость товара: ");
        Double productPrice = Double.parseDouble(scanner.nextLine());

        List<Option> options = optionDao.findByCategoryId(categoryId);

        Map<Option, String> optionStringMap = new HashMap<>();
        for (Option option:options){
            System.out.print(option.getName() + ":  ");
            String value = scanner.nextLine();
            optionStringMap.put(option, value);
        }

        productService.createProductWithValues(category, productName, productPrice, optionStringMap);

        System.out.println("Продукт создан");

        em.close();
        factory.close();
    }
}
