package io.github.bokalebsson.cli;

import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);
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
        System.out.println("Soon.");
    }

    private void handleToDoMenu() {
        System.out.println("Soon.");
    }

    private void exitApplication() {
        System.out.println("\n👋 Exiting Application...");
        running = false;
    }

}
