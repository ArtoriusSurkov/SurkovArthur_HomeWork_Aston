package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.UserDto;
import org.example.entities.UserEntity;
import org.example.services.UserService;
import org.example.utils.MappingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private MappingUtils mappingUtils;

    @GetMapping
    public List<UserDto> getAllUser() {
        return userService.getAll().stream().map(ex -> mappingUtils.mapToUserDto(ex)).toList();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return userService.getByEmail(email).map(user -> ResponseEntity.ok(mappingUtils.mapToUserDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserEntity entity = mappingUtils.mapToUSerEntity(userDto);
        userService.save(entity);
        return ResponseEntity.status(201).body(mappingUtils.mapToUserDto(entity));
    }

    @PostMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        UserEntity entity = mappingUtils.mapToUSerEntity(userDto);
        Optional<UserEntity> update = userService.updateUserByEmail(email, entity);
        return update.map(updateEntity -> ResponseEntity.ok(mappingUtils.mapToUserDto(updateEntity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.delByEmail(email);
        return ResponseEntity.noContent().build();
    }

}