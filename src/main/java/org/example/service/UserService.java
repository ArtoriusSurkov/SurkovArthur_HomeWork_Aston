package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.entity.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDaoImpl();

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.usersDao = userDao;
    }

    public User getUser(int id) {
        return usersDao.getUser(id);
    }

    public void saveUser(User user) {
        usersDao.saveUser(user);
    }

    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }

    public void updateUser(User user) {
        usersDao.updateUser(user);
    }

    public List<User> getAllUsers() {
        return usersDao.getAllUsers();
    }
}