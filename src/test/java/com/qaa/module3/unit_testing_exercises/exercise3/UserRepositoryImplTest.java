package com.qaa.module3.unit_testing_exercises.exercise3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;
    private List<User> initialUsers;

    @BeforeEach
    void setUp() {
        initialUsers = new ArrayList<>();
        initialUsers.add(new User(1, "user1", "pass1"));
        initialUsers.add(new User(2, "user2", "pass2"));
        userRepository = new UserRepositoryImpl(initialUsers);
    }

    @AfterEach
    void tearDown() {
        userRepository = null;
    }

    @Test
    void exists() {
        assertTrue(userRepository.exists("user1"));
        assertFalse(userRepository.exists("nonexistent"));
    }

    @Test
    void register() {
        User newUser = new User(3, "user3", "pass3");
        User registeredUser = userRepository.register(newUser);
        assertEquals(newUser, registeredUser);
        assertTrue(userRepository.exists("user3"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.register(newUser);
        });
        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void login() {
        User user = new User(1, "user1", "pass1");
        User loggedInUser = userRepository.login(user);
        assertEquals(user, loggedInUser);

        User invalidUser = new User(1, "user1", "wrongpass");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.login(invalidUser);
        });
        assertEquals("Invalid username or password", exception.getMessage());
    }
}