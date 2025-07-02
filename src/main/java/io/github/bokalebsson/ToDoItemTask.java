package io.github.bokalebsson;

import java.util.Objects;

public class ToDoItemTask {

    private static int toDoItemTaskIdCounter = 0;

    // Attributes:
    private int id;
    private ToDoItem toDoItem;
    private Person assignee;
    private boolean assigned;

    // Constructor:
    public ToDoItemTask(ToDoItem toDoItem, Person assignee) {
        this.id = ++toDoItemTaskIdCounter;

        this.toDoItem = Objects.requireNonNull(toDoItem, "ToDoItem cannot be null.");
        this.assignee = assignee;
        this.assigned = (assignee != null);
    }

    // Getters:
    public int getId() {
        return this.id;
    }

    public ToDoItem getToDoItem() {
        return this.toDoItem;
    }

    public Person getAssignee() {
        return this.assignee;
    }

    public boolean isAssigned() {
        return this.assigned;
    }

    // Setters:
    public void setToDoItem(ToDoItem toDoItem) {
        if (toDoItem == null){
            throw new IllegalArgumentException("ToDo-item cannot be null.");
        }
        this.toDoItem = toDoItem;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        // Set 'assigned' to true if 'assignee' is not null, otherwise false.
        this.assigned = (assignee != null);
    }
}
