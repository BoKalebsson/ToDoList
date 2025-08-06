package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.model.AppUser;

import java.util.Collection;
import java.util.Scanner;

public class PersonCLI {

    private final PersonDAOCollection personDAO;
    private final AppUserDAOCollection appUserDAO;
    private final Scanner scanner;

    public PersonCLI(PersonDAOCollection personDAO, AppUserDAOCollection appUserDAO, Scanner scanner) {
        this.personDAO = personDAO;
        this.appUserDAO = appUserDAO;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Person Menu ---");
            System.out.println("1. List all persons");
            System.out.println("2. Add new person");
            System.out.println("0. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> listPersons();
                case "2" -> addPerson();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void listPersons() {
        Collection<Person> persons = personDAO.findAll();
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }
        System.out.println("\nList of persons:");
        for (Person p : persons) {
            System.out.println(p);
        }
    }

    private void addPerson() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        AppUser user = selectAppUser();
        if (user == null) {
            System.out.println("No valid user selected. Cancelling person creation.");
            return;
        }

        try {
            Person newPerson = new Person(firstName, lastName, email, user);
            personDAO.persist(newPerson);
            System.out.println("Person created: " + newPerson);
        } catch (Exception e) {
            System.out.println("Error creating person: " + e.getMessage());
        }
    }

    private AppUser selectAppUser() {
        Collection<AppUser> users = appUserDAO.findAll();
        if (users.isEmpty()) {
            System.out.println("No AppUsers found. You need to create users first.");
            return null;
        }

        System.out.println("Select an AppUser by number:");
        int i = 1;
        for (AppUser user : users) {
            System.out.println(i++ + ". " + user);
        }
        System.out.print("Your choice: ");

        String input = scanner.nextLine();
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < users.size()) {
                // To get element at index from Collection, convert to array:
                AppUser[] userArray = users.toArray(new AppUser[0]);
                return userArray[index];
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please enter a number.");
        }
        return null;
    }
}