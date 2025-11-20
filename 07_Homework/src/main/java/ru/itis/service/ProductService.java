package ru.itis.service;

import ru.itis.dao.CategoryData;
import ru.itis.dao.DataManager;
import ru.itis.dao.ProductCategoryData;
import ru.itis.entities.CategoryEntity;
import ru.itis.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> getProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add(Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getDouble("price"))
                        .description(resultSet.getString("description"))
                        .photoPath(resultSet.getString("photo_path"))
                        .build());
            }
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e);
        }

        return products;
    }

    public static List<Product> getProductByCategory(String category) {
        try {
            CategoryEntity categoryEntity = CategoryData.findCategoryByName(category);
            return ProductCategoryData.findProductsByCategory(categoryEntity.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения товаров", e);
        }
    }
}

