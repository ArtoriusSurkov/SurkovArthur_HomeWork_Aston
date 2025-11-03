package org.example.utils;

import org.example.dto.UserDto;
import org.example.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MappingUtilsTest {

    private MappingUtils mappingUtils;

    @BeforeEach
    public void setUp() {
        mappingUtils = new MappingUtils();
    }

    @Test
    public void testMapToUserDto() {
        UserEntity user = new UserEntity();
        user.setName("Test Name");
        user.setEmail("test@example.com");
        user.setAge(25);

        UserDto dto = mappingUtils.mapToUserDto(user);

        assertNotNull(dto);
        assertEquals(user.getName(), dto.getName());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getAge(), dto.getAge());
    }

    @Test
    public void testMapToUserEntity() {
        UserDto dto = new UserDto();
        dto.setName("Another Name");
        dto.setEmail("another@example.com");
        dto.setAge(30);

        UserEntity entity = mappingUtils.mapToUSerEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getAge(), entity.getAge());
    }
}