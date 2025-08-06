package io.github.bokalebsson.cli;

import java.util.Scanner;

public class PersonCLI {

    public void handleMenu(Scanner scanner) {
        System.out.println("=== Person Menu ===");
        System.out.println("1. Create Person");
        System.out.println("2. List All Persons");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Create Person (Not implemented yet)");
                break;
            case "2":
                System.out.println("List All Persons (Not implemented yet)");
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice");
        }
    }
}