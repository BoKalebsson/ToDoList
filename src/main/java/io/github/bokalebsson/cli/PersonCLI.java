package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.model.Person;

import java.util.Scanner;

public class PersonCLI {

    private final PersonDAOCollection personDAO;
    private final Scanner scanner;

    public PersonCLI(PersonDAOCollection personDAO, Scanner scanner) {
        this.personDAO = personDAO;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Person Menu ---");
            System.out.println("1. List all persons");
            System.out.println("2. Add a new person");
            System.out.println("0. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listPersons();
                    break;
                case "2":
                    addPerson();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void listPersons() {
        System.out.println("\nListing all persons:");
        for (Person p : personDAO.findAll()) {
            System.out.println(p);
        }
    }

    private void addPerson() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        // For now, we won't link AppUser in this example
        Person newPerson = new Person(firstName, lastName, email, null);

        personDAO.persist(newPerson);
        System.out.println("Person added: " + newPerson);
    }
}