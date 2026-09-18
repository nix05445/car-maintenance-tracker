package com.example.car_maintenance_tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:car-maintenance.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}