package models;

public class Supplier {
    private String name;
    private String partsSupplied;

    public Supplier(String name, String partsSupplied) {
        this.name = name;
        this.partsSupplied = partsSupplied;
    }

    public String getName() {
        return name;
    }

    public String getPartsSupplied() {
        return partsSupplied;
    }

    @Override
    public String toString() {
        return "Supplier{name='" + name + "', partsSupplied='" + partsSupplied + "'}";
    }
}