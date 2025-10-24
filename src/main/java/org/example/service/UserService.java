package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
        userDao = new UserDao();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public void close() {
        userDao.close();
    }
}