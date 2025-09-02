package io.github.bokalebsson;

import io.github.bokalebsson.dao.database.DatabaseConnection;
import io.github.bokalebsson.dao.database.MySQLDatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        // Create a new MySQL-connection:
        DatabaseConnection databaseConnection = new MySQLDatabaseConnection();

        // Establish a connection to the database:
        try (Connection connection = databaseConnection.getConnection()){
            System.out.println("✅ Connection successful!");

            /* This section will be used to test methods from the database-classes. */

            // Prepare a statement:
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM person WHERE first_name = ?");
            ps.setString(1, "Erik");

            // Run the query:
            ResultSet resultSet = ps.executeQuery();

            // Print the result:
            while (resultSet.next()){

                int personId = resultSet.getInt("person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                System.out.println("Id: " + personId + " | " + "Name: " + firstName + " " + lastName);

            }

/*            // Working SQL-injection: Just change the ID to match the person.
            String userInput = "' OR person_id = 3; --";
            Statement stmt = connection.createStatement();
            String sql = "DELETE FROM person WHERE first_name = '" + userInput;
            stmt.executeUpdate(sql);*/

        } catch (SQLException e){
            System.err.println("❌ Failed to connect to the database:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }

    }

}