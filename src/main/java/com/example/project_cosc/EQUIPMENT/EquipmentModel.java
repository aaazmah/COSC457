package com.example.project_cosc.EQUIPMENT;

public class EquipmentModel {
    String model, brand , year, name, functional, fueltype , required;
    int id;

    public EquipmentModel(String model, String brand, String year, String name, String functional, String fueltype, String required, int id) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.name = name;
        this.functional = functional;
        this.fueltype = fueltype;
        this.required = required;
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
