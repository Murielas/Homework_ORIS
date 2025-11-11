package ru.itis.converter;

import ru.itis.entities.UserEntity;
import ru.itis.model.User;

public class UserConverter implements Converter<User, UserEntity> {
    @Override
    public UserEntity convert(User user) {
        return UserEntity.builder().
                id(user.getId())
                .username(user.getUsername())
                .hashPassword(user.getPassword())
                .build();
    }
}
