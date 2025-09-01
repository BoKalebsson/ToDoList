package io.github.bokalebsson;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        // Establish a connection:
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoit", "root", "root");
            System.out.println("Connection successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}