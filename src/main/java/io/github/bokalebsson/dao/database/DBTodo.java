package io.github.bokalebsson.dao.database;

import java.time.LocalDate;
import java.util.Objects;

public class DBTodo {

    // Fields:
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean isDone;
    private int assigneeId;

    // Constructor for loading DBTodos from the database (with ID):
    public DBTodo(int id, String title, String description, LocalDate deadline, boolean isDone, int assigneeId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.assigneeId = assigneeId;
    }

    // Constructor for creating new DBTodos (without ID):
    public DBTodo(String title, String description, LocalDate deadline, boolean isDone, int assigneeId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isDone = isDone;
        this.assigneeId = assigneeId;
    }

    // Getters:
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    // Setters:
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    // Methods:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- ToDo Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Title: ").append(getTitle()).append("\n");
        sb.append("Description: ").append(getDescription()).append("\n");
        sb.append("Deadline: ").append(getDeadline()).append("\n");
        sb.append("Done: ").append(isDone()).append("\n");
        sb.append("Assigned to: ").append(getAssigneeId()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBTodo dbTodo = (DBTodo) o;
        return id == dbTodo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
