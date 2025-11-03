package org.example.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void delByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public Optional<UserEntity> updateUserByEmail(String email, UserEntity newData) {

        Optional<UserEntity> existingUserOpt = userRepository.findByEmail(email);
        if (existingUserOpt.isEmpty()) {
            return Optional.empty();
        }
        UserEntity existingUser = existingUserOpt.get();

        existingUser.setName(newData.getName());
        existingUser.setEmail(newData.getEmail());
        existingUser.setAge(newData.getAge());

        userRepository.save(existingUser);
        return Optional.of(existingUser);
    }


}
