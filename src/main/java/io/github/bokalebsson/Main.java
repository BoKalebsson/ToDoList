package io.github.bokalebsson;

import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.dao.util.ApplicationDataManager;
import io.github.bokalebsson.model.*;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {

        // 1. STARTUP – Read data

        // Create filepath:
        String dataFolderPath = "data";

        // Create DAO collections:
        PersonDAOCollection personDAO = new PersonDAOCollection();
        ToDoItemDAOCollection toDoItemDAO = new ToDoItemDAOCollection();
        ToDoItemTaskDAOCollection toDoItemTaskDAO = new ToDoItemTaskDAOCollection();
        AppUserDAOCollection appUserDAO = new AppUserDAOCollection();

        // Create and load Application Data Manager:
        ApplicationDataManager applicationDataManager = new ApplicationDataManager(
                dataFolderPath, personDAO, appUserDAO, toDoItemDAO, toDoItemTaskDAO
        );

        // Load data from JSON-files and Sequencers:
        applicationDataManager.loadData();

        // 2. WORK AREA – Manipulate data:

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

        // Create another AppUser:
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

        // 3. SHUTDOWN – Save data to files:

        // Save data from JSON-files and Sequencers:
        applicationDataManager.saveData();


    }

}