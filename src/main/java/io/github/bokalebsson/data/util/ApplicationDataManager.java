package io.github.bokalebsson.data.util;

import io.github.bokalebsson.data.impl.AppUserDAOCollection;
import io.github.bokalebsson.data.impl.PersonDAOCollection;
import io.github.bokalebsson.data.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.data.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.model.AppUser;
import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.model.ToDoItem;
import io.github.bokalebsson.model.ToDoItemTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDataManager {

    private final FileStorageManager fileStorageManager;
    private final SequencerDataManager sequencerDataManager;

    private final PersonDAOCollection personDAO;
    private final AppUserDAOCollection appUserDAO;
    private final ToDoItemDAOCollection toDoItemDAO;
    private final ToDoItemTaskDAOCollection toDoItemTaskDAO;

    private final File personFile;
    private final File appUserFile;
    private final File toDoItemFile;
    private final File toDoItemTaskFile;

    public ApplicationDataManager(String dataFolderPath,
                                  PersonDAOCollection personDAO,
                                  AppUserDAOCollection appUserDAO,
                                  ToDoItemDAOCollection toDoItemDAO,
                                  ToDoItemTaskDAOCollection toDoItemTaskDAO) {

        // Initialize helper objects:
        this.fileStorageManager = new FileStorageManager();
        this.sequencerDataManager = new SequencerDataManager(dataFolderPath + "/sequencers.properties");

        // Initialize the file paths:
        this.personFile = new File(dataFolderPath + "/persons.json");
        this.appUserFile = new File(dataFolderPath + "/appusers.json");
        this.toDoItemFile = new File(dataFolderPath + "/todoitems.json");
        this.toDoItemTaskFile = new File(dataFolderPath + "/todoitemtasks.json");

        // Assign DAO references:
        this.personDAO = personDAO;
        this.appUserDAO = appUserDAO;
        this.toDoItemDAO = toDoItemDAO;
        this.toDoItemTaskDAO = toDoItemTaskDAO;

        // Create the data folder if it doesn't exist:
        File dataFolder = new File(dataFolderPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    // Load all data and sequencer values from files, create empty files if missing
    public void loadData() {

        // Load sequencer values first:
        sequencerDataManager.load();

        // Load persons:
        List<Person> persons;
        if (personFile.exists()) {
            try {
                persons = fileStorageManager.loadListFromFile(personFile, Person.class);
            } catch (IOException e) {
                System.err.println("Failed to load persons: " + e.getMessage());
                persons = new ArrayList<>();
            }
        } else {
            persons = new ArrayList<>();
            try {
                fileStorageManager.saveListToFile(persons, personFile, Person.class);
            } catch (IOException e) {
                System.err.println("Failed to create empty persons file: " + e.getMessage());
            }
        }
        personDAO.setItems(persons);

        // Load appUsers:
        List<AppUser> appUsers;
        if (appUserFile.exists()) {
            try {
                appUsers = fileStorageManager.loadListFromFile(appUserFile, AppUser.class);
            } catch (IOException e) {
                System.err.println("Failed to load appUsers-file: " + e.getMessage());
                appUsers = new ArrayList<>();
            }
        } else {
            appUsers = new ArrayList<>();
            try {
                fileStorageManager.saveListToFile(appUsers, appUserFile, AppUser.class);
            } catch (IOException e) {
                System.err.println("Failed to create empty appUsers-file: " + e.getMessage());
            }
        }
        appUserDAO.setItems(appUsers);


        // Load toDoItems:
        List<ToDoItem> toDoItems;
        if (toDoItemFile.exists()) {
            try {
                toDoItems = fileStorageManager.loadListFromFile(toDoItemFile, ToDoItem.class);
            } catch (IOException e) {
                System.err.println("Failed to load toDoItems-file: " + e.getMessage());
                toDoItems = new ArrayList<>();
            }
        } else {
            toDoItems = new ArrayList<>();
            try {
                fileStorageManager.saveListToFile(toDoItems, toDoItemFile, ToDoItem.class);
            } catch (IOException e) {
                System.err.println("Failed to create empty toDoItems-file: " + e.getMessage());
            }
        }
        toDoItemDAO.setItems(toDoItems);

        // Load toDoItemTasks:
        List<ToDoItemTask> toDoItemTasks;
        if (toDoItemTaskFile.exists()) {
            try {
                toDoItemTasks = fileStorageManager.loadListFromFile(toDoItemTaskFile, ToDoItemTask.class);
            } catch (IOException e) {
                System.err.println("Failed to load toDoItemTasks-file: " + e.getMessage());
                toDoItemTasks = new ArrayList<>();
            }
        } else {
            toDoItemTasks = new ArrayList<>();
            try {
                fileStorageManager.saveListToFile(toDoItemTasks, toDoItemTaskFile, ToDoItemTask.class);
            } catch (IOException e) {
                System.err.println("Failed to create empty toDoItemTasks-file: " + e.getMessage());
            }
        }
        toDoItemTaskDAO.setItems(toDoItemTasks);
    }


    // Save all data and sequencer values to files:
    public void saveData() {
        try {
            fileStorageManager.saveListToFile(new ArrayList<>(personDAO.findAll()), personFile, Person.class);
        } catch (IOException e) {
            System.err.println("Failed to save persons: " + e.getMessage());
        }

        try {
            fileStorageManager.saveListToFile(new ArrayList<>(appUserDAO.findAll()), appUserFile, AppUser.class);
        } catch (IOException e) {
            System.err.println("Failed to save appUsers: " + e.getMessage());
        }

        try {
            fileStorageManager.saveListToFile(new ArrayList<>(toDoItemDAO.findAll()), toDoItemFile, ToDoItem.class);
        } catch (IOException e) {
            System.err.println("Failed to save toDoItems: " + e.getMessage());
        }

        try {
            fileStorageManager.saveListToFile(new ArrayList<>(toDoItemTaskDAO.findAll()), toDoItemTaskFile, ToDoItemTask.class);
        } catch (IOException e) {
            System.err.println("Failed to save toDoItemTasks: " + e.getMessage());
        }

        sequencerDataManager.save();
    }
}