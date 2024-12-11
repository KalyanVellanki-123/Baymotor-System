package services;

import models.Customer;
import models.Vehicle;
import models.Supplier;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers = new ArrayList<>();

    // New method to add a customer with a vehicle
    public void addCustomer(String name, String contactInfo, String vehicleRegNo, String make, String model) {
    if (name == null || name.isEmpty() || contactInfo == null || contactInfo.isEmpty()) {
        System.out.println("Invalid customer details. Customer not added.");
        return;
    }

    // Create a new vehicle
    Vehicle vehicle = new Vehicle(vehicleRegNo, make, model);

    // Create a new customer with the vehicle
    Customer customer = new Customer(name, contactInfo, false, vehicle);

    // Add the customer to the list
    customers.add(customer);
    System.out.println("Customer added successfully: " + customer);
}

    public void addVehicleToCustomer(String vehicleRegNo, String make, String model, String customerName) {
        Customer customer = findCustomerByName(customerName);
        if (customer != null) {
            Vehicle vehicle = new Vehicle(vehicleRegNo, make, model);
            customer.addVehicle(vehicle);
            System.out.println("Vehicle added successfully to customer: " + customerName);
        } else {
            System.out.println("Customer not found: " + customerName);
        }
    }

    public Customer findCustomerByVehicle(String vehicleRegNo) {
        for (Customer customer : customers) {
            if (customer.hasVehicle(vehicleRegNo)) {
                return customer;
            }
        }
        return null;
    }

    public Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

    public void notifyRegisteredCustomers(String message) {
        for (Customer customer : customers) {
            if (customer.isRegistered()) {
                customer.notify(message);
            }
        }
    }

    public void notifyUnregisteredCustomers(String message) {
        for (Customer customer : customers) {
            if (!customer.isRegistered()) {
                customer.notify(message);
            }
        }
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public List<Customer> getRegisteredCustomers() {
        List<Customer> registeredCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isRegistered()) {
                registeredCustomers.add(customer);
            }
        }
        return registeredCustomers;
    }

    public List<Customer> getUnregisteredCustomers() {
        List<Customer> unregisteredCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (!customer.isRegistered()) {
                unregisteredCustomers.add(customer);
            }
        }
        return unregisteredCustomers;
    }

    private List<Supplier> suppliers = new ArrayList<>();

public void addSupplier(Supplier supplier) {
    if (supplier != null) {
        suppliers.add(supplier);
        System.out.println("Supplier added successfully: " + supplier);
    } else {
        System.out.println("Invalid supplier. Cannot add null supplier.");
    }
 // Placeholder for future implementation
    
}
}