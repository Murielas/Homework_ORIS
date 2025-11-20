package ru.itis.entities;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String hashPassword;
    private String role;
}