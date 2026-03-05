package com.inventory.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.inventory.entity.Product;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Product.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("SessionFactory creation failed.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}