package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.ToDoItemsDAO;
import io.github.bokalebsson.model.DBTodo;
import io.github.bokalebsson.util.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

public class ToDoItemsCLI {

    private final ToDoItemsDAO toDoItemsDAO;
    private final Scanner scanner;

    public ToDoItemsCLI(ToDoItemsDAO toDoItemsDAO, Scanner scanner) {
        this.toDoItemsDAO = toDoItemsDAO;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createToDo();
                case "2" -> listAllToDos();
                case "3" -> findById();
                case "4" -> findByDoneStatus();
                case "5" -> findByAssignee();
                case "6" -> findByUnassignedToDoItems();
                case "7" -> updateToDo();
                case "8" -> deleteToDo();
                case "0" -> running = false;
                default -> System.out.println("⚠️ Invalid option. Try again!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nToDo Menu:");
        System.out.println("1. Create ToDo-item");
        System.out.println("2. List all ToDo-items");
        System.out.println("3. Find ToDo-item by ID");
        System.out.println("4. Find ToDo-items by done status");
        System.out.println("5. Find ToDo-items by assignee ID");
        System.out.println("6. Find unassigned ToDo-items");
        System.out.println("7. Update ToDo-item");
        System.out.println("8. Delete ToDo-item");
        System.out.println("0. Back to main menu");
    }

    private void createToDo() {
        // Ask for title (not empty):
        String title = "";

        while (title.isBlank()) {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();

            if (title.isBlank()) {
                System.out.println("⚠️ Title cannot be empty.");
            }
        }

        // Ask for description (optional):
        System.out.print("Enter description (optional): ");
        String description = scanner.nextLine().trim();

        // Ask for deadline (optional) with helper-method:
        LocalDate deadline = readDeadline();

        // Set done to false when creating a new task:
        boolean done = false;

        // Ask for assignee-id (optional, leave empty for unassigned):
        Integer assigneeId = null;
        System.out.print("Enter assignee ID or press Enter to leave unassigned: ");
        String assigneeInput = scanner.nextLine().trim();
        if (!assigneeInput.isEmpty()) {
            try {
                assigneeId = Integer.parseInt(assigneeInput);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid ID format, leaving unassigned.");
                assigneeId = null;
            }
        }

        // Create the task:
        DBTodo todo = new DBTodo(title, description, deadline, done, assigneeId);

        // Save to database:
        try {
            DBTodo created = toDoItemsDAO.create(todo);
            System.out.println("✅ ToDo-item created: ID " + created.getId() + " | " + created.getTitle());
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void listAllToDos() {
        try {
            Collection<DBTodo> todos = toDoItemsDAO.findAll();

            if (todos.isEmpty()) {
                System.out.println("⚠️ No ToDo-items found in the database.");
                return;
            }

            System.out.println("\n=== ToDo-items in Database ===");
            for (DBTodo todo : todos) {
                System.out.println(todo);
            }

        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findById() {
        System.out.print("Enter ToDo-item ID: ");
        String input = scanner.nextLine().trim();

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid ID format. Please enter a valid number.");
            return;
        }

        try {
            DBTodo todo = toDoItemsDAO.findById(id);

            if (todo == null) {
                System.out.println("⚠️ No ToDo-item found with ID " + id);
            } else {
                System.out.println(todo);
            }
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findByDoneStatus() {
        Boolean isDone = null;

        while (isDone == null) {
            System.out.print("Show completed ToDo-items? (y = yes, n = show only not completed): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) {
                isDone = true;
            } else if (input.equals("n")) {
                isDone = false;
            } else {
                System.out.println("⚠️ Invalid input. Please enter 'y' or 'n'.");
            }
        }

        try {
            Collection<DBTodo> todos = toDoItemsDAO.findByDoneStatus(isDone);

            if (todos.isEmpty()) {
                System.out.println("⚠️ No ToDo-items found for your selection. (Completed: " + (isDone ? "Yes" : "No") + ")");
            } else {
                System.out.println("\n=== ToDo-items ===");
                for (DBTodo todo : todos) {
                    System.out.println(todo);
                }
            }
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findByAssignee() {
        System.out.print("Enter assignee ID: ");
        String input = scanner.nextLine().trim();

        int assigneeId;
        try {
            assigneeId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid ID format.");
            return;
        }

        try {
            Collection<DBTodo> todos = toDoItemsDAO.findByAssignee(assigneeId);

            if (todos.isEmpty()) {
                System.out.println("⚠️ No ToDo-items found for person with ID: " + assigneeId);
            } else {
                System.out.println("\n=== ToDo-items assigned to person with ID: " + assigneeId + " ===");
                for (DBTodo todo : todos) {
                    System.out.println(todo);
                }
            }
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }

    private void findByUnassignedToDoItems() {
        try {
            Collection<DBTodo> todos = toDoItemsDAO.findByUnassignedToDoItems();

            if (todos.isEmpty()) {
                System.out.println("⚠️ No unassigned ToDo-items found.");
            } else {
                System.out.println("\n=== Unassigned ToDo-items ===");
                for (DBTodo todo : todos) {
                    System.out.println(todo);
                }
            }
        } catch (SQLException e) {
            ExceptionHandler.handle(e);
        }
    }
    private void updateToDo() {}
    private void deleteToDo() {}

    private LocalDate readDeadline() {
        LocalDate deadline = null;

        while (true) {
            System.out.print("Enter deadline (yyyy-MM-dd) or press Enter to skip: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            try {
                deadline = LocalDate.parse(input);
                break;
            } catch (Exception e) {
                System.out.println("⚠️ Invalid date format. Please use yyyy-MM-dd.");
            }
        }
        return deadline;
    }

}
