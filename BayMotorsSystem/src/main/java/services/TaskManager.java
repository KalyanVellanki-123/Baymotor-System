package services;

import models.Task;
import models.Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import models.Vehicle;

public class TaskManager {
    private List<Task> tasks;
    private CustomerManager customerManager;

    public TaskManager(CustomerManager customerManager) {
        this.tasks = new ArrayList<>();
        this.customerManager = customerManager;
    }

    public void addTask(Task task) {
        Customer customer = customerManager.findCustomerByVehicle(task.getVehicleRegNo());
        if (customer == null) {
            throw new IllegalArgumentException("Invalid vehicle registration. Task not added.");
        }
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasksByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
        return tasks;
    }

    public Task findTaskByDescription(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getCompletedTasks() {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public void addVehicle(Vehicle vehicle) {
    if (vehicle == null) {
        throw new IllegalArgumentException("Cannot add null vehicle");
    }
    
    // Find the customer with this vehicle
    Customer customer = customerManager.findCustomerByVehicle(vehicle.getRegistrationNumber());
    
    if (customer == null) {
        // If no customer found, you might want to add the vehicle to a new or existing customer
        // For this example, I'll throw an exception, but you could modify this logic
        throw new IllegalArgumentException("No customer found for this vehicle");
    }
    
    // Add the vehicle to the customer
    customerManager.addVehicleToCustomer(
        vehicle.getRegistrationNumber(), 
        vehicle.getMake(), 
        vehicle.getModel(), 
        customer.getName()
    );
    }
}