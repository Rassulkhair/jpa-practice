package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.*;
import org.example.model.*;
import org.example.service.*;

import java.util.List;
import java.util.Scanner;

public class MainForUpdateProduct {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        ProductDao productDao = new ProductDao(em);
        ValueDao valueDao = new ValueDao(em);

        ProductService productService = new ProductService(productDao);
        ValueService valueService = new ValueService(valueDao);

        Scanner scanner = new Scanner(System.in);

        List<Category> categories = categoryDao.findAll();
        System.out.println("Категории:");
        for (Category c : categories) {
            System.out.println(c.getId() + ". " + c.getName());
        }

        System.out.print("Выберите категорию: ");
        Long categoryId = scanner.nextLong();
        scanner.nextLine();

        List<Product> products = productDao.findByCategoryId(categoryId);


        System.out.println("Товары:");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.getName() + " (" + p.getPrice() + ")");
        }

        System.out.print("Выберите товар: ");
        Long productId = scanner.nextLong();
        scanner.nextLine();

        Product fullProduct = productService.findWithCategoryAndValues(productId);

        System.out.println("\nВыбран товар: " + fullProduct.getName());
        System.out.println("Цена: " + fullProduct.getPrice());
        System.out.println("Характеристики:");
        for (Value v : fullProduct.getValueList()) {
            System.out.println("- " + v.getOption().getName() + ": " + v.getName());
        }

        System.out.println("\nЧто вы хотите изменить?");
        System.out.println("1. Название");
        System.out.println("2. Цена");
        System.out.println("3. Характеристику");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Введите новое название: ");
                String newName = scanner.nextLine();
                fullProduct.setName(newName);
                productService.update(fullProduct);
                System.out.println("Название обновлено!");
            }
            case 2 -> {
                System.out.print("Введите новую цену: ");
                double newPrice = scanner.nextDouble();
                scanner.nextLine();
                fullProduct.setPrice(newPrice);
                productService.update(fullProduct);
                System.out.println("Цена обновлена!");
            }
            case 3 -> {
                List<Value> values = fullProduct.getValueList();
                for (int i = 0; i < values.size(); i++) {
                    Value v = values.get(i);
                    System.out.println((i + 1) + ". " + v.getOption().getName() + " (" + v.getName() + ")");
                }
                System.out.print("Выберите характеристику: ");
                int valueIndex = scanner.nextInt() - 1;
                scanner.nextLine();

                Value selectedValue = values.get(valueIndex);
                System.out.print("Введите новое значение: ");
                String newValue = scanner.nextLine();

                selectedValue.setName(newValue);
                valueService.update(selectedValue);
                System.out.println("Характеристика обновлена!");
            }
            default -> System.out.println("Неверный выбор!");
        }

        em.close();
        emf.close();
    }
}
