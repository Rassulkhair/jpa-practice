package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Category;
import org.example.model.Product;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        //SELECT
//        Category category = entityManager.find(Category.class, 1L);
//        System.out.println(category.getId() + " " + category.getName());

//        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
//
//        List<Category> categories = query.getResultList();
//
//        categories.forEach(category -> {
//            System.out.println(category.getId() + " " + category.getName());
//        });


//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Name for category");
//        String categoryName = scanner.nextLine();
//
//        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.name = ?1", Category.class);
//        query.setParameter(1, categoryName);
//        Optional<Category> optional = query.getResultList()
//                .stream()
//                .findFirst();
//        if (optional.isEmpty()) {
//            System.out.println("Not found");
//        } else {
//            Category category = optional.get();
//            System.out.println(category.getId() + " " + category.getName());
//        }


//        //CREATE
//
//        Category category = new Category();
//        category.setName("Мебель");
//        try{
//            System.out.println("before persist : " + category.getId());
//            entityManager.getTransaction().begin();
//            entityManager.persist(category);
//            entityManager.getTransaction().commit();
//
//            System.out.println("Категория создана  + " + category.getId());
//        }catch (RuntimeException e){
//            entityManager.getTransaction().rollback();
//            e.printStackTrace();
//        }


//        //UPDATE
//        Category category = entityManager.find(Category.class, 5L);
//        category.setName("МЕБЕЛЬ UPDATED");
//
//        try{
//            entityManager.getTransaction().begin();
//            entityManager.merge(category);
//            entityManager.getTransaction().commit();
//        }catch (RuntimeException e){
//            entityManager.getTransaction().rollback();
//            e.printStackTrace();
//        }

//        //DELETE
//        Category category = entityManager.find(Category.class, 5L);
//
//        try{
//            entityManager.getTransaction().begin();
//            entityManager.remove(category);
//            entityManager.getTransaction().commit();
//        }catch (RuntimeException e){
//            entityManager.getTransaction().rollback();
//            e.printStackTrace();
//        }


        Product product = entityManager.find(Product.class,1L);
        System.out.println(product.getName() + " " + product.getPrice());
        System.out.println(product.getCategory().getName());

        Category category = entityManager.find(Category.class,1L);
        System.out.println(category.getName());
        category.getProductList().forEach(product1 -> {
            System.out.println(" "  + product1.getId() + ". " + product1.getName());
        });
    }
}
