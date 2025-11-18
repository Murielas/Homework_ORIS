package ru.itis.dao;

import ru.itis.entities.UserEntity;

import java.sql.*;

public class UsersData {
    public static void createUser(String username, String password, String role) throws SQLException {
        String sql = "insert into users (username, hash_password, role) values (?, ?, ?)";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка создания категории");
        }
    }

    public static void removeUser(String username) throws SQLException {
        String sql = "delete from users where username = ?";
        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибкa удаления юзера");
        }
    }

    public static UserEntity findUserByUsername(String username) throws SQLException {
        String sql = "select * from users where username = ?";

        try (Connection connection = DataManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRStoUserEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println();
        }
        return null;
    }

    private static UserEntity mapRStoUserEntity(ResultSet resultSet) throws SQLException {
        UserEntity userEntity = UserEntity.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .hashPassword(resultSet.getString("hash_password"))
                .role(resultSet.getString("role"))
                .build();
        return userEntity;
    }

}
