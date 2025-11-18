package ru.itis.dao;

import ru.itis.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryData {
    public static void addCategoryToProduct(Long productId, Long categoryId) {
        String sql = "insert into product_categories (product_id, category_id) values (?, ?)";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, productId);
            statement.setLong(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка добавления связи");
        }
    }

    public static List<Product> findProductsByCategory(Long categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select p.* from product p " +
                "inner join product_categories pc on p.id = pc.product_id " +
                "where pc.category_id = ?";

        try (Connection connection = DataManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = mapRSToProduct(resultSet);
                products.add(product);
            }
        }
        return products;
    }

    private static Product mapRSToProduct(ResultSet resultSet) throws SQLException {
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
