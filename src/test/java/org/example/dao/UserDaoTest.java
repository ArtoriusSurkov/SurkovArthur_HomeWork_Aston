package org.example.dao;

import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {

    private SessionFactory sessionFactory;
    private UserDao userDao;

    @BeforeAll
    public void setUpAll() {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @BeforeEach
    public void setUp() {
        userDao = new UserDao(sessionFactory);
    }

//    @AfterEach
//    public void cleanUp() {
//        List<User> users = userDao.getAllUsers();
//        for (User user : users) {
//            userDao.deleteUser(user.getId());
//        }
//    }

    @AfterAll
    public void tearDownAll() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setAge(10);

        User savedUser = userDao.saveUser(user);
        assertNotNull(savedUser);
        assertTrue(savedUser.getId() > 0);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setAge(10);
        userDao.saveUser(user);

        User fetchedUser = userDao.getUser(user.getId());
        assertNotNull(fetchedUser);
        assertEquals("Alice", fetchedUser.getName());
    }

    @Test
    public void testGetAllUsers() {
        userDao.getAllUsers().forEach(u -> userDao.deleteUser(u.getId()));

        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("u1@example.com");
        user1.setAge(10);
        userDao.saveUser(user1);

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("u2@example.com");
        user2.setAge(10);
        userDao.saveUser(user2);

        List<User> users = userDao.getAllUsers();

        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("OldName");
        user.setEmail("old@example.com");
        user.setAge(10);
        userDao.saveUser(user);

        user.setName("NewName");
        userDao.updateUser(user);

        User updatedUser = userDao.getUser(user.getId());
        assertEquals("NewName", updatedUser.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("ToDelete");
        user.setEmail("delete@example.com");
        user.setAge(100);
        userDao.saveUser(user);

        int userId = user.getId();
        userDao.deleteUser(userId);

        User deletedUser = userDao.getUser(userId);
        assertNull(deletedUser);
    }
}
