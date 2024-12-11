package models;

public class Mechanic extends User {
    public Mechanic(String name) {
        super(name);
    }

    @Override
    public void displayMenu() {
        System.out.println("Mechanic Menu:");
        System.out.println("1. View Tasks by Priority");
        System.out.println("2. Mark Task as Completed");
        System.out.println("3. Add Manufacturer or Supplier");
        System.out.println("4. Exit");
    }
}
