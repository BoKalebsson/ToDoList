package io.github.bokalebsson.dao.connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnection {

    /**
     * Returns a JDBC Connection to the database.
     *
     * <p>Classes that access the database should use this method
     * without worrying about how the connection is configured.</p>
     *
     * @return a new {@link Connection}
     * @throws SQLException if a database access error occurs
     */
    Connection getConnection() throws SQLException;

}
