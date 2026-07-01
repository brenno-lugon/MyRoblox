package com.myroblox.userservice.controller;

import com.myroblox.userservice.model.User;
import com.myroblox.userservice.repository.UserRepository;
import com.myroblox.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        User user = new User();
        user.setUsername("buffy_summers");
        user.setEmail("buffysummers@email.com");
        user.setDisplayName("Buffy Summers");
        user.setCountry("United States of America");

        User created = userService.create(user);

        assertNotNull(created.getId());
        assertEquals("buffy_summers", created.getUsername());
        assertEquals("buffysummers@email.com", created.getEmail());
        assertEquals("Buffy Summers", created.getDisplayName());
        assertEquals("United States of America", created.getCountry());
        assertEquals(1, created.getUserLevel());
        assertEquals(0, created.getExperience());
        assertEquals(0.0, created.getRobux());
        assertNotNull(created.getCreatedAt());
        assertNotNull(created.getUpdatedAt());
    }

    @Test
    void shouldFindAllUsers() {
        userRepository.save(createUser("user1", "user1@email.com"));
        userRepository.save(createUser("user2", "user2@email.com"));

        var users = userService.findAll();

        assertFalse(users.isEmpty());
    }

    @Test
    void shouldFindUserById() {
        User saved = userRepository.save(createUser("buffy_id", "buffy_id@email.com"));

        User found = userService.findById(saved.getId());

        assertEquals(saved.getId(), found.getId());
        assertEquals("buffy_id", found.getUsername());
    }

    @Test
    void shouldFindUserByUsername() {
        userRepository.save(createUser("buffy_search", "buffy_search@email.com"));

        User found = userService.findByUsername("buffy_search");

        assertEquals("buffy_search", found.getUsername());
        assertEquals("buffy_search@email.com", found.getEmail());
    }

    @Test
    void shouldUpdateUser() {
        User saved = userRepository.save(createUser("old_user", "old@email.com"));

        User updateData = new User();
        updateData.setUsername("new_user");
        updateData.setEmail("new@email.com");
        updateData.setDisplayName("New User");
        updateData.setCountry("USA");
        updateData.setUserLevel(5);
        updateData.setExperience(100);
        updateData.setRobux(50.0);

        User updated = userService.update(updateData, saved.getId());

        assertEquals(saved.getId(), updated.getId());
        assertEquals("new_user", updated.getUsername());
        assertEquals("new@email.com", updated.getEmail());
        assertEquals("New User", updated.getDisplayName());
        assertEquals("USA", updated.getCountry());
        assertEquals(5, updated.getUserLevel());
        assertEquals(100, updated.getExperience());
        assertEquals(50.0, updated.getRobux());
        assertNotNull(updated.getUpdatedAt());
    }

    @Test
    void shouldDeleteUser() {
        User saved = userRepository.save(createUser("delete_user", "delete@email.com"));

        userService.deleteById(saved.getId());

        assertFalse(userRepository.existsById(saved.getId()));
    }

    private User createUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setDisplayName("Test User");
        user.setCountry("Brazil");
        user.setUserLevel(1);
        user.setExperience(0);
        user.setRobux(0.0);
        return user;
    }
}
