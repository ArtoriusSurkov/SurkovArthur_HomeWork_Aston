package org.example.services;

import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testSave() {
        UserEntity user = new UserEntity();
        user.setName("Test");
        user.setEmail("test@example.com");
        user.setAge(25);

        userService.save(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetByEmail_Found() {
        String email = "test@example.com";
        UserEntity user = new UserEntity();
        user.setName("Test");
        user.setEmail(email);
        user.setAge(24);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<UserEntity> result = userService.getByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testGetByEmail_NotFound() {
        String email = "notfound@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<UserEntity> result = userService.getByEmail(email);

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testGetAll() {
        List<UserEntity> users = Arrays.asList(new UserEntity(), new UserEntity());
        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userService.getAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDelByEmail() {
        String email = "delete@example.com";

        userService.delByEmail(email);

        verify(userRepository, times(1)).deleteByEmail(email);
    }

    @Test
    public void testUpdateUserByEmail_UserExists() {
        String email = "exist@example.com";

        UserEntity existingUser = new UserEntity();
        existingUser.setEmail(email);
        existingUser.setName("Old Name");
        existingUser.setAge(30);

        UserEntity newData = new UserEntity();
        newData.setName("New Name");
        newData.setEmail("newemail@example.com");
        newData.setAge(35);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<UserEntity> resultOpt = userService.updateUserByEmail(email, newData);

        assertTrue(resultOpt.isPresent());
        UserEntity updatedUser = resultOpt.get();

        assertEquals("New Name", updatedUser.getName());
        assertEquals("newemail@example.com", updatedUser.getEmail());
        assertEquals(35, updatedUser.getAge());

        verify(userRepository).findByEmail(email);
        verify(userRepository).save(existingUser);
    }

    @Test
    public void testUpdateUserByEmail_UserNotFound() {
        String email = "notfound@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserEntity newData = new UserEntity();
        newData.setName("Name");
        newData.setEmail("email@example.com");
        newData.setAge(40);

        Optional<UserEntity> resultOpt = userService.updateUserByEmail(email, newData);

        assertTrue(resultOpt.isEmpty());
        verify(userRepository).findByEmail(email);
        verify(userRepository, never()).save(any());
    }
}