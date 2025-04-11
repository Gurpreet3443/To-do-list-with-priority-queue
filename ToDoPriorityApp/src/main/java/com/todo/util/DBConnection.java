package com.todo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        try {
            // Register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Handle the error if the driver class is not found
            e.printStackTrace();
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        // Database connection URL
        String url = "jdbc:mysql://localhost:3306/todo_db";
        // Database credentials
        String user = "root";
        String password = "Gurpreet3443B#";

        // Establish and return the database connection
        return DriverManager.getConnection(url, user, password);
    }
}
