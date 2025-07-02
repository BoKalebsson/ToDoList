package io.github.bokalebsson;

import java.time.LocalDate;
import java.util.UUID;

public class ToDoItem {

    // Attributes:
    private String id;
    private String title;
    private String taskDescription;
    private LocalDate deadline;
    private boolean done;

    // Constructor:
    public ToDoItem(String title, String taskDescription, LocalDate deadline) {
        this.id = UUID.randomUUID().toString();
        setTitle(title);
        setTaskDescription(taskDescription);
        setDeadline(deadline);
        this.done = false;
    }

    // Getters:
    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public boolean isDone() {
        return this.done;
    }

    // Setters:
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public void setTaskDescription(String taskDescription) {
        if (taskDescription == null || taskDescription.trim().isEmpty()){
            throw new IllegalArgumentException("Task description cannot be null or empty.");
        }
        this.taskDescription = taskDescription;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null){
            throw new IllegalArgumentException("Deadline cannot be null.");
        }
        if(deadline.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Deadline cannot be in the past.");
        }
        this.deadline = deadline;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
