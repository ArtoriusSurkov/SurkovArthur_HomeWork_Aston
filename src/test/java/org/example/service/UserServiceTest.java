package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    private UserDao mockUserDao;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockUserDao = mock(UserDao.class);
        userService = new UserService(mockUserDao);
    }

    @Test
    public void testSaveUser() {
        User user = new User("John", "john@mail.ru",15);
        userService.saveUser(user);
        verify(mockUserDao).saveUser(user);
    }

    @Test
    public void testGetUser() {
        User user = new User("John", "john@mail.ru",15);
        when(mockUserDao.getUser(0)).thenReturn(user);

        User result = userService.getUser(0);
        assertEquals(user, result);
        verify(mockUserDao).getUser(0);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(new User("John", "john@mail.ru",15), new User("Mih", "mih@mail.ru",12));
        when(mockUserDao.getAllUsers()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(users, result);
        verify(mockUserDao).getAllUsers();
    }

    @Test
    public void testUpdateUser() {
        User user = new User("John", "john@mail.ru",15);
        userService.updateUser(user);
        verify(mockUserDao).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        int userId = 0;
        userService.deleteUser(userId);
        verify(mockUserDao).deleteUser(userId);
    }

    @Test
    public void testClose() {
        userService.close();
        verify(mockUserDao).close();
    }
}