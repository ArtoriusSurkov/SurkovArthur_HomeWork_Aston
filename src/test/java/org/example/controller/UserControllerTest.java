package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.controller.UserController;
import org.example.dto.UserDto;
import org.example.entities.UserEntity;
import org.example.services.UserService;
import org.example.utils.MappingUtils;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MappingUtils mappingUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllUser() throws Exception {
        UserEntity user1 = new UserEntity("name1", "name1@gmail.com",18);
        UserEntity user2 = new UserEntity("name2", "name2@gmail.com",18);

        UserDto dto1 = new UserDto("name1", "name1@gmail.com",18);
        UserDto dto2 = new UserDto("name2", "name2@gmail.com",18);

        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));
        when(mappingUtils.mapToUserDto(user1)).thenReturn(dto1);
        when(mappingUtils.mapToUserDto(user2)).thenReturn(dto2);

        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetByEmail_Found() throws Exception {
        String email = "test@example.com";

        UserEntity user = new UserEntity("name1", "test1@example.com",18);
        UserDto dto = new UserDto("name1", "test@example.com",18);

        when(userService.getByEmail(email)).thenReturn(Optional.of(user));
        when(mappingUtils.mapToUserDto(user)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/users/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void testGetByEmail_NotFound() throws Exception {
        String email = "unknown@example.com";

        when(userService.getByEmail(email)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/{email}", email))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUser() throws Exception {
        String email = "unknown@example.com";
        UserDto userDto = new UserDto("name2", "unknown@example.com",18);
        UserEntity entity = new UserEntity("name2", "unknown@example.com",18);
        UserDto responseDto = new UserDto("name2", "unknown@example.com",18);

        when(mappingUtils.mapToUSerEntity(any(UserDto.class))).thenReturn(entity);
        when(mappingUtils.mapToUserDto(entity)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").exists());
    }

    @Test
    public void testUpdateUser_Found() throws Exception {
        String email = "test@example.com";

        UserDto userDto = new UserDto("old", "old@example.com",18);
        UserEntity entity = new UserEntity("old", "old@example.com",18);
        UserEntity updatedEntity = new UserEntity("new", "test@example.com",20);
        UserDto responseDto = new UserDto("new", "test@example.com",20);

        when(mappingUtils.mapToUSerEntity(any(UserDto.class))).thenReturn(entity);
        when(userService.updateUserByEmail(eq(email), any(UserEntity.class))).thenReturn(Optional.of(updatedEntity));
        when(mappingUtils.mapToUserDto(updatedEntity)).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/users/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void testUpdateUser_NotFound() throws Exception {
        String email = "unknown@example.com";

        UserDto userDto = new UserDto("one", "one@example.com",18);
        when(mappingUtils.mapToUSerEntity(any(UserDto.class))).thenReturn(new UserEntity());
        when(userService.updateUserByEmail(eq(email), any(UserEntity.class))).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/users/{email}", email)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteUser() throws Exception {
        String email = "test@example.com";

        doNothing().when(userService).delByEmail(email);

        mockMvc.perform(delete("/api/v1/users/{email}", email))
                .andExpect(status().isNoContent());

        verify(userService).delByEmail(email);
    }
}