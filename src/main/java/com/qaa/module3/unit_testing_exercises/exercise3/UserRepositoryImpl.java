package com.qaa.module3.unit_testing_exercises.exercise3;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private List<User> users;

    public UserRepositoryImpl() {
        this.users = List.of();
    }

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean exists(String trimmedUsername) {
        return users.stream().anyMatch(user -> user.getUsername().equals(trimmedUsername));
    }

    @Override
    public User register(User user) {
        if (exists(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        users.add(user);
        return user;
    }

    @Override
    public User login(User user) {
        return users.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }
}
