package com.myroblox.userservice.service;

import com.myroblox.userservice.exception.UserServiceException;
import com.myroblox.userservice.model.User;
import com.myroblox.userservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {

        if (user.getUserLevel() == null) {
            user.setUserLevel(1);
        }

        if (user.getExperience() == null) {
            user.setExperience(0);
        }

        if (user.getRobux() == null) {
            user.setRobux(0.0);
        }

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException(HttpStatus.NOT_FOUND,
                        "Usuário com id: " + id + " não encontrado"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserServiceException(HttpStatus.NOT_FOUND,
                        "Usuário com nome: " + username + " não encontrado"));
    }

    public User update(User user, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserServiceException(HttpStatus.NOT_FOUND,
                        "Usuário com id: " + id + " não encontrado"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setDisplayName(user.getDisplayName());
        existingUser.setCountry(user.getCountry());
        existingUser.setUserLevel(user.getUserLevel());
        existingUser.setExperience(user.getExperience());
        existingUser.setRobux(user.getRobux());
        existingUser.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
