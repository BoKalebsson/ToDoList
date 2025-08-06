package io.github.bokalebsson.cli;

import java.util.Scanner;

public class AppUserCLI {

    public void handleMenu(Scanner scanner) {
        System.out.println("=== AppUser Menu ===");
        System.out.println("1. Create AppUser");
        System.out.println("2. List All AppUsers");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Create AppUser (Not implemented yet)");
                break;
            case "2":
                System.out.println("List All AppUsers (Not implemented yet)");
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice");
        }
    }
}