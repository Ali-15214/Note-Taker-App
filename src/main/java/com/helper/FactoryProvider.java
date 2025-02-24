package com.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
    private static SessionFactory sessionFactory; // Not initialized here

    public static SessionFactory getFactory() {
        // SessionFactory should already be initialized by the container
        return sessionFactory;
    }

    public static void setFactory(SessionFactory factory) {
        // Set by the container
        sessionFactory = factory;
    }

    public static void closeFactory() {
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
        }
    }
}
