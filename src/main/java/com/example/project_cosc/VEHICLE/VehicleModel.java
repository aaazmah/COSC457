package com.example.project_cosc.VEHICLE;

public class VehicleModel {
    String brand, model, fueltype, name, functional;
    int id;

    public VehicleModel(String brand, String model, String fueltype, String name, String functional, int id) {
        this.brand = brand;
        this.model = model;
        this.fueltype = fueltype;
        this.name = name;
        this.functional = functional;
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctional() {
        return functional;
    }

    public void setFunctional(String functional) {
        this.functional = functional;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
