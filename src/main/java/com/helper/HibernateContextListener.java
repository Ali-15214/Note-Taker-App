package com.helper;

import com.helper.FactoryProvider;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // Create SessionFactory when the application starts
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        FactoryProvider.setFactory(sessionFactory);
        System.out.println("SessionFactory created and initialized at application startup.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Close SessionFactory when the application stops
        FactoryProvider.closeFactory();
        System.out.println("SessionFactory closed at application shutdown.");
    }
}