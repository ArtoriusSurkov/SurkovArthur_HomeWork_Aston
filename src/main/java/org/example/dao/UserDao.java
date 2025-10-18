package org.example.dao;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserDao {
    private final SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public UserDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        logger.info("SessionFactory created.");
    }

    public User saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("User saved successfully: {}", user);
            return user;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error while saving user: ", e);
            return null;
        }
    }

    public User getUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            logger.info("Fetched user by ID ({}): {}", id, user);
            return user;
        } catch (Exception e) {
            logger.error("Error while fetching user by ID: {}", id, e);
            return null;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            logger.info("Fetched all users. Total count: {}", users.size());
            return users;
        } catch (Exception e) {
            logger.error("Error while fetching all users: ", e);
            return null;
        }
    }

    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            logger.info("User updated successfully: {}", user);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error while updating user: ", e);
        }
    }

    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                logger.info("User deleted successfully with ID: {}", id);
            } else {
                logger.warn("No user found to delete with ID: {}", id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error while deleting user with ID: {}", id, e);
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
            logger.info("SessionFactory closed.");
        }
    }
}