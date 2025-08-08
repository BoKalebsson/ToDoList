package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.PersonDAO;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.AppUserDAO;
import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.model.AppUser;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.util.UserInputManager;

import java.util.Collection;
import java.util.Scanner;

public class PersonCLI {

    private final Scanner scanner = new Scanner(System.in);

    private final PersonDAO personDAO;
    private final AppUserDAO appUserDAO;

    public PersonCLI(PersonDAO personDAO, AppUserDAO appUserDAO) {
        this.personDAO = personDAO;
        this.appUserDAO = appUserDAO;
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Person Menu ---");
            System.out.println("1. Create Person");
            System.out.println("2. List All Persons");
            System.out.println("3. Remove Person");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createPerson();
                    break;
                case "2":
                    listAllPersons();
                    break;
                case "3":
                    removePerson();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public void createPerson() {
        System.out.println("\n--- Create Person ---");

        String firstName = UserInputManager.readValidName("Enter first name: ");
        String lastName = UserInputManager.readValidName("Enter last name: ");
        String email = UserInputManager.readValidEmail("Enter email: ");

        AppUser selectedAppUser = chooseAppUserOrGuest();

        Person person;
        if (selectedAppUser == null) {
            // No AppUser selected – use constructor that assigns Guest by default
            person = new Person(firstName, lastName, email);
        } else {
            person = new Person(firstName, lastName, email, selectedAppUser);
        }

        try {
            personDAO.persist(person);
            System.out.println("Person created: " + person.getFirstName() + " " + person.getLastName());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private AppUser chooseAppUserOrGuest() {
        Collection<AppUser> appUsers = appUserDAO.findAll();
        if (appUsers.isEmpty()) {
            System.out.println("No AppUsers found. Guest will be used by default.");
            return null;
        }

        System.out.println("Available AppUsers:");
        int index = 1;
        for (AppUser user : appUsers) {
            System.out.println(index + ". " + user.getUsername() + " (" + user.getRole() + ")");
            index++;
        }
        System.out.println("0. Use Guest");

        int choice = UserInputManager.readIntInRange("Choose AppUser by number (or 0 for Guest): ", 0, appUsers.size());

        if (choice == 0) {
            return null;
        }

        // Get AppUser by index
        int i = 1;
        for (AppUser user : appUsers) {
            if (i == choice) return user;
            i++;
        }

        System.out.println("Invalid choice, defaulting to Guest.");
        return null;
    }

    public void listAllPersons() {
        System.out.println("\n--- All Persons ---");
        Collection<Person> persons = personDAO.findAll();

        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }

        for (Person person : persons) {
            System.out.println(person.getId() + ": " + person.getFirstName() + " " +
                    person.getLastName() + " - " +
                    person.getCredentials().getUsername());
        }
    }

    public void removePerson() {
        System.out.println("\n=== Remove Person ===");

        System.out.print("Enter the ID of the person to remove: ");
        int id;

        try {
            // Try to parse ID from input
            id = Integer.parseInt(scanner.nextLine().trim());

            // Try to fetch the person (may throw IllegalArgumentException)
            Person person = personDAO.findById(id);

            System.out.println("Found: " + person);
            System.out.print("Are you sure you want to remove this person? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y")) {
                personDAO.remove(id);
                System.out.println("Person successfully removed.");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Must be a number.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}