package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
}
