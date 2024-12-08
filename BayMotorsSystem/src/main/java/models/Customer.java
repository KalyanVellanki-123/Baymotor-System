package models;

public class Customer {
    private String name;
    private String contactInfo;
    private boolean isRegistered;
    private String vehicleRegNo; 

    
    public Customer(String name, String contactInfo, boolean isRegistered, String vehicleRegNo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.isRegistered = isRegistered;
        this.vehicleRegNo = vehicleRegNo; 
    }

    
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo; 
    }

    
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    
    public void upgradeToRegistered() {
        this.isRegistered = true;
    }

    
    public void notify(String message) {
        System.out.println("Notification to " + name + " (" + contactInfo + "): " + message);
    }

    
    @Override
    public String toString() {
        return "Customer{name='" + name + "', contactInfo='" + contactInfo + "', isRegistered=" + isRegistered + ", vehicleRegNo='" + vehicleRegNo + "'}";
    }
}
