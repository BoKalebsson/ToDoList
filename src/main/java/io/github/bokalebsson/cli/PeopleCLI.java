package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.PeopleDAO;
import io.github.bokalebsson.model.DBPerson;
import io.github.bokalebsson.util.ExceptionHandler;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class PeopleCLI {

    private final PeopleDAO peopleDAO;
    private final Scanner scanner;

    public PeopleCLI(PeopleDAO peopleDAO, Scanner scanner) {
        this.peopleDAO = peopleDAO;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createPerson();
                case "2" -> listAllPeople();
                case "3" -> findById();
                case "4" -> findByName();
                case "5" -> updatePerson();
                case "6" -> deletePerson();
                case "0" -> running = false;
                default -> System.out.println("⚠️ Invalid option. Try again!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nPerson Menu:");
        System.out.println("1. Create person");
        System.out.println("2. List all people");
        System.out.println("3. Find person by ID");
        System.out.println("4. Find person by name");
        System.out.println("5. Update person");
        System.out.println("6. Delete person");
        System.out.println("0. Back to main menu");
    }

    private void createPerson() {
        String firstName = "";
        String lastName = "";

        while (firstName.isBlank()) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.isBlank()) {
                System.out.println("⚠️ First name cannot be empty.");
            }
        }

        while (lastName.isBlank()) {
            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.isBlank()) {
                System.out.println("⚠️ Last name cannot be empty.");
            }
        }

        DBPerson dbPerson = new DBPerson(firstName, lastName);

        try {
            DBPerson createdPerson = peopleDAO.create(dbPerson);
            System.out.println("✅ Person created: " + createdPerson.getFirstName() + " " + createdPerson.getLastName());
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void listAllPeople() {
        try {
            Collection<DBPerson> people = peopleDAO.findAll();

            if (people.isEmpty()) {
                System.out.println("⚠️ No people found in the database.");
                return;
            }

            System.out.println("\n=== People in Database ===");
            for (DBPerson person : people) {
                System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstName() + " " + person.getLastName());
            }

        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findById() {
        System.out.print("Enter person ID: ");
        String input = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid input. Please enter a valid number.");
            return;
        }

        try {
            DBPerson person = peopleDAO.findById(id);
            if (person == null) {
                System.out.println("⚠️ No person found with ID " + id);
            } else {
                System.out.println("ID: " + person.getId() + " | Name: " + person.getFirstName() + " " + person.getLastName());
            }
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findByName() {}
    private void updatePerson() {}
    private void deletePerson() {}
}
