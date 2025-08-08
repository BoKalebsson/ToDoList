package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.model.ToDoItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        String title = "";
        do {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        } while (title.isEmpty());

        String description = "";
        do {
            System.out.print("Enter description: ");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Description cannot be empty. Please enter a valid description.");
            }
        } while (description.isEmpty());

        LocalDate dueDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        do {
            System.out.print("Enter a deadline (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
            try {
                dueDate = LocalDate.parse(input, formatter);
                if (dueDate.isBefore(LocalDate.now())) {
                    System.out.println("Deadline cannot be in the past. Please enter a future date or today.");
                    dueDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
            }
        } while (dueDate == null);

        System.out.println("Select a person ID to assign a creator for this ToDoItem:");
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
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter a numeric ID.");
            } catch (IllegalArgumentException e) {
                System.out.println("No person found with ID: " + idInput + ". Try again.");
            }
        }

        ToDoItem newItem = new ToDoItem(title, description, dueDate, assignedPerson);
        toDoItemDAO.persist(newItem);
        System.out.println("\nToDoItem added: \n" + newItem);
    }
}