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

        // Step 1: Are we comparing the same object in memory?
        if (this == o) return true;

        // Step 2: Null can't be equal to anything.
        if (o == null) return false;

        // Step 3: Is the other object of the correct type?
        if (!(o instanceof ToDoItemTask)) return false;

        // Step 4: Safe type casting.
        ToDoItemTask other = (ToDoItemTask) o;

        // Step 5: Compare all relevant fields (except creator).
        return this.id == other.id &&
               this.toDoItem.equals(other.toDoItem) &&
               this.assigned == (other.assigned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.toDoItem, this.assigned);
    }

}
