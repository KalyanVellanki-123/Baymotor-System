package models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String contactInfo;
    private boolean isRegistered;
    private List<Vehicle> vehicles; // List to hold multiple vehicles

  public Customer(String name, String contactInfo, boolean isRegistered, Vehicle vehicle) {
    if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (contactInfo == null || contactInfo.isEmpty()) {
        throw new IllegalArgumentException("Contact information cannot be null or empty");
    }
    this.name = name;
    this.contactInfo = contactInfo;
    this.isRegistered = isRegistered;
    this.vehicles = new ArrayList<>();
    if (vehicle != null) {
        this.vehicles.add(vehicle);
    }
}

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public boolean hasVehicle(String vehicleRegNo) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getRegistrationNumber().equalsIgnoreCase(vehicleRegNo)) {
                return true;
            }
        }
        return false;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void upgradeToRegistered() {
        this.isRegistered = true;
    }

    public void notify(String message) {
        System.out.println("Notification to " + name + " (" + contactInfo + "): " + message);
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', contactInfo='" + contactInfo + "', isRegistered=" + isRegistered + ", vehicles=" + vehicles + "}";
    }
}