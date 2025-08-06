package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.model.AppUser;
import io.github.bokalebsson.model.AppRole;

import java.util.Scanner;

public class AppUserCLI {

    private final AppUserDAOCollection appUserDAO;
    private final Scanner scanner;

    public AppUserCLI(AppUserDAOCollection appUserDAO, Scanner scanner) {
        this.appUserDAO = appUserDAO;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- AppUser Menu ---");
            System.out.println("1. List all users");
            System.out.println("2. Add new user");
            System.out.println("0. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listUsers();
                    break;
                case "2":
                    addUser();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void listUsers() {
        System.out.println("\nListing all users:");
        for (AppUser user : appUserDAO.findAll()) {
            System.out.println(user);
        }
    }

    private void addUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Select role:");
        System.out.println("1. ROLE_APP_ADMIN");
        System.out.println("2. ROLE_APP_USER");
        System.out.print("Choose role: ");

        AppRole role = null;
        while (role == null) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    role = AppRole.ROLE_APP_ADMIN;
                    break;
                case "2":
                    role = AppRole.ROLE_APP_USER;
                    break;
                default:
                    System.out.print("Invalid choice, please select 1 or 2: ");
            }
        }

        AppUser newUser = new AppUser(username, password, role);
        appUserDAO.persist(newUser);
        System.out.println("User added: " + newUser);
    }
}