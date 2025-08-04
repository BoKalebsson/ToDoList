package io.github.bokalebsson;

import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.model.ToDoItem;
import io.github.bokalebsson.model.ToDoItemTask;

import java.util.List;

public class DataBundle {
    private final List<Person> persons;
    private final List<ToDoItem> toDoItems;
    private final List<ToDoItemTask> toDoItemTasks;

    public DataBundle(List<Person> persons, List<ToDoItem> toDoItems, List<ToDoItemTask> toDoItemTasks) {
        this.persons = persons;
        this.toDoItems = toDoItems;
        this.toDoItemTasks = toDoItemTasks;
    }

    public List<Person> getPersons() { return persons; }
    public List<ToDoItem> getToDoItems() { return toDoItems; }
    public List<ToDoItemTask> getToDoItemTasks() { return toDoItemTasks; }
}