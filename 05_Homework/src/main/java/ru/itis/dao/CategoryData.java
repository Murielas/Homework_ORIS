package ru.itis.dao;

import ru.itis.entities.CategoryEntity;

import java.sql.*;

public class CategoryData {
    public static void createCategory(String name) throws SQLException {
        String sql = "insert into category (name) values (?)";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка создания категории");
        }
    }

    public static void removeCategory(Long categoryId) {
        String sql = "delete from category where id = ?";
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибкa: " + e.getMessage());
        }
    }

    public static CategoryEntity findCategoryByName(String name) throws SQLException {
        String sql = "select * from category where name = ?";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRStoCategory(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println();
        }
        return null;
    }

    private static CategoryEntity mapRStoCategory(ResultSet resultSet) throws SQLException {
        CategoryEntity category = new CategoryEntity(resultSet.getLong("id"), resultSet.getString("name"));
        return category;
    }
}
