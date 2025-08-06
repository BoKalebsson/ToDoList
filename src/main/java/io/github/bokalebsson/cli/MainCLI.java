package io.github.bokalebsson.cli;

import java.util.Scanner;

import io.github.bokalebsson.cli.PersonCLI;
import io.github.bokalebsson.cli.ToDoItemCLI;
import io.github.bokalebsson.cli.ToDoItemTaskCLI;
import io.github.bokalebsson.cli.AppUserCLI;

import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;

public class MainCLI {

    private static final Scanner scanner = new Scanner(System.in);

    private final PersonCLI personCLI;
    private final ToDoItemCLI toDoItemCLI;
    private final ToDoItemTaskCLI toDoItemTaskCLI;
    private final AppUserCLI appUserCLI;

    public MainCLI(AppUserDAOCollection appUserDAO, PersonDAOCollection personDAO,
                   ToDoItemDAOCollection toDoItemDAO, ToDoItemTaskDAOCollection toDoItemTaskDAO) {
        this.personCLI = new PersonCLI(personDAO, appUserDAO);
        this.toDoItemCLI = new ToDoItemCLI(toDoItemDAO, personDAO, scanner);
        this.toDoItemTaskCLI = new ToDoItemTaskCLI(toDoItemTaskDAO, toDoItemDAO, personDAO, scanner);
        this.appUserCLI = new AppUserCLI(appUserDAO, scanner);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n==== Main Menu ====");
            System.out.println("1. Manage Persons");
            System.out.println("2. Manage ToDoItems");
            System.out.println("3. Manage ToDoItemTasks");
            System.out.println("4. Manage AppUsers");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": personCLI.run(); break;
                case "2": toDoItemCLI.run(); break;
                case "3": toDoItemTaskCLI.run(); break;
                case "4": appUserCLI.run(); break;
                case "0":
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}