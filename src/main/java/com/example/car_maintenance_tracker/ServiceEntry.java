package com.example.car_maintenance_tracker;

public class ServiceEntry {

    private int id;
    private String date;
    private String workDone;
    private int mileage;
    private String category;
    private double cost;

    public ServiceEntry(int id, String date, String workDone, int mileage,
                        String category, double cost) {

        this.id = id;
        this.date = date;
        this.workDone = workDone;
        this.mileage = mileage;
        this.category = category;
        this.cost = cost;
    }

    public ServiceEntry(String date, String workDone, int mileage,
                        String category, double cost) {

        this.date = date;
        this.workDone = workDone;
        this.mileage = mileage;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getWorkDone() {
        return workDone;
    }

    public int getMileage() {
        return mileage;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }
}