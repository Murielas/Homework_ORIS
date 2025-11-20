package ru.itis.service;

import ru.itis.entities.UserEntity;
import ru.itis.model.User;

public interface UserService {
    User authenticateUser(String username, String password);

    void saveNewUser(String username, String password) throws IllegalAccessException;

//  void changeUsername(User user, String newUsername);
}