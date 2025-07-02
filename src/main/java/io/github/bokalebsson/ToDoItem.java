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


}
