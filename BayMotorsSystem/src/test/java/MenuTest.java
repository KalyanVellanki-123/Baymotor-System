import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.CustomerManager;
import models.Customer;
import models.Supplier;
import models.Vehicle;
import ui.ManufacturerManager;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    private CustomerManager customerManager;
    private ManufacturerManager manufacturerManager;

    @BeforeEach
    public void setUp() {
        customerManager = new CustomerManager();
        manufacturerManager = new ManufacturerManager();
    }

    @Test
    public void T1_addCustomer_ValidDetails() {
        // Given valid customer details
        String name = "John Doe";
        String contactInfo = "123-456-7890";
        String vehicleRegNo = "ABC123";
        String make = "Toyota";
        String model = "Corolla";

        // When addCustomer is called
        customerManager.addCustomer(name, contactInfo, vehicleRegNo, make, model);

        // Then customer should be added successfully
        Customer customer = customerManager.findCustomerByName(name);
        assertNotNull(customer);
        assertEquals(name, customer.getName());
        assertTrue(customer.hasVehicle(vehicleRegNo));
    }

    @Test
    public void T2_addCustomer_MissingFields() {
        // Given missing customer details
        String name = "";
        String contactInfo = "123-456-7890";
        String vehicleRegNo = "ABC123";
        String make = "Toyota";
        String model = "Corolla";

        // When addCustomer is called
        customerManager.addCustomer(name, contactInfo, vehicleRegNo, make, model);

        // Then customer should not be added
        Customer customer = customerManager.findCustomerByName(name);
        assertNull(customer);
    }

    @Test
    public void T3_addVehicleToCustomer_ValidCustomer() {
        // Given an existing customer and a new vehicle
        String customerName = "John Doe";
        String vehicleRegNo = "DEF456";
        String make = "Honda";
        String model = "Civic";
        customerManager.addCustomer("John Doe", "123-456-7890", "ABC123", "Toyota", "Corolla");

        // When addVehicleToCustomer is called
        customerManager.addVehicleToCustomer(vehicleRegNo, make, model, customerName);

        // Then the vehicle should be added to the customer's vehicle list
        Customer customer = customerManager.findCustomerByName(customerName);
        assertNotNull(customer);
        assertTrue(customer.hasVehicle(vehicleRegNo));
    }

    @Test
    public void T4_addVehicleToCustomer_InvalidCustomer() {
        // Given a non-existing customer
        String customerName = "Jane Smith";
        String vehicleRegNo = "XYZ789";
        String make = "Ford";
        String model = "Focus";

        // When addVehicleToCustomer is called
        customerManager.addVehicleToCustomer(vehicleRegNo, make, model, customerName);

        // Then no vehicle should be added
        Customer customer = customerManager.findCustomerByName(customerName);
        assertNull(customer);
    }

    @Test
    void T5_notifyRegisteredCustomers() {
        // Arrange
        Customer john = new Customer("John", "john@example.com", true, new Vehicle("123", "Toyota", "Corolla"));
        Customer jane = new Customer("Jane", "jane@example.com", false, new Vehicle("456", "Honda", "Civic"));
        customerManager.addCustomer(john.getName(), john.getContactInfo(), "123", "Toyota", "Corolla");
        customerManager.addCustomer(jane.getName(), jane.getContactInfo(), "456", "Honda", "Civic");

        // Act
        customerManager.notifyRegisteredCustomers("Welcome to our service!");

        // Assert
        assertTrue(john.isRegistered(), "John should be registered");
        assertFalse(jane.isRegistered(), "Jane should not be registered");
    }

    @Test
    void T9_addSupplier_ValidSupplier() {
        // Arrange
        String manufacturer = "Toyota";
        String supplierName = "ABC Supplies";
        String supplierAddress = "1234 Market Street";
        Supplier supplier = new Supplier(supplierName, supplierAddress);

        // Add manufacturer and supplier
        manufacturerManager.addManufacturer(manufacturer, supplierName); 
        manufacturerManager.addSupplierToManufacturer(manufacturer, supplierName); 

        // Act
        List<String> suppliers = manufacturerManager.getSuppliersByManufacturer(manufacturer);

        // Assert
        assertNotNull(suppliers, "Suppliers list should not be null");
        assertEquals(1, suppliers.size(), "There should be one supplier");
        assertEquals(supplierName, suppliers.get(0), "Supplier name should match");
    }

    @Test
    void T7_getRegisteredCustomers() {
        // Arrange
        Customer john = new Customer("John", "john@example.com", true, new Vehicle("123", "Toyota", "Corolla"));
        Customer jane = new Customer("Jane", "jane@example.com", false, new Vehicle("456", "Honda", "Civic"));
        customerManager.addCustomer(john.getName(), john.getContactInfo(), "123", "Toyota", "Corolla");
        customerManager.addCustomer(jane.getName(), jane.getContactInfo(), "456", "Honda", "Civic");

        // Act
        List<Customer> registeredCustomers = customerManager.getRegisteredCustomers();

        // Assert
        assertEquals(1, registeredCustomers.size(), "There should be one registered customer");
        assertTrue(registeredCustomers.contains(john), "John should be in the registered customers list");
        assertFalse(registeredCustomers.contains(jane), "Jane should not be in the registered customers list");
    }

    @Test
    void T8_getUnregisteredCustomers() {
        // Arrange
        Customer john = new Customer("John", "john@example.com", true, new Vehicle("123", "Toyota", "Corolla"));
        Customer jane = new Customer("Jane", "jane@example.com", false, new Vehicle("456", "Honda", "Civic"));
        customerManager.addCustomer(john.getName(), john.getContactInfo(), "123", "Toyota", "Corolla");
        customerManager.addCustomer(jane.getName(), jane.getContactInfo(), "456", "Honda", "Civic");

        // Act
        List<Customer> unregisteredCustomers = customerManager.getUnregisteredCustomers();

        // Assert
        assertEquals(1, unregisteredCustomers.size(), "There should be one unregistered customer");
        assertTrue(unregisteredCustomers.contains(jane), "Jane should be in the unregistered customers list");
        assertFalse(unregisteredCustomers.contains(john), "John should not be in the unregistered customers list");
    }

    @Test
    void T6_notifyUnregisteredCustomers() {
        // Arrange
        Customer john = new Customer("John", "john@example.com", true, new Vehicle("123", "Toyota", "Corolla"));
        Customer jane = new Customer("Jane", "jane@example.com", false, new Vehicle("456", "Honda", "Civic"));
        customerManager.addCustomer(john.getName(), john.getContactInfo(), "123", "Toyota", "Corolla");
        customerManager.addCustomer(jane.getName(), jane.getContactInfo(), "456", "Honda", "Civic");

        // Act
        customerManager.notifyUnregisteredCustomers("Please register with us!");

        // Assert
        assertTrue(john.isRegistered(), "John should be registered after notification");
        assertFalse(jane.isRegistered(), "Jane should not be registered after notification");
    }
}
