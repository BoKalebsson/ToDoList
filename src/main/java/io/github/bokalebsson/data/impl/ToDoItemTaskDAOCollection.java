package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.model.ToDoItemTask;
import io.github.bokalebsson.data.ToDoItemTaskDAO;

import java.util.*;

public class ToDoItemTaskDAOCollection implements ToDoItemTaskDAO {

    private Map<Integer, ToDoItemTask> todoItemTasks = new HashMap<>();

    // Constructor: Default
    public ToDoItemTaskDAOCollection() {
        this.todoItemTasks = new HashMap<>();
    }

    // Constructor: Load from Collection<ToDoItemTask> and build internal Map
    public ToDoItemTaskDAOCollection(Collection<ToDoItemTask> toDoItemTaskCollection) {
        // Check if the collection is null
        if (toDoItemTaskCollection == null) {
            throw new IllegalArgumentException("ToDoItemTask collection cannot be null.");
        }

        // Create a new empty map to hold tasks
        Map<Integer, ToDoItemTask> map = new HashMap<>();

        // Populate the map with each task from the collection
        for (ToDoItemTask task : toDoItemTaskCollection) {
            int id = task.getId();

            // Check for duplicate IDs to avoid overwriting
            if (map.containsKey(id)) {
                throw new IllegalArgumentException("Duplicate ToDoItemTask ID found: " + id);
            }

            map.put(id, task);
        }

        // Assign the map to our internal collection
        this.todoItemTasks = map;
    }

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
        return new ArrayList<>(todoItemTasks.values());
    }

    @Override
    public Collection<ToDoItemTask> findByAssignedStatus(boolean assigned) {

        // Create a new list to hold ToDoItemTasks with status assigned:
        Collection<ToDoItemTask> matchingAssignedTasks = new ArrayList<>();

        for (ToDoItemTask toDoItemTask : todoItemTasks.values()) {

            if (toDoItemTask.isAssigned() == assigned) {
                matchingAssignedTasks.add(toDoItemTask);
            }
        }
        return matchingAssignedTasks;
    }

    @Override
    public Collection<ToDoItemTask> findByPersonId(int personId) {

        // Check if id is negative or zero:
        if(personId <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Create a new list to hold ToDoItemTasks with same personId:
        Collection<ToDoItemTask> matchingIdItems = new ArrayList<>();

        for (ToDoItemTask toDoItemTask : todoItemTasks.values()) {

            // Creator should never be null. It is handled in the constructor of ToDoItems.
            if (toDoItemTask.getToDoItem().getCreator() == null) {
                throw new IllegalStateException("Creator is not allowed to be null");
            }

            if (toDoItemTask.getToDoItem().getCreator().getId() == personId) {
                matchingIdItems.add(toDoItemTask);
            }
        }
        return matchingIdItems;

    }

    @Override
    public ToDoItemTask update(ToDoItemTask toDoItemTask) {
        // 1. Check if the input is null
        if (toDoItemTask == null) {
            throw new IllegalArgumentException("ToDoItemTask cannot be null.");
        }

        // 2. Get the item's id
        int id = toDoItemTask.getId();

        // 3. Check if the id is valid
        if (id <= 0) {
            throw new IllegalArgumentException("ToDoItemTask id must be greater than zero.");
        }

        // 4. Check if item with this id exists
        if (!todoItemTasks.containsKey(id)) {
            throw new IllegalArgumentException("ToDoItemTask with id " + id + " does not exist.");
        }

        // 5. Update the item in the map
        todoItemTasks.put(id, toDoItemTask);

        // 6. Return the updated item
        return toDoItemTask;
    }

    @Override
    public void remove(int id) {

        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        if(!todoItemTasks.containsKey(id)) {
            throw new IllegalArgumentException("No ToDoItemTask found with the following id: " + id);
        }
        todoItemTasks.remove(id);
    }
}
