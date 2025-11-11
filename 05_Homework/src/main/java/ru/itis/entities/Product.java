package ru.itis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private double price;
    private String description;
    private String photoPath;

    // пока остаётся, потому что я ещё не поменяла логику корзины
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
