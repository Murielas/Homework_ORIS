package ru.itis.entities;

import lombok.*;

@Getter
@Setter
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String hashPassword;
    private String role;

    public UserEntity(Long id, String username, String email, String hashPassword, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashPassword = hashPassword;
        this.role = role;
    }
}