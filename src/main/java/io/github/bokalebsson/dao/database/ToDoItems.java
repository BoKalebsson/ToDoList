package io.github.bokalebsson.dao.database;

import java.sql.SQLException;
import java.util.Collection;

public interface ToDoItems {

    DBTodo create(DBTodo dbTodo) throws SQLException;

    Collection<DBTodo> findAll() throws SQLException;

    DBTodo findById(int id) throws SQLException;

    Collection<DBTodo> findByDoneStatus(boolean isDone) throws SQLException;

    Collection<DBTodo> findByAssignee(int assigneeId) throws SQLException;

    Collection<DBTodo> findByAssignee(DBPerson dbPerson) throws SQLException;

    Collection<DBTodo> findByUnassignedToDoItems() throws SQLException;

    DBTodo update(DBTodo dbTodo) throws SQLException;

    boolean deleteById(int id) throws SQLException;

}
