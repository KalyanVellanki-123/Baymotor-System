package models;

public class Vehicle {
    private String registrationNumber;
    private String make;
    private String model;

    public Vehicle(String registrationNumber, String make, String model) {
        this.registrationNumber = registrationNumber;
        this.make = make;
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
