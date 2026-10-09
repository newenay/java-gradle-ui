package com.example;
import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://[::1]:5432/sonarqube";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to the database!");
            // Perform database operations here
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
