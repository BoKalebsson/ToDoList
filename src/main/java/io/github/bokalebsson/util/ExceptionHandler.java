package io.github.bokalebsson.util;

import java.sql.SQLException;

public class ExceptionHandler {

    public static void handle(SQLException e) {
        System.out.println("⚠️ SQL Error: " + e.getMessage());
        System.out.println("🔹 SQL State: " + e.getSQLState());
        System.out.println("🔹 Error Code: " + e.getErrorCode());
    }

}
