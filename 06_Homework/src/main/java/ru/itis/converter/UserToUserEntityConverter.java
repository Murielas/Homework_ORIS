package ru.itis.converter;

import ru.itis.entities.UserEntity;
import ru.itis.model.User;

public class UserToUserEntityConverter implements Converter<User, UserEntity> {
    @Override
    public UserEntity convertToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    @Override
    public User convertToModel(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .role(userEntity.getRole())
                .build();
    }
}
