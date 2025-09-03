package io.github.bokalebsson.dao.connections;

import java.sql.*;

public class MySQLDatabaseConnection implements DatabaseConnection {

    private final String URL = "jdbc:mysql://localhost:3306/todoit";
    private final String USER = "root";
    private final String PASSWORD = "root";

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
