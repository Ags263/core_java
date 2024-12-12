package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            
            session.beginTransaction();

           
            Product product = new Product();
            product.setName("Laptop");
            product.setPrice(1200.0);

            // Create Orders
            Order order1 = new Order();
            order1.setQuantity(2);
            order1.setProduct(product);

            Order order2 = new Order();
            order2.setQuantity(1);
            order2.setProduct(product);

            
            product.setOrders(Arrays.asList(order1, order2));

           
            session.save(product);

          
            session.getTransaction().commit();
            System.out.println("Records saved successfully!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
