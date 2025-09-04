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
    public Collection<DBPerson> findAll() throws SQLException {
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
        }
        return persons;
    }

    @Override
    public DBPerson findById(int id) throws SQLException{

        String sql = "SELECT person_id, first_name, last_name FROM person WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
             ) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new DBPerson(
                            resultSet.getInt("person_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public Collection<DBPerson> findByName(String name) throws SQLException {
        List<DBPerson> persons = new ArrayList<>();

        String sql = "SELECT person_id, first_name, last_name FROM person WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
             ) {

            preparedStatement.setString(1, "%" + name + "%");
            preparedStatement.setString(2, "%" + name + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("person_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    persons.add(new DBPerson(id, firstName, lastName));
                }
            }
        }
        return persons;
    }

    @Override
    public DBPerson update(DBPerson dbPerson) throws SQLException{

        String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1, dbPerson.getFirstName());
            preparedStatement.setString(2, dbPerson.getLastName());
            preparedStatement.setInt(3, dbPerson.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return dbPerson;
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException{

        String sql = "DELETE FROM person WHERE person_id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        }
    }
}
