package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/eradb";
    private static final String USER = "erauser";
    private static final String PASSWORD = "erapassword";

    private static Connection connection;

    // Метод для получения подключения
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    // Приватный метод для создания подключения
    private static void connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("✅ Подключение к базе данных успешно установлено.");
    }

    // Метод для закрытия подключения
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 Подключение к базе данных закрыто.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
