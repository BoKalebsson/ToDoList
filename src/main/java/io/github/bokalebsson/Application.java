package io.github.bokalebsson;

import io.github.bokalebsson.cli.MainCLI;
import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.util.ApplicationDataManager;
import io.github.bokalebsson.model.*;

import java.time.LocalDate;
import java.util.Optional;

public class Application {

    private final String dataFolderPath;

    private PersonDAOCollection personDAO;
    private AppUserDAOCollection appUserDAO;
    private ToDoItemDAOCollection toDoItemDAO;
    private ToDoItemTaskDAOCollection toDoItemTaskDAO;
    private ApplicationDataManager applicationDataManager;

    private MainCLI cli;

    // Constructor for filepathing to JSON and properties-files:
    public Application(String dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
    }

    public void start() {
        loadData();

        // Comment out runClI() to disable CLI-interface:
        runCLI();

        // Comment out workArea to disable default-DAO-showcase:
//        workArea();
        saveData();
        System.out.println("Application closed. Data saved. ✅");
    }

    private void loadData() {
        // Create DAO collections:
        personDAO = new PersonDAOCollection();
        toDoItemDAO = new ToDoItemDAOCollection();
        toDoItemTaskDAO = new ToDoItemTaskDAOCollection();
        appUserDAO = new AppUserDAOCollection();

        // Create and load Application Data Manager:
        applicationDataManager = new ApplicationDataManager(
                dataFolderPath, personDAO, appUserDAO, toDoItemDAO, toDoItemTaskDAO);

        applicationDataManager.loadData();
    }

    // Use this method to show the CLI-interface:
    private void runCLI() {
        cli = new MainCLI(appUserDAO, personDAO, toDoItemDAO, toDoItemTaskDAO);
        cli.run();
    }

    // Use this method to show that the application works:
    private void workArea() {

        // Create a new AppUser:
        AppUser user = new AppUser("Erik", "123456", AppRole.ROLE_APP_ADMIN);
        appUserDAO.persist(user);
        System.out.println(user);

        // Create another AppUser:
        AppUser user2 = new AppUser("Anna", "654321", AppRole.ROLE_APP_USER);
        appUserDAO.persist(user2);
        System.out.println(user2);

        // Create a new Person:
        Person erik = new Person("Erik", "Andersson", "erik@test.nu", user);
        personDAO.persist(erik);
        System.out.println(erik);

        // Create another Person:
        Person anna = new Person("Anna", "Svensson", "anna@test.nu", user2);
        personDAO.persist(anna);
        System.out.println(anna);

        // Create a new ToDoItem:
        ToDoItem item = new ToDoItem("Grocery shopping!",
                "Buy eggs and milk.", LocalDate.now().plusDays(7), erik);
        toDoItemDAO.persist(item);
        System.out.println(item);

        // Create another ToDoItem:
        ToDoItem item2 = new ToDoItem("Study Java!",
                "Finish the assignment!", LocalDate.now().plusDays(7), anna);
        toDoItemDAO.persist(item2);
        System.out.println(item2);

        // Create a new ToDoItemTask:
        ToDoItemTask task = new ToDoItemTask(item, erik);
        toDoItemTaskDAO.persist(task);
        System.out.println(task);

        // Create another ToDoItemTask:
        ToDoItemTask task2 = new ToDoItemTask(item2, anna);
        toDoItemTaskDAO.persist(task2);
        System.out.println(task2);
    }

    private void saveData() {
        applicationDataManager.saveData();
    }
}