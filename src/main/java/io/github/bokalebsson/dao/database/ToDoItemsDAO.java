package io.github.bokalebsson.dao.database;

import io.github.bokalebsson.dao.connections.DatabaseConnection;
import io.github.bokalebsson.dao.connections.MySQLDatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
        List<DBTodo> todos = new ArrayList<>();

        String sql = "SELECT * FROM todo_item";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("todo_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");

                Date deadlineDate = resultSet.getDate("deadline");
                LocalDate deadline = (deadlineDate != null) ? deadlineDate.toLocalDate() : null;

                boolean done = resultSet.getBoolean("done");
                int assigneeId = resultSet.getInt("assignee_id");
                todos.add(new DBTodo(id, title, description, deadline, done, assigneeId));
            }
        }
        return todos;
    }

    @Override
    public DBTodo findById(int id) throws SQLException {

        String sql = "SELECT * FROM todo_item WHERE todo_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Date deadlineDate = resultSet.getDate("deadline");
                    LocalDate deadline = (deadlineDate != null) ? deadlineDate.toLocalDate() : null;


                    return new DBTodo(
                            resultSet.getInt("todo_id"),
                            resultSet.getString("title"),
                            resultSet.getString("description"),
                            deadline,
                            resultSet.getBoolean("done"),
                            resultSet.getInt("assignee_id")
                    );
                }
            }
        }
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
