package ru.itis.dao;

import ru.itis.entities.Product;

import java.sql.*;

public class ProductData {
    public static Long createProduct(String name, double price , String description, String photoPath) throws SQLException {
        String sql = "insert into product (name, price, description, photo_path) values (?, ?, ?, ?)";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setString(4, ("/src/main/webapp/images/" + photoPath));

            statement.executeUpdate();

            try (ResultSet generateKeys = statement.getGeneratedKeys()){
                if (generateKeys.next()) {
                    Long generatedId = generateKeys.getLong(1);
                    return generatedId;
                } else {
                    throw new SQLException("Ошибка: id не получен");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка создания товара");
            throw e;
        }
    }

    public static void removeProduct(Long productId) throws SQLException {
        String sql = "delete from product where id = ?";
        try (Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибкa удаления товара");
        }
    }

    public static void updateProduct(Long id, String name, double price , String description, String photoPath) throws SQLException {
        String sql = "update product set name = ?, price = ?, description = ?, photo_path = ? where id = ?";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, description);
            statement.setString(4, ("/src/main/webapp/images/" + photoPath));
            statement.setLong(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка изменения товара");
        }
    }

    public static Product findProductByName(String name) throws SQLException {
        String sql = "select * from product where name = ?";

        try (Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRStoProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println();
        }
        return null;
    }

    public static Product findProductById(Long id) throws SQLException {
        String sql = "select * from product where id = ?";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRStoProduct(resultSet);
                }
            }
        }
        return null;
    }

    private static Product mapRStoProduct(ResultSet resultSet) throws SQLException {
        Product product = Product.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("description"))
                .photoPath(resultSet.getString("photo_path"))
                .build();

        return product;
    }
}
