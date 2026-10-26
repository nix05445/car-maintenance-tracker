package com.example.car_maintenance_tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ServiceManager {

    private final DatabaseManager databaseManager;

    public ServiceManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void addEntry(ServiceEntry entry) {

        String sql = """
                INSERT INTO service_entries
                (date, work_done, mileage, category, cost)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, entry.getDate());
            statement.setString(2, entry.getWorkDone());
            statement.setInt(3, entry.getMileage());
            statement.setString(4, entry.getCategory());
            statement.setDouble(5, entry.getCost());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not save service entry.");
            e.printStackTrace();
        }
    }

    public List<ServiceEntry> getEntries() {

        List<ServiceEntry> entries = new ArrayList<>();

        String sql = """
                SELECT id, date, work_done, mileage, category, cost
                FROM service_entries
                ORDER BY mileage DESC
                """;

        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet results = statement.executeQuery()) {

            while (results.next()) {

                ServiceEntry entry = new ServiceEntry(
                        results.getInt("id"),
                        results.getString("date"),
                        results.getString("work_done"),
                        results.getInt("mileage"),
                        results.getString("category"),
                        results.getDouble("cost")
                );
                entries.add(entry);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve service entries.");
            e.printStackTrace();
        }
        return entries;
    }

    public List<ServiceEntry> getEntriesByCategory(String category) {

        List<ServiceEntry> entries = new ArrayList<>();
    
        String sql = """
                SELECT id, date, work_done, mileage, category, cost
                FROM service_entries
                WHERE category = ?
                ORDER BY mileage DESC
                """;
    
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, category);
    
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    ServiceEntry entry = new ServiceEntry(
                            results.getInt("id"),
                            results.getString("date"),
                            results.getString("work_done"),
                            results.getInt("mileage"),
                            results.getString("category"),
                            results.getDouble("cost")
                    );
                    entries.add(entry);
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve filtered service entries.");
            e.printStackTrace();
        }
        return entries;
    }

    public void deleteEntry(int id) {

        String sql = """
                DELETE FROM service_entries
                WHERE id = ?
                """;
    
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, id);
            statement.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println("Could not delete service entry.");
            e.printStackTrace();
        }
    }

    public ServiceEntry getEntryById(int id) {

        String sql = """
                SELECT id, date, work_done, mileage, category, cost
                FROM service_entries
                WHERE id = ?
                """;
    
        try (Connection connection = databaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setInt(1, id);
    
            try (ResultSet results = statement.executeQuery()) {
                if (results.next()) {
                    return new ServiceEntry(
                            results.getInt("id"),
                            results.getString("date"),
                            results.getString("work_done"),
                            results.getInt("mileage"),
                            results.getString("category"),
                            results.getDouble("cost")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Could not find service entry.");
            e.printStackTrace();
        }
        return null;
    }
    
    public void updateEntry(ServiceEntry entry) {
    
        String sql = """
                UPDATE service_entries
                SET date = ?,
                    work_done = ?,
                    mileage = ?,
                    category = ?,
                    cost = ?
                WHERE id = ?
                """;
    
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, entry.getDate());
            statement.setString(2, entry.getWorkDone());
            statement.setInt(3, entry.getMileage());
            statement.setString(4, entry.getCategory());
            statement.setDouble(5, entry.getCost());
            statement.setInt(6, entry.getId());
    
            statement.executeUpdate();
    
        } catch (SQLException e) {
            System.out.println("Could not update service entry.");
            e.printStackTrace();
        }
    }
}