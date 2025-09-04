package io.github.bokalebsson;

import io.github.bokalebsson.dao.database.DBPerson;
import io.github.bokalebsson.dao.database.People;
import io.github.bokalebsson.dao.database.PeopleDAO;

import java.sql.*;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        // Create a PeopleDAO instance:
        People peopleDao = new PeopleDAO();

        // Create a new DBPerson
        DBPerson newPerson = new DBPerson("Märta", "Johansson");
        DBPerson createdPerson = peopleDao.create(newPerson);

        if (createdPerson != null) {
            System.out.println("✅ Person created successfully: \n" + createdPerson);
        } else {
            System.out.println("⚠️ Person could not be created.");
        }

        // Call findAll() to fetch all persons from the database:
        Collection<DBPerson> persons = peopleDao.findAll();

        // Print the results:
        if (persons.isEmpty()) {
            System.out.println("⚠️ No persons found in the database.");
        } else {
            System.out.println("✅ Found " + persons.size() + " persons:");
            for (DBPerson p : persons) {
                System.out.println(p);
            }
        }

        // Fetch a person with findById:
        DBPerson foundPerson = peopleDao.findById(2);

        if (foundPerson != null) {
            System.out.println("✅ Person found: \n" + foundPerson);
        } else {
            System.out.println("⚠️ No person found.\n");
        }

    }

}