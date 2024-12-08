package ui;

import models.*;
import services.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame {
    private CustomerManager customerManager;
    private TaskManager taskManager;
    private User currentUser ;

    public MenuGUI() {
        customerManager = new CustomerManager();
        taskManager = new TaskManager();
        initializeMainMenu();
    }

    private void initializeMainMenu() {
        setTitle("Bay Motors System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel welcomeLabel = new JLabel("Welcome to Bay Motors System", JLabel.CENTER);
        add(welcomeLabel);

        JButton managerButton = new JButton("Manager");
        JButton mechanicButton = new JButton("Mechanic");
        JButton exitButton = new JButton("Exit");

        add(managerButton);
        add(mechanicButton);
        add(exitButton);

        managerButton.addActionListener(e -> openManagerMenu());
        mechanicButton.addActionListener(e -> openMechanicMenu());
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    private void openManagerMenu() {
        currentUser  = new Manager("Manager");
        this.setVisible(false); 
        new ManagerMenu(); 
    }

    private void openMechanicMenu() {
        currentUser  = new Mechanic("Mechanic");
        this.setVisible(false); 
        new MechanicMenu(); 
    }

   
    private class ManagerMenu extends JFrame {
        public ManagerMenu() {
            setTitle("Manager Menu");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(7, 1));

            JLabel titleLabel = new JLabel("Manager Menu", JLabel.CENTER);
            add(titleLabel);

            JButton addCustomerButton = new JButton("Add Customer");
            JButton upgradeCustomerButton = new JButton("Upgrade Customer");
            JButton addVehicleButton = new JButton("Add Vehicle");
            JButton allocateTaskButton = new JButton("Allocate Task");
            JButton addSupplierButton = new JButton("Add Manufacturer/Supplier");
            JButton backButton = new JButton("Back to Main Menu");

            add(addCustomerButton);
            add(upgradeCustomerButton);
            add(addVehicleButton);
            add(allocateTaskButton);
            add(addSupplierButton);
            add(backButton);

            addCustomerButton.addActionListener(e -> handleAddCustomer());
            upgradeCustomerButton.addActionListener(e -> handleUpgradeCustomer());
            addVehicleButton.addActionListener(e -> handleAddVehicle());
            allocateTaskButton.addActionListener(e -> handleAllocateTask());
            addSupplierButton.addActionListener(e -> handleAddSupplier());
            backButton.addActionListener(e -> {
                MenuGUI.this.setVisible(true); 
                this.dispose(); 
            });

            setVisible(true);
        }

        private void handleAddCustomer() {
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            String contactInfo = JOptionPane.showInputDialog("Enter Contact Information:");
            String vehicleRegNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number:");
            customerManager.addCustomer(new Customer(name, contactInfo, false, vehicleRegNo));
            JOptionPane.showMessageDialog(this, "Customer added successfully.");
        }

        private void handleUpgradeCustomer() {
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            Customer customer = customerManager.findCustomerByName(name);
            if (customer != null && !customer.isRegistered()) {
                customer.upgradeToRegistered();
                JOptionPane.showMessageDialog(this, "Customer upgraded to registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found or already registered.");
            }
        }

        private void handleAddVehicle() {
            String regNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number :");
            String make = JOptionPane.showInputDialog("Enter Vehicle Make:");
            String model = JOptionPane.showInputDialog("Enter Vehicle Model:");
            JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
        }

        private void handleAllocateTask() {
            String description = JOptionPane.showInputDialog("Enter Task Description:");
            int priority = Integer.parseInt(JOptionPane.showInputDialog("Enter Priority (1-High, 2-Medium, 3-Low):"));
            String vehicleRegNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number:");
            taskManager.addTask(new Task(description, priority, vehicleRegNo));
            JOptionPane.showMessageDialog(this, "Task allocated successfully.");
        }

        private void handleAddSupplier() {
            String name = JOptionPane.showInputDialog("Enter Name of Manufacturer or Supplier:");
            JOptionPane.showMessageDialog(this, name + " added successfully.");
        }
    }

   
    private class MechanicMenu extends JFrame {
        public MechanicMenu() {
            setTitle("Mechanic Menu");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(5, 1));

            JLabel titleLabel = new JLabel("Mechanic Menu", JLabel.CENTER);
            add(titleLabel);

            JButton viewTasksButton = new JButton("View Tasks");
            JButton markTaskCompletedButton = new JButton("Mark Task as Completed");
            JButton addSupplierButton = new JButton("Add Manufacturer/Supplier");
            JButton backButton = new JButton("Back to Main Menu");

            add(viewTasksButton);
            add(markTaskCompletedButton);
            add(addSupplierButton);
            add(backButton);

            viewTasksButton.addActionListener(e -> handleViewTasks());
            markTaskCompletedButton.addActionListener(e -> handleMarkTaskCompleted());
            addSupplierButton.addActionListener(e -> handleAddSupplier());
            backButton.addActionListener(e -> {
                MenuGUI.this.setVisible(true); 
                this.dispose(); 
            });

            setVisible(true);
        }

        private void handleViewTasks() {
            StringBuilder tasksList = new StringBuilder("Tasks:\n");
            for (Task task : taskManager.getTasksByPriority()) {
                tasksList.append(task.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, tasksList.toString());
        }

        private void handleMarkTaskCompleted() {
            String description = JOptionPane.showInputDialog("Enter Task Description:");
            Task task = taskManager.findTaskByDescription(description);
            if (task != null && !task.isCompleted()) {
                task.completeTask(currentUser .getName());
                JOptionPane.showMessageDialog(this, "Task marked as completed.");
            } else {
                JOptionPane.showMessageDialog(this, "Task not found or already completed.");
            }
        }

        private void handleAddSupplier() {
         
    String name = JOptionPane.showInputDialog("Enter Name of Manufacturer or Supplier:");
    if (name == null || name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Name cannot be empty. Please try again.");
    } else {
        JOptionPane.showMessageDialog(this, name + " added successfully.");
    }
}
        
    }

    public static void main(String[] args) {
        new MenuGUI();
    }
} 