package services;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

  
    public Customer findCustomerByVehicle(String vehicleRegNo) {
        for (Customer customer : customers) {
            if (customer.getVehicleRegNo().equalsIgnoreCase(vehicleRegNo)) {
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
}
