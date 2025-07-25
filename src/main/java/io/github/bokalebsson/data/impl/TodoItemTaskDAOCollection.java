package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.ToDoItemTask;
import io.github.bokalebsson.data.ToDoItemTaskDAO;

import java.util.Collection;
import java.util.List;

public class TodoItemTaskDAOCollection implements ToDoItemTaskDAO {
    @Override
    public ToDoItemTask persist(ToDoItemTask toDoItemTask) {
        return null;
    }

    @Override
    public ToDoItemTask findById(int id) {
        return null;
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
