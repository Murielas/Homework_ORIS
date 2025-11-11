package ru.itis.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryEntity {
    private Long id;
    private String name;

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
