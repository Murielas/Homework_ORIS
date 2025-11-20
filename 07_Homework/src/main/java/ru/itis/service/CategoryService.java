package ru.itis.service;

import ru.itis.dao.DataManager;
import ru.itis.entities.CategoryEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    public static List<CategoryEntity> getCategories() throws SQLException {
        List<CategoryEntity> categories = new ArrayList<>();
        String sql = "select id, name from category";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                categories.add(new CategoryEntity(
                        resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e);
        }

        return categories;
    }
}
