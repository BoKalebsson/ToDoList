package io.github.bokalebsson.dao.database;

import io.github.bokalebsson.dao.connections.DatabaseConnection;
import io.github.bokalebsson.dao.connections.MySQLDatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PeopleDAO implements People {

    private final DatabaseConnection databaseConnection = new MySQLDatabaseConnection();

    @Override
    public DBPerson create(DBPerson dbPerson) {
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
