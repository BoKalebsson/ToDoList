package io.github.bokalebsson.dao.impl;

import io.github.bokalebsson.model.ToDoItem;
import io.github.bokalebsson.dao.ToDoItemDAO;

import java.time.LocalDate;
import java.util.*;

public class ToDoItemDAOCollection implements ToDoItemDAO {

    private Map<Integer, ToDoItem> todoItems = new HashMap<>();

    // Constructor: Default
    public ToDoItemDAOCollection() {
        this.todoItems = new HashMap<>();
    }

    // Constructor: Load from Collection<ToDoItem> and build internal Map
    public ToDoItemDAOCollection(Collection<ToDoItem> toDoItemCollection) {
        // Check if the collection is null
        if (toDoItemCollection == null) {
            throw new IllegalArgumentException("ToDoItem collection cannot be null.");
        }

        // Create a new empty map to hold items
        Map<Integer, ToDoItem> map = new HashMap<>();

        // Populate the map with each item from the collection
        for (ToDoItem item : toDoItemCollection) {
            int id = item.getId();

            // Check for duplicate IDs to avoid overwriting
            if (map.containsKey(id)) {
                throw new IllegalArgumentException("Duplicate ToDoItem ID found: " + id);
            }

            map.put(id, item);
        }

        // Assign the map to our internal collection
        this.todoItems = map;
    }

    // Helper methods: Converts a given list of ToDoItem objects into the internal map representation.
    public void setItems(List<ToDoItem> toDoItemList) {
        todoItems.clear();
        for (ToDoItem item : toDoItemList) {
            todoItems.put(item.getId(), item);
        }
    }

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
        return new ArrayList<>(todoItems.values());
    }

    @Override
    public Collection<ToDoItem> findAllByDoneStatus(boolean done) {

        // Create a new list to hold ToDoItems with status done:
        Collection<ToDoItem> matchingStatusItems = new ArrayList<>();

        for (ToDoItem toDoItem : todoItems.values()) {

            if (toDoItem.isDone() == done) {
                matchingStatusItems.add(toDoItem);
            }
        }
        return matchingStatusItems;
    }

    @Override
    public Collection<ToDoItem> findByTitleContains(String title) {

        // Check if title is null or empty:
        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }

        // Create a new list to hold ToDoItems with matching titles:
        Collection<ToDoItem> matchingTitles = new ArrayList<>();

        // Title to lower-case:
        String searchTitle = title.toLowerCase();

        for (ToDoItem toDoItem : todoItems.values()) {

            // toDoItem's title to lower-case:
            String itemTitle = toDoItem.getTitle().toLowerCase();

            // Check if it contains the searchTitle. Note: contains, not equals.
            if (itemTitle.contains(searchTitle)){
                matchingTitles.add(toDoItem);
            }
        }
        // Return the matching titles:
        return matchingTitles;
    }

    @Override
    public Collection<ToDoItem> findByPersonId(int personId) {

        // Check if id is negative or zero:
        if(personId <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Create a new list to hold ToDoItems with same personId:
        Collection<ToDoItem> matchingIdItems = new ArrayList<>();

        for (ToDoItem toDoItem : todoItems.values()) {

            // Creator should never be null. It is handled in the constructor of ToDoItems.
            if (toDoItem.getCreator() == null) {
                throw new IllegalStateException("Creator is not allowed to be null");
            }

            if (toDoItem.getCreator().getId() == personId) {
                matchingIdItems.add(toDoItem);
            }
        }
        return matchingIdItems;
    }

    @Override
    public Collection<ToDoItem> findByDeadlineBefore(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Date is not allowed to be null.");
        }

        // Create a new list to hold ToDoItems before Deadline:
        Collection<ToDoItem> itemsBeforeDeadline = new ArrayList<>();

        for (ToDoItem toDoItem : todoItems.values()) {

            LocalDate deadline = toDoItem.getDeadline();

            if (deadline.isBefore(date)) {
                itemsBeforeDeadline.add(toDoItem);
            }
        }
        return itemsBeforeDeadline;
    }

    @Override
    public Collection<ToDoItem> findByDeadlineAfter(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException("Date is not allowed to be null.");
        }

        // Create a new list to hold ToDoItems before Deadline:
        Collection<ToDoItem> itemsAfterDeadline = new ArrayList<>();

        for (ToDoItem toDoItem : todoItems.values()) {

            LocalDate deadline = toDoItem.getDeadline();

            if (deadline.isAfter(date)) {
                itemsAfterDeadline.add(toDoItem);
            }
        }
        return itemsAfterDeadline;
    }

    @Override
    public ToDoItem update(ToDoItem toDoItem) {
        // 1. Check if the input is null
        if (toDoItem == null) {
            throw new IllegalArgumentException("ToDoItem cannot be null.");
        }

        // 2. Get the item's id
        int id = toDoItem.getId();

        // 3. Check if the id is valid
        if (id <= 0) {
            throw new IllegalArgumentException("ToDoItem id must be greater than zero.");
        }

        // 4. Check if item with this id exists
        if (!todoItems.containsKey(id)) {
            throw new IllegalArgumentException("ToDoItem with id " + id + " does not exist.");
        }

        // 5. Update the item in the map
        todoItems.put(id, toDoItem);

        // 6. Return the updated item
        return toDoItem;
    }

    @Override
    public void remove(int id) {

        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        if(!todoItems.containsKey(id)) {
            throw new IllegalArgumentException("No ToDo-item found with the following id: " + id);
        }
        todoItems.remove(id);
    }
}
