package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages manufacturers and their associated suppliers.
 */
public class ManufacturerManager {
    // Map to store manufacturers and their associated suppliers.
    private Map<String, List<String>> manufacturerSuppliers = new HashMap<>();

    /**
     * Adds a new manufacturer with its associated supplier.
     *
     * @param manufacturer The name of the manufacturer.
     * @param supplier     The name of the supplier associated with the manufacturer.
     */
    public void addManufacturer(String manufacturer, String supplier) {
        if (manufacturer == null || manufacturer.isEmpty() || supplier == null || supplier.isEmpty()) {
            System.out.println("Invalid manufacturer or supplier details. Operation failed.");
            return;
        }

        manufacturerSuppliers.putIfAbsent(manufacturer, new ArrayList<>());
        manufacturerSuppliers.get(manufacturer).add(supplier);
        System.out.println("Manufacturer and supplier added successfully.");
    }

    /**
     * Adds a supplier to an existing manufacturer.
     *
     * @param manufacturer The name of the manufacturer.
     * @param supplier     The name of the supplier to be added.
     */
    public void addSupplierToManufacturer(String manufacturer, String supplier) {
        if (manufacturer == null || manufacturer.isEmpty() || supplier == null || supplier.isEmpty()) {
            System.out.println("Invalid manufacturer or supplier details. Operation failed.");
            return;
        }

        if (manufacturerSuppliers.containsKey(manufacturer)) {
            manufacturerSuppliers.get(manufacturer).add(supplier);
            System.out.println("Supplier added successfully to manufacturer: " + manufacturer);
        } else {
            System.out.println("Manufacturer not found. Please add the manufacturer first.");
        }
    }

    /**
     * Retrieves a list of suppliers associated with a given manufacturer.
     *
     * @param manufacturer The name of the manufacturer.
     * @return A list of suppliers, or null if the manufacturer does not exist.
     */
    public List<String> getSuppliersByManufacturer(String manufacturer) {
        return manufacturerSuppliers.getOrDefault(manufacturer, null);
    }

    /**
     * Lists all manufacturers and their associated suppliers.
     */
    public void listAllManufacturersAndSuppliers() {
        if (manufacturerSuppliers.isEmpty()) {
            System.out.println("No manufacturers or suppliers found.");
            return;
        }

        for (Map.Entry<String, List<String>> entry : manufacturerSuppliers.entrySet()) {
            System.out.println("Manufacturer: " + entry.getKey());
            System.out.println("Suppliers: " + String.join(", ", entry.getValue()));
            System.out.println();
        }
    }

    /**
     * Removes a manufacturer and all associated suppliers.
     *
     * @param manufacturer The name of the manufacturer to be removed.
     */
    public void removeManufacturer(String manufacturer) {
        if (manufacturerSuppliers.containsKey(manufacturer)) {
            manufacturerSuppliers.remove(manufacturer);
            System.out.println("Manufacturer and associated suppliers removed successfully.");
        } else {
            System.out.println("Manufacturer not found.");
        }
    }

    /**
     * Removes a specific supplier associated with a manufacturer.
     *
     * @param manufacturer The name of the manufacturer.
     * @param supplier     The name of the supplier to be removed.
     */
    public void removeSupplier(String manufacturer, String supplier) {
        if (manufacturerSuppliers.containsKey(manufacturer)) {
            List<String> suppliers = manufacturerSuppliers.get(manufacturer);
            if (suppliers.remove(supplier)) {
                System.out.println("Supplier removed successfully.");
            } else {
                System.out.println("Supplier not found for the given manufacturer.");
            }

            // Remove the manufacturer if no suppliers are left.
            if (suppliers.isEmpty()) {
                manufacturerSuppliers.remove(manufacturer);
                System.out.println("No suppliers left for the manufacturer. Manufacturer removed.");
            }
        } else {
            System.out.println("Manufacturer not found.");
        }
    }
}