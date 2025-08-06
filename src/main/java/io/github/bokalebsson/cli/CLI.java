package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;

import java.util.Scanner;

public class CLI {

    private final Scanner scanner = new Scanner(System.in);
    private final AppUserDAOCollection appUserDAO;
    private final PersonDAOCollection personDAO;
    private final ToDoItemDAOCollection toDoItemDAO;
    private final ToDoItemTaskDAOCollection toDoItemTaskDAO;

    public CLI(AppUserDAOCollection appUserDAO, PersonDAOCollection personDAO,
               ToDoItemDAOCollection toDoItemDAO, ToDoItemTaskDAOCollection toDoItemTaskDAO) {
        this.appUserDAO = appUserDAO;
        this.personDAO = personDAO;
        this.toDoItemDAO = toDoItemDAO;
        this.toDoItemTaskDAO = toDoItemTaskDAO;
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMainMenu();

            String choice = scanner.nextLine();

            switch (choice) {
                case "0":
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Manage Persons");
        System.out.println("2. Manage ToDoItems");
        System.out.println("3. Manage ToDoItemTasks");
        System.out.println("4. Manage AppUsers");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }
}
