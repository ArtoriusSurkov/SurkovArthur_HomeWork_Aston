package org.example.utils;

import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactoryUtil.class);

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                logger.info("SessionFactory успешно создан");
            } catch (Exception e) {
                logger.error("Ошибка при создании SessionFactory", e);
                throw new RuntimeException("Ошибка при создании SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}
