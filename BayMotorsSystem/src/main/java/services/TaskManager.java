package services;

import models.*;
import java.util.*;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    
    public void addTask(Task task) {
        tasks.add(task);  
    }

    
    public List<Task> getTasksByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
        return tasks;
    }

    
    public Task findTaskByDescription(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }
        return null;
    }
}
