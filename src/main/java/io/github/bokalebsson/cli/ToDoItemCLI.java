package io.github.bokalebsson.cli;

import java.util.Scanner;

public class ToDoItemCLI {

    public void handleMenu(Scanner scanner) {
        System.out.println("=== ToDoItem Menu ===");
        System.out.println("1. Create ToDoItem");
        System.out.println("2. List All ToDoItems");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Create ToDoItem (Not implemented yet)");
                break;
            case "2":
                System.out.println("List All ToDoItems (Not implemented yet)");
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice");
        }
    }
}