package com.example.car_maintenance_tracker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DatabaseManager databaseManager;

    public DatabaseInitializer(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void run(String... args) {

        String sql = """
                CREATE TABLE IF NOT EXISTS service_entries (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date TEXT NOT NULL,
                    work_done TEXT NOT NULL,
                    mileage INTEGER NOT NULL,
                    category TEXT NOT NULL,
                    cost REAL NOT NULL
                )
                """;

        try (Connection connection = databaseManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Database table is ready.");

        } catch (SQLException e) {
            System.out.println("Could not create database table.");
            e.printStackTrace();
        }
    }
}