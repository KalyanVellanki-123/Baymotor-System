package models;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private int priority; 
    private boolean completed;
    private LocalDateTime completionTime;
    private String completedBy;
    private String vehicleRegNo;

    
    public Task(String description, int priority, String vehicleRegNo) {
        this.description = description;
        this.priority = priority;
        this.completed = false; 
        this.vehicleRegNo = vehicleRegNo;
    }

    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < 1 || priority > 3) {
            throw new IllegalArgumentException("Priority must be 1 (High), 2 (Medium), or 3 (Low).");
        }
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public String getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    // Method to mark the task as completed
    public void completeTask(String mechanicName) {
        if (!this.completed) {
            this.completed = true;
            this.completionTime = LocalDateTime.now();
            this.completedBy = mechanicName;
        } else {
            System.out.println("Task is already completed.");
        }
    }

    // Method to get task status
    public String getStatus() {
        return completed ? "Completed" : "Pending";
    }

    
    @Override
    public String toString() {
        return "Task{" +
                "Description='" + description + '\'' +
                ", Priority=" + (priority == 1 ? "High" : priority == 2 ? "Medium" : "Low") +
                ", Status=" + getStatus() +
                ", VehicleRegNo='" + vehicleRegNo + '\'' +
                (completed ? ", Completed By='" + completedBy + '\'' +
                        ", Completion Time=" + completionTime : "") +
                '}';
    }
}
