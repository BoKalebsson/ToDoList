package io.github.bokalebsson.dao.database;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ToDoItemsDAO implements ToDoItems {

    @Override
    public DBTodo create(DBTodo dbTodo) throws SQLException {
        return null;
    }

    @Override
    public Collection<DBTodo> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public DBTodo findById(int id) throws SQLException {
        return null;
    }

    @Override
    public Collection<DBTodo> findByDoneStatus(boolean isDone) throws SQLException {
        return List.of();
    }

    @Override
    public Collection<DBTodo> findByAssignee(int assigneeId) throws SQLException {
        return List.of();
    }

    @Override
    public Collection<DBTodo> findByAssignee(DBPerson dbPerson) throws SQLException {
        return List.of();
    }

    @Override
    public Collection<DBTodo> findByUnassignedToDoItems() throws SQLException {
        return List.of();
    }

    @Override
    public DBTodo update(DBTodo dbTodo) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }
}
