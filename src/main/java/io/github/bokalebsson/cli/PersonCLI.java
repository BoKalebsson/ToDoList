package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.PersonDAO;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.AppUserDAO;
import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.model.AppUser;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.util.UserInputManager;

import java.util.Collection;

public class PersonCLI {

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
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = UserInputManager.readIntInRange("", 0, 2);

            switch (choice) {
                case 1:
                    createPerson();
                    break;
                case 2:
                    listAllPersons();
                    break;
                case 0:
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

        personDAO.persist(person);
        System.out.println("Person created: " + person.getFirstName() + " " + person.getLastName());
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

        // Get AppUser by index (without streams)
        int i = 1;
        for (AppUser user : appUsers) {
            if (i == choice) return user;
            i++;
        }

        // Should not reach here
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
}