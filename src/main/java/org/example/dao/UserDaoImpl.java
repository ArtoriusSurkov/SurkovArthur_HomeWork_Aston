package org.example.dao;

import org.example.entity.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUser(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            logger.info("Получен пользователь с id={}", id);
            return user;
        } catch (Exception e) {
            logger.error("Ошибка при получении пользователя с id={}", id, e);
            throw new RuntimeException("Ошибка при получении пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("FROM User", User.class).list();
            logger.info("Получено {} пользователей", users.size());
            return users;
        } catch (Exception e) {
            logger.error("Ошибка при получении всех пользователей", e);
            throw new RuntimeException("Ошибка при получении всех пользователей", e);
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                logger.info("Пользователь успешно сохранен с id={}", user.getId());
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Ошибка при сохранении пользователя", e);
                throw new RuntimeException("Ошибка при сохранении пользователя", e);
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(user);
                transaction.commit();
                logger.info("Пользователь успешно обновлен с id={}", user.getId());
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Ошибка при обновлении пользователя с id={}", user.getId(), e);
                throw new RuntimeException("Ошибка при обновлении пользователя", e);
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = getUser(id);
                if (user != null) {
                    session.delete(user);
                    transaction.commit();
                    logger.info("Пользователь успешно удален с id={}", id);
                } else {
                    logger.warn("Пользователь с id={} не найден для удаления", id);
                }
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Ошибка при удалении пользователя с id={}", id, e);
                throw new RuntimeException("Ошибка при удалении пользователя", e);
            }
        }
    }
}