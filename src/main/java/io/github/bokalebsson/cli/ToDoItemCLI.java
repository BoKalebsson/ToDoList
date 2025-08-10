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
            System.out.println("\n--- ToDo-Item Menu ---");
            System.out.println("1. List all ToDo-Items");
            System.out.println("2. Add new ToDo-Item");
            System.out.println("3. Remove ToDo-Item");
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
                case "3":
                    removeToDoItem();
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
        System.out.println("\nListing all ToDo-Items:\n");
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
        System.out.println("\nToDo-Item added: \n" + newItem);
    }

    public void removeToDoItem() {
        System.out.println("\n=== Remove ToDo-Item ===");

        System.out.print("Enter the ID of the ToDo-Item to remove: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine().trim());

            ToDoItem item = toDoItemDAO.findById(id);

            System.out.println("\nFound: \n" + item);
            System.out.print("Are you sure you want to remove this ToDo-Item? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y")) {
                toDoItemDAO.remove(id);
                System.out.println("ToDo-Item successfully removed.");
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Must be a number.");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}