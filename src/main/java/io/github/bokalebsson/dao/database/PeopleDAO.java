package io.github.bokalebsson.dao.database;

import io.github.bokalebsson.dao.connections.DatabaseConnection;
import io.github.bokalebsson.dao.connections.MySQLDatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PeopleDAO implements People {

    private final DatabaseConnection databaseConnection = new MySQLDatabaseConnection();

    @Override
    public DBPerson create(DBPerson dbPerson) {
        String sql = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ){

            // Set the values for first_name and last_name:
            preparedStatement.setString(1, dbPerson.getFirstName());
            preparedStatement.setString(2, dbPerson.getLastName());

            // Check if rows in database got affected:
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("⚠️ Creating person failed, no rows affected.");
            }

            // Fetching the generated person_id from database:
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return new DBPerson(generatedId, dbPerson.getFirstName(), dbPerson.getLastName());
                } else {
                    throw new SQLException("⚠️ Creating person failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Something went wrong creating the person:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }
        return null;
    }

    @Override
    public Collection<DBPerson> findAll() {
        List<DBPerson> persons = new ArrayList<>();

        String sql = "SELECT person_id, first_name, last_name FROM person";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int id = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                persons.add(new DBPerson(id, firstName, lastName));
            }

        } catch (SQLException e) {
            System.err.println("❌ Something went wrong while fetching persons:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            return Collections.emptyList();
        }
        return persons;
    }

    @Override
    public DBPerson findById(int id) {
        return null;
    }

    @Override
    public Collection<DBPerson> findByName(String name) {
        return List.of();
    }

    @Override
    public DBPerson update(DBPerson dbPerson) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
