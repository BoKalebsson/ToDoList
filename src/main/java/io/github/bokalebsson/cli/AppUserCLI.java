package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.model.AppUser;
import io.github.bokalebsson.model.AppRole;

import java.util.Scanner;
import java.util.NoSuchElementException;

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
            System.out.println("3. Remove user");
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
                case "3":
                    removeUser();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice, please try again.");
            }
        }
    }

    private void listUsers() {
        System.out.println("\nListing all users:\n");
        for (AppUser user : appUserDAO.findAll()) {
            System.out.println(user);
        }
    }

    private void addUser() {
        String username;
        while (true) {
            System.out.print("Enter username (or type 'exit' to cancel): ");
            username = scanner.nextLine();

            if (username.equalsIgnoreCase("exit")) {
                System.out.println("User creation cancelled.");
                return;
            }

            if (username.trim().isEmpty()) {
                System.out.println("\nError: Username cannot be empty. Please enter a valid username.");
                continue;
            }

            try {
                appUserDAO.findByUsername(username);
                System.out.println("\nError: Username '" + username + "' already exists. Try another.");
            } catch (NoSuchElementException e) {
                break;
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("\nSelect role:");
        System.out.println("1. ROLE_APP_ADMIN");
        System.out.println("2. ROLE_APP_USER");
        System.out.println("3. ROLE_APP_GUEST");
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
                case "3":
                    role = AppRole.ROLE_APP_GUEST;
                    break;
                default:
                    System.out.print("\nInvalid choice, please select 1, 2 or 3: ");
            }
        }

        AppUser newUser = new AppUser(username, password, role);

        try {
            appUserDAO.persist(newUser);
            System.out.println("\nUser added: \n" + newUser);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public void removeUser() {
        System.out.println("\n=== Remove User ===");

        System.out.print("Enter the username of the user to remove: ");
        String username = scanner.nextLine().trim();

        try {
            // Try to find the user
            AppUser user = appUserDAO.findByUsername(username);
            System.out.println("Found: " + user);

            // Confirm deletion
            System.out.print("Are you sure you want to remove this user? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y")) {
                appUserDAO.remove(username);
                System.out.println("User successfully removed.");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred: " + e.getMessage());
        }
    }
}