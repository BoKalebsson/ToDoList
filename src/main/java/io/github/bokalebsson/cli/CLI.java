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
        printWelcomeBanner();

        while (running) {
            printMainMenu();

            System.out.print("🔹 Choose an option: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> handlePersonMenu();
                case "2" -> handleToDoMenu();
                case "3" -> exitApplication();
                default -> System.out.println("⚠️ Invalid input. Please try again.");
            }
        }
    }

    private void printWelcomeBanner() {
        System.out.println("\n====================================");
        System.out.println("     📝 Welcome to ToDo-Manager!");
        System.out.println("   Manage your tasks and people.");
        System.out.println("====================================");
    }

    private void printMainMenu() {
        System.out.println("\n====================================");
        System.out.println("             MAIN MENU");
        System.out.println("====================================");
        System.out.println("1. 🗂️ Manage People");
        System.out.println("2. 📌 Manage ToDo-items");
        System.out.println("3. 🔒 Exit Application");
        System.out.println("====================================");
    }

    private void handlePersonMenu() {
        try {
            peopleCLI.start();
        } catch (Exception e) {
            System.out.println("⚠️ An error occurred with the Person-menu: " + e.getMessage());
        }
    }

    private void handleToDoMenu() {
        try {
            toDoItemsCLI.start();
        } catch (Exception e) {
            System.out.println("⚠️ An error occurred with the ToDo-menu: " + e.getMessage());
        }
    }

    private void exitApplication() {
        System.out.println("\n====================================");
        System.out.println(" 👋 Thank you for using ToDo-Manager!");
        System.out.println(" 🗂️ All changes have been saved.");
        System.out.println(" 🔒 Application closed safely.");
        System.out.println("====================================");
        System.out.println("     Have a productive day! ✨");
        System.out.println("====================================");
        running = false;
    }

}
