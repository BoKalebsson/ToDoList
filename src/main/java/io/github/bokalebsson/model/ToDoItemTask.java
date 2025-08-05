package io.github.bokalebsson.model;

import io.github.bokalebsson.dao.sequencers.ToDoItemTaskIdSequencer;

import java.util.Objects;

public class ToDoItemTask {

    // Attributes:
    private int id;
    private ToDoItem toDoItem;
    private Person assignee;
    private boolean assigned;

    // Constructor:

    //Required for JSON deserialization (Jackson), Do not use this manually.
    ToDoItemTask() {
    }

    public ToDoItemTask(ToDoItem toDoItem, Person assignee) {
        this.id = ToDoItemTaskIdSequencer.nextId();
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
        if (toDoItem == null) {
            throw new IllegalArgumentException("ToDo-item cannot be null.");
        }
        this.toDoItem = toDoItem;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        // Set 'assigned' to true if 'assignee' is not null, otherwise false.
        this.assigned = (assignee != null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- ToDoItemTask Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("ToDo-item: ").append("\n").append(toDoItem.toString());
        sb.append("Assigned: ").append(isAssigned()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        // Step 1: Check if both references point to the same object in memory.
        if (this == o) return true;

        // Step 2: If the other object is null, they cannot be equal.
        if (o == null) return false;

        // Step 3: Check if the other object is of type ToDoItemTask.
        if (!(o instanceof ToDoItemTask)) return false;

        // Step 4: Cast the object to ToDoItemTask so we can access its fields.
        ToDoItemTask other = (ToDoItemTask) o;

        // Step 5: Compare fields that define identity (excluding creator).
        return this.id == other.id &&
               this.toDoItem.equals(other.toDoItem) &&
               this.assigned == (other.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.toDoItem, this.assigned);
    }

}
