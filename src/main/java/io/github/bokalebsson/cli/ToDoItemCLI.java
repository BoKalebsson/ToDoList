package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.model.ToDoItem;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ToDoItemCLI {

    private final ToDoItemDAOCollection toDoItemDAO;
    private final PersonDAOCollection personDAO;
    private final Scanner scanner;

    public ToDoItemCLI(ToDoItemDAOCollection toDoItemDAO, PersonDAOCollection personDAO, Scanner scanner) {
        this.toDoItemDAO = toDoItemDAO;
        this.personDAO = personDAO;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- ToDoItem Menu ---");
            System.out.println("1. List all ToDoItems");
            System.out.println("2. Add new ToDoItem");
            System.out.println("0. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listToDoItems();
                    break;
                case "2":
                    addToDoItem();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void listToDoItems() {
        System.out.println("\nListing all ToDoItems:\n");
        for (ToDoItem item : toDoItemDAO.findAll()) {
            System.out.println(item);
        }
    }

    private void addToDoItem() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        LocalDate dueDate = null;
        while (dueDate == null) {
            System.out.print("Enter due date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try {
                dueDate = LocalDate.parse(dateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format, please try again.");
            }
        }

        System.out.println("Select a person ID to assign this ToDoItem to:");
        for (Person p : personDAO.findAll()) {
            System.out.println(p.getId() + ": " + p.getFirstName() + " " + p.getLastName());
        }

        Person assignedPerson = null;
        while (assignedPerson == null) {
            System.out.print("Enter person ID: ");
            String idInput = scanner.nextLine();
            try {
                int id = Integer.parseInt(idInput);
                assignedPerson = personDAO.findById(id);
                if (assignedPerson == null) {
                    System.out.println("No person found with that ID, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter a numeric ID.");
            }
        }

        ToDoItem newItem = new ToDoItem(title, description, dueDate, assignedPerson);
        toDoItemDAO.persist(newItem);
        System.out.println("ToDoItem added: " + newItem);
    }
}