package ui;

import models.*;
import services.*;
import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JFrame {
    private CustomerManager customerManager;
    private TaskManager taskManager;
    private User currentUser ;

    public MenuGUI() {
        customerManager = new CustomerManager();
        taskManager = new TaskManager(customerManager);
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
            setLayout(new GridLayout(8, 1));

            JLabel titleLabel = new JLabel("Manager Menu", JLabel.CENTER);
            add(titleLabel);

            JButton addCustomerButton = new JButton("Add Customer");
            JButton upgradeCustomerButton = new JButton("Upgrade Customer");
            JButton addVehicleButton = new JButton("Add Vehicle");
            JButton allocateTaskButton = new JButton("Allocate Task");
            JButton addSupplierButton = new JButton("Add Manufacturer/Supplier");
            JButton postNotificationsButton = new JButton("Post Notifications");
            JButton backButton = new JButton("Back to Main Menu");

            add(addCustomerButton);
            add(upgradeCustomerButton);
            add(addVehicleButton);
            add(allocateTaskButton);
            add(addSupplierButton);
            add(postNotificationsButton);
            add(backButton);

            addCustomerButton.addActionListener(e -> handleAddCustomer());
            upgradeCustomerButton.addActionListener(e -> handleUpgradeCustomer());
            addVehicleButton.addActionListener(e -> handleAddVehicle());
            allocateTaskButton.addActionListener(e -> handleAllocateTask());
            addSupplierButton.addActionListener(e -> handleAddSupplier());
            postNotificationsButton.addActionListener(e -> handlePostNotifications());
            backButton.addActionListener(e -> {
                MenuGUI.this.setVisible(true);
                this.dispose();
            });

            setVisible(true);
        }

        private void handleAddCustomer() {
    String name = JOptionPane.showInputDialog("Enter Customer Name:");
    if (name == null || name.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Customer Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String contactInfo = JOptionPane.showInputDialog("Enter Contact Information:");
    if (contactInfo == null || contactInfo.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Contact Information cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String vehicleRegNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number:");
    if (vehicleRegNo == null || vehicleRegNo.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vehicle Registration Number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String make = JOptionPane.showInputDialog("Enter Vehicle Make:");
    if (make == null || make.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vehicle Make cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    String model = JOptionPane.showInputDialog("Enter Vehicle Model:");
    if (model == null || model.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vehicle Model cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    customerManager.addCustomer(name, contactInfo, vehicleRegNo, make, model);
    JOptionPane.showMessageDialog(this, "Customer added successfully.");
}

        private void handleUpgradeCustomer() {
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Customer Name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Customer customer = customerManager.findCustomerByName(name);
            if (customer != null && !customer.isRegistered()) {
                customer.upgradeToRegistered();
                JOptionPane.showMessageDialog(this, "Customer upgraded to registered.");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found or already registered.");
            }
 
        }

        private void handleAddVehicle() {
            String regNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number:");
            if (regNo == null || regNo.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle Registration Number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String make = JOptionPane.showInputDialog("Enter Vehicle Make:");
            if (make == null || make.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle Make cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String model = JOptionPane.showInputDialog("Enter Vehicle Model:");
            if (model == null || model.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vehicle Model cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            taskManager.addVehicle(new Vehicle(regNo, make, model));
            JOptionPane.showMessageDialog(this, "Vehicle added successfully.");
        }

        private void handleAllocateTask() {
            try {
                String description = JOptionPane.showInputDialog("Enter Task Description:");
                if (description == null || description.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Task description cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String priorityInput = JOptionPane.showInputDialog("Enter Priority (1-High, 2-Medium, 3-Low):");
                if (priorityInput == null || priorityInput.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Priority cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int priority;
                try {
                    priority = Integer.parseInt(priorityInput.trim());
                    if (priority < 1 || priority > 3) {
                        JOptionPane.showMessageDialog(this, "Priority must be 1 (High), 2 (Medium), or 3 (Low).", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Priority must be a valid number (1, 2, or 3).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String vehicleRegNo = JOptionPane.showInputDialog("Enter Vehicle Registration Number:");
                if (vehicleRegNo == null || vehicleRegNo.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vehicle registration number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                taskManager.addTask(new Task(description.trim(), priority, vehicleRegNo.trim()));
                JOptionPane.showMessageDialog(this, "Task allocated successfully.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void handleAddSupplier() {
            String name = JOptionPane.showInputDialog("Enter Name of Manufacturer or Supplier:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String parts = JOptionPane.showInputDialog("Enter Parts Supplied:");
            if (parts == null || parts.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Parts cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            customerManager.addSupplier(new Supplier(name, parts));
            JOptionPane.showMessageDialog(this, name + " added successfully.");
        }

        private void handlePostNotifications() {
            String message = JOptionPane.showInputDialog("Enter notification message for registered customers:");
            if (message != null && !message.trim().isEmpty()) {
                customerManager.notifyRegisteredCustomers(message);
            }

            String promoMessage = JOptionPane.showInputDialog("Enter promotional message for unregistered customers:");
            if (promoMessage != null && !promoMessage.trim().isEmpty()) {
                customerManager.notifyUnregisteredCustomers(promoMessage);
            }
            JOptionPane.showMessageDialog(this, "Notifications sent successfully.");
        }
    }

    private class MechanicMenu extends JFrame {
        public MechanicMenu() {
            setTitle("Mechanic Menu");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(4, 1));

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

        // Notify the customer that their vehicle is ready
        Customer customer = customerManager.findCustomerByVehicle(task.getVehicleRegNo());
        if (customer != null) {
            // Create the notification message with an additional line
            String message = "Dear " + customer.getName() + ",\n" +
                             "Your vehicle with registration number " + task.getVehicleRegNo() + " is ready for pickup.\n" +
                             "Please visit us at your earliest convenience to collect your vehicle. "
                    + "message sent to customer.";
            
            
            // Show a dialog to the mechanic
            JOptionPane.showMessageDialog(this, message);
            
            // Optionally, send the message via email or SMS
            // Uncomment the following line if you have implemented email sending
            // sendEmailNotification(customer.getContactInfo(), message);
        } else {
            JOptionPane.showMessageDialog(this, "Customer not found for the vehicle registration number.");
        }

        JOptionPane.showMessageDialog(this, "Task marked as completed.");
    } else {
        JOptionPane.showMessageDialog(this, "Task not found or already completed.");
    }
}



        private void handleAddSupplier() {
            String name = JOptionPane.showInputDialog("Enter Name of Manufacturer or Supplier:");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String parts = JOptionPane.showInputDialog("Enter Parts Supplied:");
            if (parts == null || parts.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Parts cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            customerManager.addSupplier(new Supplier(name, parts));
            JOptionPane.showMessageDialog(this, name + " added successfully.");
        }
    }

    public static void main(String[] args) {
        new MenuGUI();
    }
}