package io.github.bokalebsson.dao.connections;

import io.github.bokalebsson.interfaces.DatabaseConnection;

import java.sql.*;

public class MySQLDatabaseConnection implements DatabaseConnection {

    private final String URL = System.getenv("DB_URL");
    private final String USER = System.getenv("DB_USER");
    private final String PASSWORD = System.getenv("DB_PASSWORD");

    @Override
    public Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("⚠️ Database credentials not set in environment variables!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
