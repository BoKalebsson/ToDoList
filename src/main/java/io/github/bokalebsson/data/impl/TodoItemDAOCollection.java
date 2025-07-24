package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.ToDoItem;
import io.github.bokalebsson.data.ToDoItemDAO;

import java.time.LocalDate;
import java.util.*;

public class TodoItemDAOCollection implements ToDoItemDAO {

    Map<Integer, ToDoItem> todoItems = new HashMap<>();

    @Override
    public ToDoItem persist(ToDoItem toDoItem) {

        // Check if ToDoItem is null
        if (toDoItem == null) {
            throw new IllegalArgumentException("ToDoItem is not allowed to be null.");
        }

        // Get the ToDoItem's id:
        int toDoItemId = toDoItem.getId();

        if (todoItems.containsKey(toDoItemId)) {
            throw new IllegalArgumentException("ToDoItem already exists: " + toDoItemId);
        }

        // Adds the toDoItem to the collection:
        todoItems.put(toDoItemId, toDoItem);

        // Return the toDoItem:
        return toDoItem;
    }

    @Override
    public ToDoItem findById(int id) {

        // Check if id is negative or zero:
        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Check if the id is in the map:
        if(!todoItems.containsKey(id)) {
            throw new IllegalArgumentException("No ToDo-item found with id: " + id);
        }
        // Return the toDoItem:
        return todoItems.get(id);
    }

    @Override
    public Collection<ToDoItem> findAll() {
        return List.of();
    }

    @Override
    public Collection<ToDoItem> findAllByDoneStatus(boolean done) {
        return List.of();
    }

    @Override
    public Collection<ToDoItem> findByTitleContains(String title) {
        return List.of();
    }

    @Override
    public Collection<ToDoItem> findByPersonId(int personId) {
        return List.of();
    }

    @Override
    public Collection<ToDoItem> findByDeadlineBefore(LocalDate date) {
        return List.of();
    }

    @Override
    public Collection<ToDoItem> findByDeadlineAfter(LocalDate date) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }
}
