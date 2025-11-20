package ru.itis.dao;

import java.sql.*;

public class DataManager {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка: " + e);
        }

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Everbloom",
                "postgres", "2006");
    }
}
