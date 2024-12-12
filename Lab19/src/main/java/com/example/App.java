package com.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
       
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Subject.class)
                .buildSessionFactory();

       
        Session session = factory.getCurrentSession();

        try {
            
            Subject subject = new Subject();
            subject.setName("Mathematics");
            subject.setCreditPoints(4);

            
            Student student = new Student();
            student.setName("Nilesh Tambekar");
            student.setSubject(subject);

            
            session.beginTransaction();

         
            session.save(student);

            session.getTransaction().commit();

            System.out.println("Record saved successfully!");

        } finally {
            factory.close();
        }
    }
}
