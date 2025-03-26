package com.qaa.module3.unit_testing_exercises.exercise3;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserRepository userRepository;
    private UserController userController;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userController = new UserController(userRepository);
    }

    @AfterEach
    void tearDown() {
        userRepository = null;
        userController = null;
    }

    @Test
    void register_UserIsNull_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(null);
        });
        assertEquals("User must not be null", exception.getMessage());
    }

    @Test
    void register_UsernameIsNull_ThrowsException() {
        User user = new User(1, null, "Password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Username must not be null", exception.getMessage());
    }

    @Test
    void register_UsernameIsEmpty_ThrowsException() {
        User user = new User(1, "   ", "Password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Username must not be whitespace only", exception.getMessage());
    }

    @Test
    void register_PasswordIsNull_ThrowsException() {
        User user = new User(1, "username", null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must not be null", exception.getMessage());
    }

    @Test
    void register_PasswordIsEmpty_ThrowsException() {
        User user = new User(1, "username", "   ");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must not be whitespace only", exception.getMessage());
    }

    @Test
    void register_UsernameTooShort_ThrowsException() {
        User user = new User(1, "usr", "Password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Username must contain at least 4 characters", exception.getMessage());
    }

    @Test
    void register_UsernameAlreadyExists_ThrowsException() {
        User user = new User(1, "username", "Password1");
        when(userRepository.exists("username")).thenReturn(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void register_PasswordTooShort_ThrowsException() {
        User user = new User(1, "username", "Pass1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must contain at least 6 characters", exception.getMessage());
    }

    @Test
    void register_PasswordNoUppercase_ThrowsException() {
        User user = new User(1, "username", "password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must contain at least 1 uppercase character", exception.getMessage());
    }

    @Test
    void register_PasswordNoLowercase_ThrowsException() {
        User user = new User(1, "username", "PASSWORD1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must contain at least 1 lowercase character", exception.getMessage());
    }

    @Test
    void register_PasswordNoNumber_ThrowsException() {
        User user = new User(1, "username", "Password");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.register(user);
        });
        assertEquals("Password must contain at least 1 number character", exception.getMessage());
    }

    @Test
    void register_ValidUser_ReturnsUser() {
        User user = new User(1, "username", "Password1");
        when(userRepository.exists("username")).thenReturn(false);
        when(userRepository.register(user)).thenReturn(user);
        User registeredUser = userController.register(user);
        assertEquals(user, registeredUser);
    }

    @Test
    void login_UserIsNull_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.login(null);
        });
        assertEquals("User must not be null", exception.getMessage());
    }

    @Test
    void login_UsernameOrPasswordIsNull_ThrowsException() {
        final User user = new User(1, null, "Password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.login(user);
        });
        assertEquals("Username and password must not be null", exception.getMessage());

        final User user2 = new User(1, "username", null);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.login(user2);
        });
        assertEquals("Username and password must not be null", exception.getMessage());
    }

    @Test
    void login_UsernameOrPasswordIsEmpty_ThrowsException() {
        final User user = new User(1, "", "Password1");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.login(user);
        });
        assertEquals("Username and password must not be empty", exception.getMessage());

        final User user2 = new User(1, "username", "");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            userController.login(user2);
        });
        assertEquals("Username and password must not be empty", exception.getMessage());
    }

    @Test
    void login_ValidUser_ReturnsUser() {
        User user = new User(1, "username", "Password1");
        when(userRepository.login(user)).thenReturn(user);
        User loggedInUser = userController.login(user);
        assertEquals(user, loggedInUser);
    }
}