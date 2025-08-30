package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.dao.ProductDao;
import org.example.model.Category;
import org.example.model.Product;
import org.example.service.ProductService;



public class MainForProduct {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        try {
            ProductDao productDao = new ProductDao(em);
            ProductService productService = new ProductService(productDao);

//            Category category = new Category();
//            category.setName("Электроника");

//            em.getTransaction().begin();
//            em.persist(category);
//            em.getTransaction().commit();

//            productService.create("Смартфон", 599.99, category);


            Product found = productService.findById(4L);
            System.out.println("Найден продукт: " + found.getName() + ", цена: " + found.getPrice());

//            found.setPrice(499.99);
//            productService.update(found);
//            System.out.println("Цена после обновления: " + productService.findById(5L).getPrice());

            Product fullProduct = productService.findWithCategoryAndValues(4L);
            System.out.println("Продукт: " + fullProduct.getName());
            System.out.println("Категория: " + fullProduct.getCategory().getName());
            System.out.println("Values: " + fullProduct.getValueList().toString());

//            productService.deleteById(1L);
//            Product deleted = productService.findById(1L);
//            System.out.println("После удаления: " + deleted);

        } finally {
            em.close();
            emf.close();
        }
    }
}
