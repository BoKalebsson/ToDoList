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
        this.title = title;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.done = false;
    }
}
