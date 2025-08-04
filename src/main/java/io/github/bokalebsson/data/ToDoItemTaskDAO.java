package io.github.bokalebsson.data;

import io.github.bokalebsson.model.ToDoItemTask;

import java.util.Collection;

public interface ToDoItemTaskDAO {

    /**
     * Saves a new ToDoItemTask or updates an existing one.
     *
     * @param toDoItemTask The task to be persisted.
     * @return The saved ToDoItemTask instance.
     */
    ToDoItemTask persist(ToDoItemTask toDoItemTask);

    /**
     * Finds a ToDoItemTask by its unique ID.
     *
     * @param id The ID of the task to find.
     * @return The matching ToDoItemTask, or null if not found.
     */
    ToDoItemTask findById(int id);

    /**
     * Returns all ToDoItemTask objects currently stored.
     *
     * @return A collection of all ToDoItemTasks.
     */
    Collection<ToDoItemTask> findAll();

    /**
     * Finds all tasks based on their assigned status.
     *
     * @param assigned If true, only tasks with an assignee are returned; otherwise, only unassigned.
     * @return A collection of ToDoItemTasks matching the assigned status.
     */
    Collection<ToDoItemTask> findByAssignedStatus(boolean assigned);

    /**
     * Finds all tasks assigned to a specific person by their ID.
     *
     * @param personId The ID of the assignee.
     * @return A collection of tasks assigned to the given person.
     */
    Collection<ToDoItemTask> findByPersonId(int personId);

    /**
     * Updates an existing ToDoItemTask.
     *
     * @param task the task to update
     * @return the updated task
     */
    ToDoItemTask update(ToDoItemTask task);

    /**
     * Removes a task by its ID.
     *
     * @param id The ID of the task to remove.
     */
    void remove(int id);

}
