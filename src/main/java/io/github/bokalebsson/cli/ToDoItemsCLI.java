package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.ToDoItemsDAO;

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
        System.out.println("1. Create ToDo item");
        System.out.println("2. List all ToDo items");
        System.out.println("3. Find ToDo by ID");
        System.out.println("4. Find ToDos by done status");
        System.out.println("5. Find ToDos by assignee ID");
        System.out.println("6. Find unassigned ToDos");
        System.out.println("7. Update ToDo");
        System.out.println("8. Delete ToDo");
        System.out.println("0. Back to main menu");
    }

    private void createToDo() {}
    private void listAllToDos() {}
    private void findById() {}
    private void findByDoneStatus() {}
    private void findByAssignee() {}
    private void findByUnassignedToDoItems() {}
    private void updateToDo() {}
    private void deleteToDo() {}

}
