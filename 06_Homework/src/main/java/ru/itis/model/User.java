package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private String role;

    public User(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
