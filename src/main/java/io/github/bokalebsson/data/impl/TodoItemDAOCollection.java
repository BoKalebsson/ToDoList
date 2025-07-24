package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.ToDoItem;
import io.github.bokalebsson.data.ToDoItemDAO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class TodoItemDAOCollection implements ToDoItemDAO {

    @Override
    public ToDoItem persist(ToDoItem toDoItem) {
        return null;
    }

    @Override
    public ToDoItem findById(int id) {
        return null;
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
