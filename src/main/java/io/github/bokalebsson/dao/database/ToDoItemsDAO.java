package io.github.bokalebsson.dao.database;

import io.github.bokalebsson.dao.connections.DatabaseConnection;
import io.github.bokalebsson.dao.connections.MySQLDatabaseConnection;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class ToDoItemsDAO implements ToDoItems {

    private final DatabaseConnection databaseConnection = new MySQLDatabaseConnection();

    @Override
    public DBTodo create(DBTodo dbTodo) throws SQLException {
        String sql = "INSERT INTO todo_item(title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?) ";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            preparedStatement.setString(1, dbTodo.getTitle());
            preparedStatement.setString(2, dbTodo.getDescription());
            preparedStatement.setDate(3, Date.valueOf(dbTodo.getDeadline()));
            preparedStatement.setBoolean(4, dbTodo.isDone());
            preparedStatement.setInt(5, dbTodo.getAssigneeId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("⚠️ Creating todo failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return new DBTodo(
                            generatedId,
                            dbTodo.getTitle(),
                            dbTodo.getDescription(),
                            dbTodo.getDeadline(),
                            dbTodo.isDone(),
                            dbTodo.getAssigneeId());
                } else {
                    throw new SQLException("⚠️ Creating todo failed, no ID obtained.");
                }
            }
        }
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
