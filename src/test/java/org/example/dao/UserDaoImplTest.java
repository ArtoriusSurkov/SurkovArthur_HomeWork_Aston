package org.example.dao;

import org.example.entity.User;
import org.example.utils.HibernateSessionFactoryUtil;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDaoImplTest {

    private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>()
            .withDatabaseName("example")
            .withUsername("postgres")
            .withPassword("1234");

    private static SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private UserDaoImpl userDao;


    @AfterAll
    public static void tearDownContainer() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        postgresContainer.stop();
    }

    @BeforeEach
    public void setup() {
        userDao = new UserDaoImpl();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
    }

    @Test
    public void testSaveAndGetUser() {
        User user = new User();
        user.setName("TestUser");
        user.setEmail("testuser@example.com");
        user.setAge(18);
        userDao.saveUser(user);
        assertNotNull(user.getId());

        User updatedUser = userDao.getUser(user.getId());
        assertNotNull(updatedUser);
        assertEquals("TestUser", updatedUser.getName());
        assertEquals("testuser@example.com", updatedUser.getEmail());
        assertEquals(18, updatedUser.getAge());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setName("Ivan");
        user1.setEmail("ivan@example.com");
        user1.setAge(20);
        userDao.saveUser(user1);

        User user2 = new User();
        user2.setName("Anna");
        user2.setEmail("anna@example.com");
        user2.setAge(25);
        userDao.saveUser(user2);

        List<User> users = userDao.getAllUsers();
        assertEquals(2, users.size());
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setName("Original Name");
        user.setEmail("original@example.com");
        user.setAge(12);
        userDao.saveUser(user);

        user.setName("Updated Name");
        user.setEmail("updated@example.com");
        user.setAge(22);
        userDao.updateUser(user);

        User updatedUser = userDao.getUser(user.getId());

        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals(22,updatedUser.getAge());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setName("To Be Deleted");
        user.setEmail("delete@example.com");
        userDao.saveUser(user);

        int userId = user.getId();

        userDao.deleteUser(userId);

        User deletedUser = userDao.getUser(userId);

        assertNull(deletedUser);
    }

    @Test
    public void testDeleteUserNotFound() {
        userDao.deleteUser(9999);
        User deletedUser = userDao.getUser(9999);
        assertNull(deletedUser);
    }
}