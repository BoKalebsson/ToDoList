package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.model.ToDoItem;
import io.github.bokalebsson.model.ToDoItemTask;
import io.github.bokalebsson.model.Person;

import java.util.Scanner;

public class ToDoItemTaskCLI {

    private final ToDoItemTaskDAOCollection toDoItemTaskDAO;
    private final ToDoItemDAOCollection toDoItemDAO;
    private final PersonDAOCollection personDAO;
    private final Scanner scanner;

    public ToDoItemTaskCLI(ToDoItemTaskDAOCollection toDoItemTaskDAO,
                           ToDoItemDAOCollection toDoItemDAO,
                           PersonDAOCollection personDAO,
                           Scanner scanner) {
        this.toDoItemTaskDAO = toDoItemTaskDAO;
        this.toDoItemDAO = toDoItemDAO;
        this.personDAO = personDAO;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- ToDoItemTask Menu ---");
            System.out.println("1. List all ToDoItemTasks");
            System.out.println("2. Add new ToDoItemTask");
            System.out.println("0. Back to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void listTasks() {
        System.out.println("\nListing all ToDoItemTasks:\n");
        for (ToDoItemTask task : toDoItemTaskDAO.findAll()) {
            System.out.println(task);
        }
    }

    private void addTask() {
        System.out.println("Select a ToDoItem ID to assign task to:");
        for (ToDoItem item : toDoItemDAO.findAll()) {
            System.out.println(item.getId() + ": " + item.getTitle());
        }

        ToDoItem selectedItem = null;
        while (selectedItem == null) {
            System.out.print("Enter ToDoItem ID: ");
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                selectedItem = toDoItemDAO.findById(id);
                if (selectedItem == null) {
                    System.out.println("No ToDoItem found with that ID, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter a numeric ID.");
            }
        }

        System.out.println("Select a Person ID to assign the task to:");
        for (Person p : personDAO.findAll()) {
            System.out.println(p.getId() + ": " + p.getFirstName() + " " + p.getLastName());
        }

        Person assignedPerson = null;
        while (assignedPerson == null) {
            System.out.print("Enter Person ID: ");
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                assignedPerson = personDAO.findById(id);
                if (assignedPerson == null) {
                    System.out.println("No person found with that ID, try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter a numeric ID.");
            }
        }

        ToDoItemTask newTask = new ToDoItemTask(selectedItem, assignedPerson);
        toDoItemTaskDAO.persist(newTask);
        System.out.println("ToDoItemTask added: " + newTask);
    }
}