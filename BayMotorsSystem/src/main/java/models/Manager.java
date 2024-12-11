package models;

public class Manager extends User {
    public Manager(String name) {
        super(name);
    }

    @Override
    public void displayMenu() {
        System.out.println("Manager Menu:");
        System.out.println("1. Add Customer");
        System.out.println("2. Upgrade Customer to Registered");
        System.out.println("3. Add Vehicle");
        System.out.println("4. Allocate Task to Mechanic");
        System.out.println("5. Add Manufacturer or Supplier");
        System.out.println("6. Exit");
    }
}
