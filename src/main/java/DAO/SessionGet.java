package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionGet {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().configure();
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable th) {
            System.err.println("Init SessionFactory creation failed: " + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
