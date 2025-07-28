package io.github.bokalebsson.data.util;

import io.github.bokalebsson.Person;
import io.github.bokalebsson.ToDoItem;
import io.github.bokalebsson.ToDoItemTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDataManager {

    private final FileStorageManager fileStorageManager;
    private final SequencerDataManager sequencerDataManager;

    private final File personFile;
    private final File toDoItemFile;
    private final File toDoItemTaskFile;


    private List<Person> persons;
    private List<ToDoItem> toDoItems;
    private List<ToDoItemTask> toDoItemTasks;

    public ApplicationDataManager(String dataFolderPath) {

        // Initialize helper objects:
        this.fileStorageManager = new FileStorageManager();
        this.sequencerDataManager = new SequencerDataManager(dataFolderPath + "/sequencers.properties");

        // Initialize the file paths:
        this.personFile = new File(dataFolderPath + "/persons.json");
        this.toDoItemFile = new File(dataFolderPath + "/todoitems.json");
        this.toDoItemTaskFile = new File(dataFolderPath + "/todoitemtasks.json");

        // Create the data folder if it doesn't exist:
        File dataFolder = new File(dataFolderPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        // Initialize lists as empty:
        this.persons = new ArrayList<>();
        this.toDoItems = new ArrayList<>();
        this.toDoItemTasks = new ArrayList<>();
    }

    // Load all data and sequencer values from files, create empty files if missing
    public void loadData() {

        // Load sequencer values first:
        sequencerDataManager.load();

        // Load persons:
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

        // Load toDoItems:
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

        // Load toDoItemTasks:
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
    }

    // Save all data and sequencer values to files:
    public void saveData() {
        try {
            fileStorageManager.saveListToFile(persons, personFile, Person.class);
        } catch (IOException e) {
            System.err.println("Failed to save persons: " + e.getMessage());
        }

        try {
            fileStorageManager.saveListToFile(toDoItems, toDoItemFile, ToDoItem.class);
        } catch (IOException e) {
            System.err.println("Failed to save toDoItems: " + e.getMessage());
        }

        try {
            fileStorageManager.saveListToFile(toDoItemTasks, toDoItemTaskFile, ToDoItemTask.class);
        } catch (IOException e) {
            System.err.println("Failed to save toDoItemTasks: " + e.getMessage());
        }

        sequencerDataManager.save();
    }

    // Getters to access the data lists:
    public List<Person> getPersons() {
        return persons;
    }

    public List<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    public List<ToDoItemTask> getToDoItemTasks() {
        return toDoItemTasks;
    }
}