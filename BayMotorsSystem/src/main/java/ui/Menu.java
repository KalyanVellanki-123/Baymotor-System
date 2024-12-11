package ui;

import java.util.InputMismatchException;
import java.util.List;
import models.*;
import services.*;
import java.util.Scanner;

public class Menu {
    private CustomerManager customerManager;
    private TaskManager taskManager;
    private User currentUser;

   public Menu() {
    customerManager = new CustomerManager();
    taskManager = new TaskManager(customerManager); // Pass the CustomerManager instance to TaskManager
}


    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        showMainMenu(scanner);  
    }

    public void showMainMenu(Scanner scanner) {
        while (true) { 
            System.out.println("\nWelcome to Bay Motors System");
            System.out.println("1. Manager");
            System.out.println("2. Mechanic");
            System.out.println("3. Exit");
            System.out.print("Choose your role (1 for Manager, 2 for Mechanic, 3 to Exit): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                currentUser = new Manager("Manager");
                runMenu(scanner); 
            } else if (choice.equals("2")) {
                currentUser = new Mechanic("Mechanic");
                runMenu(scanner); 
            } else if (choice.equals("3")) {
                System.out.println("Exiting the system. Goodbye!");
                break; 
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   private void runMenu(Scanner scanner) {
    while (true) {
        if (currentUser == null) { 
            System.out.println("Error: No user logged in.");
            break;
        }

        currentUser.displayMenu();
        System.out.print("Choose an option: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            continue;
        }

        if (currentUser instanceof Manager) {
            if (!handleManagerOptions(choice, scanner)) {
                break; 
            }
        } else if (currentUser instanceof Mechanic) {
            if (!handleMechanicOptions(choice, scanner)) {
                break;
            }
        }
    }
}


    private boolean handleManagerOptions(int choice, Scanner scanner) {
    switch (choice) {
        case 1 -> addCustomer(scanner);
        case 2 -> upgradeCustomer(scanner);
        case 3 -> addVehicle(scanner);
        case 4 -> allocateTask(scanner);
        case 5 -> addManufacturerOrSupplier(scanner);
        case 6 -> {
    exit(false); // Return to the main menu
    return false; // Exit Manager menu loop
}

        default -> System.out.println("Invalid option.");
    }
    return true; // Continue showing the menu if no exit option is selected
}

private boolean handleMechanicOptions(int choice, Scanner scanner) {
    switch (choice) {
        case 1 -> viewTasks();
        case 2 -> markTaskCompleted(scanner);
        case 3 -> addManufacturerOrSupplier(scanner);
        case 4 -> {
    exit(false); // Return to the main menu
    return false; // Exit Mechanic menu loop
}

        default -> System.out.println("Invalid option.");
    }
    return true; // Continue showing the menu if no exit option is selected
}

  private void addCustomer(Scanner scanner) {
    System.out.print("Enter customer name: ");
    String name = scanner.nextLine().trim();
    System.out.print("Enter contact information: ");
    String contactInfo = scanner.nextLine().trim();
    System.out.print("Enter vehicle registration number: ");
    String vehicleRegNo = scanner.nextLine().trim();
    System.out.print("Enter vehicle make: ");
    String make = scanner.nextLine().trim();
    System.out.print("Enter vehicle model: ");
    String model = scanner.nextLine().trim();

    // Validate input fields
    if (name.isEmpty() || contactInfo.isEmpty() || vehicleRegNo.isEmpty() || make.isEmpty() || model.isEmpty()) {
        System.out.println("All fields are required. Please fill in all details.");
        return;
    }

    // Add the customer to the customerManager with vehicle details
    customerManager.addCustomer(name, contactInfo, vehicleRegNo, make, model);

}
    private void upgradeCustomer(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        Customer customer = customerManager.findCustomerByName(name);
        if (customer != null && !customer.isRegistered()) {
            customer.upgradeToRegistered();
            System.out.println("Customer upgraded to registered.");
        } else {
            System.out.println("Customer not found or already registered.");
        }
    }

    private void addVehicle(Scanner scanner) {
        System.out.print("Enter vehicle registration number: ");
        String regNo = scanner.nextLine();
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.println("Vehicle added successfully.");
    }

private void allocateTask(Scanner scanner) {
    System.out.print("Enter task description: ");
    String description = scanner.nextLine().trim();
    if (description.isEmpty()) {
        System.out.println("Task description cannot be empty.");
        return;
    }

    int priority;
    try {
        System.out.print("Enter priority (1-High, 2-Medium, 3-Low): ");
        priority = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (priority < 1 || priority > 3) {
            System.out.println("Priority must be 1 (High), 2 (Medium), or 3 (Low).");
            return;
        }
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Priority must be a number.");
        scanner.nextLine(); // Clear the invalid input
        return;
    }

    System.out.print("Enter vehicle registration number: ");
    String vehicleRegNo = scanner.nextLine().trim();
    if (vehicleRegNo.isEmpty()) {
        System.out.println("Vehicle registration number cannot be empty.");
        return;
    }

    // Create task and add with proper error handling
    try {
        Task task = new Task(description, priority, vehicleRegNo);
        taskManager.addTask(task);
        System.out.println("Task allocated successfully.");
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}

    private void viewTasks() {
    System.out.println("Fetching tasks for the mechanic...");
    List<Task> tasks = taskManager.getTasksByPriority();
    if (tasks.isEmpty()) {
        System.out.println("No tasks available.");
    } else {
        System.out.println("Available tasks:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}

    

    private void markTaskCompleted(Scanner scanner) {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task task = taskManager.findTaskByDescription(description);

        if (task != null && !task.isCompleted()) {
            task.completeTask(currentUser.getName());
            System.out.println("Task marked as completed.");

            Customer customer = customerManager.findCustomerByVehicle(task.getVehicleRegNo());
            if (customer != null) {
                customer.notify("Your vehicle with registration number " + task.getVehicleRegNo() + " is ready.");
            }
        } else {
            System.out.println("Task not found or already completed.");
        }
    }

    private void addManufacturerOrSupplier(Scanner scanner) {
        System.out.print("Enter name of manufacturer or supplier: ");
        String name = scanner.nextLine();
        System.out.println(name + " added successfully.");
    }

 private void exit(boolean exitEntireSystem) {
    if (exitEntireSystem) {
        System.out.println("Exiting the system. Goodbye!");
        System.exit(0);
    } else {
        System.out.println("Returning to the main menu...");
    }
}
}