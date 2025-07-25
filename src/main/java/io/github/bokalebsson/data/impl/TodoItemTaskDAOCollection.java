package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.ToDoItemTask;
import io.github.bokalebsson.data.ToDoItemTaskDAO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoItemTaskDAOCollection implements ToDoItemTaskDAO {

    Map<Integer, ToDoItemTask> todoItemTasks = new HashMap<>();

    @Override
    public ToDoItemTask persist(ToDoItemTask toDoItemTask) {

        // Check if ToDoItemTask is null
        if (toDoItemTask == null) {
            throw new IllegalArgumentException("ToDoItemTask is not allowed to be null.");
        }

        // Get the ToDoItemTask's id:
        int toDoItemTaskId = toDoItemTask.getId();

        if (todoItemTasks.containsKey(toDoItemTaskId)) {
            throw new IllegalArgumentException("ToDoItemTask already exists: " + toDoItemTaskId);
        }

        // Adds the toDoItemTask to the collection:
        todoItemTasks.put(toDoItemTaskId, toDoItemTask);

        // Return the toDoItemTask:
        return toDoItemTask;

    }

    @Override
    public ToDoItemTask findById(int id) {

        // Check if id is negative or zero:
        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Check if the id is in the map:
        if(!todoItemTasks.containsKey(id)) {
            throw new IllegalArgumentException("No ToDoItemTask found with id: " + id);
        }
        // Return the toDoItemTask:
        return todoItemTasks.get(id);

    }

    @Override
    public Collection<ToDoItemTask> findAll() {
        return List.of();
    }

    @Override
    public Collection<ToDoItemTask> findByAssignedStatus(boolean assigned) {
        return List.of();
    }

    @Override
    public Collection<ToDoItemTask> findByPersonId(int personId) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }
}
