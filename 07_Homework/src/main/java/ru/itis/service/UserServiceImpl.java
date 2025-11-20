package ru.itis.service;

import lombok.RequiredArgsConstructor;
import ru.itis.converter.UserToUserEntityConverter;
import ru.itis.dao.UsersData;
import ru.itis.entities.UserEntity;
import ru.itis.model.User;
import ru.itis.util.HashUtil;

import java.sql.SQLException;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersData usersData;
    private final UserToUserEntityConverter converter;

    public User authenticateUser(String username, String password) {
        try {
            UserEntity userEntity = UsersData.findUserByUsername(username);

            if (userEntity != null && HashUtil.checkPassword(password, userEntity.getHashPassword())) {
                return converter.convertToModel(userEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка аутентификации", e);
        }
        return null;
    }

    @Override
    public void saveNewUser(String username, String password) throws IllegalAccessException {
        try {
            UserEntity haveUser = usersData.findUserByUsername(username);
            if (haveUser != null) {
                throw new IllegalAccessException("Это имя пользователя уже занято");
            }

            String hashPassword = HashUtil.hashPassword(password);
            usersData.createUser(username, hashPassword, "Пользователь");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка создания пользователя", e);
        }
    }

//    @Override
//    public void changeUsername(User user, String newUsername) {
//    }
}
