package io.github.bokalebsson.data;

import io.github.bokalebsson.model.ToDoItem;

import java.time.LocalDate;
import java.util.Collection;

public interface ToDoItemDAO {

    /**
     * Saves a new ToDoItem to the collection or updates it if already existing.
     *
     * @param toDoItem the item to persist
     * @return the persisted item
     */
    ToDoItem persist(ToDoItem toDoItem);

    /**
     * Finds a ToDoItem by its unique ID.
     *
     * @param id the ID of the item
     * @return the found item, or null if not found
     */
    ToDoItem findById(int id);

    /**
     * Returns all ToDoItems in the collection.
     *
     * @return a collection of all items
     */
    Collection<ToDoItem> findAll();

    /**
     * Finds all ToDoItems filtered by their 'done' status.
     *
     * @param done true for completed items, false for active items
     * @return a collection of matching items
     */
    Collection<ToDoItem> findAllByDoneStatus(boolean done);

    /**
     * Finds all ToDoItems where the title contains a specific keyword.
     *
     * @param title keyword to search for in item titles
     * @return a collection of matching items
     */
    Collection<ToDoItem> findByTitleContains(String title);

    /**
     * Finds all ToDoItems assigned to a person by their ID.
     *
     * @param personId the ID of the person
     * @return a collection of items created by that person
     */
    Collection<ToDoItem> findByPersonId(int personId);

    /**
     * Finds all ToDoItems with a deadline before the given date.
     *
     * @param date the cutoff date
     * @return a collection of overdue items
     */
    Collection<ToDoItem> findByDeadlineBefore(LocalDate date);

    /**
     * Finds all ToDoItems with a deadline after the given date.
     *
     * @param date the cutoff date
     * @return a collection of upcoming items
     */
    Collection<ToDoItem> findByDeadlineAfter(LocalDate date);

    /**
     * Updates an existing ToDoItem.
     *
     * @param toDoItem the item to update
     * @return the updated item
     */
    ToDoItem update(ToDoItem toDoItem);

    /**
     * Removes a ToDoItem by its ID.
     *
     * @param id the ID of the item to remove
     */
    void remove(int id);

}
