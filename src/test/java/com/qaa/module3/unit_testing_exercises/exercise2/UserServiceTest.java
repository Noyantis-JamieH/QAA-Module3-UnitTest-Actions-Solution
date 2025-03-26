package com.qaa.module3.unit_testing_exercises.exercise2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @AfterEach
    void tearDown() {
        userService = null;
    }

    @Test
    void register_withValidData_shouldRegisterUser() {
        String username = "testUser";
        String password = "Password1";
        String result = userService.register(username, password);
        assertEquals(username, result);
    }

    @Test
    void register_withNullUsername_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register(null, "Password1");
        });
        assertEquals("Username must not be null", exception.getMessage());
    }

    @Test
    void register_withEmptyUsername_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("   ", "Password1");
        });
        assertEquals("Username must not be whitespace only", exception.getMessage());
    }

    @Test
    void register_withShortUsername_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("usr", "Password1");
        });
        assertEquals("Username must contain at least 4 characters", exception.getMessage());
    }

    @Test
    void register_withExistingUsername_shouldThrowException() {
        userService.register("testUser", "Password1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "Password2");
        });
        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void register_withNullPassword_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", null);
        });
        assertEquals("Password must not be null", exception.getMessage());
    }

    @Test
    void register_withEmptyPassword_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "   ");
        });
        assertEquals("Password must not be whitespace only", exception.getMessage());
    }

    @Test
    void register_withShortPassword_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "Pass1");
        });
        assertEquals("Password must contain at least 6 characters", exception.getMessage());
    }

    @Test
    void register_withPasswordWithoutUppercase_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "password1");
        });
        assertEquals("Password must contain at least 1 uppercase character", exception.getMessage());
    }

    @Test
    void register_withPasswordWithoutLowercase_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "PASSWORD1");
        });
        assertEquals("Password must contain at least 1 lowercase character", exception.getMessage());
    }

    @Test
    void register_withPasswordWithoutNumber_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.register("testUser", "Password");
        });
        assertEquals("Password must contain at least 1 number character", exception.getMessage());
    }

    @Test
    void login_withValidData_shouldLoginUser() {
        String username = "testUser";
        String password = "Password1";
        userService.register(username, password);
        String result = userService.login(username, password);
        assertEquals(username, result);
    }

    @Test
    void login_withInvalidUsername_shouldThrowException() {
        userService.register("testUser", "Password1");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.login("invalidUser", "Password1");
        });
        assertEquals("Invalid username supplied", exception.getMessage());
    }

    @Test
    void login_withInvalidPassword_shouldThrowException() {
        userService.register("testUser", "Password1");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.login("testUser", "InvalidPassword");
        });
        assertEquals("Invalid password supplied", exception.getMessage());
    }
}