package io.github.bokalebsson;

import io.github.bokalebsson.data.sequencers.ToDoItemIdSequencer;

import java.time.LocalDate;
import java.util.Objects;

public class ToDoItem {

    // Attributes:
    private int id;
    private String title;
    private String taskDescription;
    private LocalDate deadline;
    private Person creator;
    private boolean done;

    // Constructor:
    public ToDoItem(String title, String taskDescription, LocalDate deadline, Person creator) {
        this.id = ToDoItemIdSequencer.nextId();
        setTitle(title);
        setTaskDescription(taskDescription);
        setDeadline(deadline);
        this.creator = Objects.requireNonNull(creator, "Creator cannot be null.");
        this.done = false;
    }

    // Getters:
    public int getId() {
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

    public Person getCreator() {
        return creator;
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

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    // Operations:
    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadline);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- ToDoItem Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Title: ").append(getTitle()).append("\n");
        sb.append("Task Description: ").append(getTaskDescription()).append("\n");
        sb.append("Deadline: ").append(getDeadline()).append("\n");
        sb.append("Done: ").append(isDone()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        // Step 1: Check if both references point to the same object in memory.
        if (this == o) return true;

        // Step 2: If the other object is null, they cannot be equal.
        if (o == null) return false;

        // Step 3: Check if the other object is of type ToDoItem.
        if (!(o instanceof ToDoItem)) return false;

        // Step 4: Cast the object to ToDoItem so we can access its fields.
        ToDoItem other = (ToDoItem) o;

        // Step 5: Compare fields that define identity (excluding creator).
        return this.id == other.id &&
                this.title.equals(other.title) &&
                this.taskDescription.equals(other.taskDescription) &&
                this.deadline.equals(other.deadline) &&
                this.done == other.done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.taskDescription, this.deadline, this.done);
    }

}

