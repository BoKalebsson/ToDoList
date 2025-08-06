package io.github.bokalebsson.cli;

import java.util.Scanner;

public class ToDoItemTaskCLI {

    public void handleMenu(Scanner scanner) {
        System.out.println("=== ToDoItemTask Menu ===");
        System.out.println("1. Create ToDoItemTask");
        System.out.println("2. List All ToDoItemTasks");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                System.out.println("Create ToDoItemTask (Not implemented yet)");
                break;
            case "2":
                System.out.println("List All ToDoItemTasks (Not implemented yet)");
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid choice");
        }
    }
}