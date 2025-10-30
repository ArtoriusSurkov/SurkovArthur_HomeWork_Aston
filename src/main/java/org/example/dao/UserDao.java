package org.example.dao;

import org.example.entity.User;
import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User getUser(int id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);
}
