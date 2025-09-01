package io.github.bokalebsson;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/todoit";
        String user = "root";
        String password = "root";

        // Establish a connection:
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connection successful!");

            // Prepare a statement:
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM person");

            // Run the query:
            ResultSet resultSet = ps.executeQuery();

            // Print the result:
            while (resultSet.next()){
                System.out.println(resultSet.getInt("person_id") + " - "
                        + resultSet.getString("first_name") + " "
                        + resultSet.getString("last_name"));
            }


        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to the database:");
            System.err.println("Error message: " + e.getMessage());
            System.err.println("SQL state: " + e.getSQLState());
            System.err.println("Error code: " + e.getErrorCode());
        }

    }

}