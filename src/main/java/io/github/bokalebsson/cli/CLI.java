package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.PeopleDAO;
import io.github.bokalebsson.dao.impl.ToDoItemsDAO;

import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);
    private final PeopleCLI peopleCLI = new PeopleCLI(new PeopleDAO(), scanner);
    private final ToDoItemsCLI toDoItemsCLI = new ToDoItemsCLI(new ToDoItemsDAO(), scanner);
    private boolean running = true;

    public void start() {
        System.out.println("=== ToDo Application CLI ===");

        while (running) {
            printMainMenu();

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> handlePersonMenu();
                case "2" -> handleToDoMenu();
                case "3" -> exitApplication();
                default -> System.out.println("⚠️ Invalid option. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Handle persons");
        System.out.println("2. Handle ToDo-items");
        System.out.println("3. Exit");
    }

    private void handlePersonMenu() {
        peopleCLI.start();
    }

    private void handleToDoMenu() {
        toDoItemsCLI.start();
    }

    private void exitApplication() {
        System.out.println("\n👋 Exiting Application...");
        running = false;
    }

}
