package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.ToDoItem;
import io.github.bokalebsson.data.ToDoItemDAO;

import java.time.LocalDate;
import java.util.*;

public class ToDoItemDAOCollection implements ToDoItemDAO {

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
