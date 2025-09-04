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


    //TODO: Refactor the code to throw SQL exceptions, and let other classes and/or methods handle the error handling
    // to inform the user that something went wrong.

    @Override
    public DBPerson create(DBPerson dbPerson) throws SQLException {
        String sql = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
             ) {

            preparedStatement.setString(1, dbPerson.getFirstName());
            preparedStatement.setString(2, dbPerson.getLastName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("⚠️ Creating person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return new DBPerson(generatedId, dbPerson.getFirstName(), dbPerson.getLastName());
                } else {
                    throw new SQLException("⚠️ Creating person failed, no ID obtained.");
                }
            }
        }
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

        String sql = "SELECT person_id, first_name, last_name FROM person WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ) {

            // Set the parameter in preparedStatement to the id to search for in the database:
            preparedStatement.setInt(1, id);

            // Execute the query:
            ResultSet resultSet = preparedStatement.executeQuery();

            // Return the person found:
            if (resultSet.next()){
                return new DBPerson(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
            }

        } catch (SQLException e) {
        System.err.println("❌ Something went wrong finding the person:");
        System.err.println("Error message: " + e.getMessage());
        System.err.println("SQL state: " + e.getSQLState());
        System.err.println("Error code: " + e.getErrorCode());
    }
        return null;
    }

    @Override
    public Collection<DBPerson> findByName(String name) {
        List<DBPerson> persons = new ArrayList<>();

        String sql = "SELECT person_id, first_name, last_name FROM person WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ){

            // Set the parameter in preparedStatement to the name to search for in the database:
            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            // Execute the query:
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                persons.add(new DBPerson(id, firstName, lastName));
            }

        } catch (SQLException e) {
            System.err.println("❌ Something went wrong finding the person:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            return Collections.emptyList();
        }
        return persons;
    }

    @Override
    public DBPerson update(DBPerson dbPerson) {

        String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setString(1, dbPerson.getFirstName());
            preparedStatement.setString(2, dbPerson.getLastName());
            preparedStatement.setInt(3, dbPerson.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return dbPerson;
            } else {
                System.out.println("ℹ️ No person found with id: " + dbPerson.getId());
                return null;
            }

        } catch (SQLException e) {
            System.err.println("❌ Something went wrong updating the person:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {

        String sql = "DELETE FROM person WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("❌ Something went wrong deleting the person:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
            return false;
        }
    }
}
