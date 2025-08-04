package io.github.bokalebsson;

import io.github.bokalebsson.data.impl.PersonDAOCollection;
import io.github.bokalebsson.data.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.data.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.data.util.ApplicationDataManager;


public class Main {
    public static void main(String[] args) {

        // 1. STARTUP – Read data

        // Create filepath:
        String dataFolderPath = "data";

        // Create DAO collections:
        PersonDAOCollection personDAO = new PersonDAOCollection();
        ToDoItemDAOCollection toDoItemDAO = new ToDoItemDAOCollection();
        ToDoItemTaskDAOCollection toDoItemTaskDAO = new ToDoItemTaskDAOCollection();

        // Create and load Application Data Manager:
        ApplicationDataManager applicationDataManager = new ApplicationDataManager(
                dataFolderPath, personDAO, toDoItemDAO, toDoItemTaskDAO
        );

        // Load data from JSON-files and Sequencers:
        applicationDataManager.loadData();

        // 2. WORK AREA – Manipulate data:



        // 3. SHUTDOWN – Save data to files:

        // Save data from JSON-files and Sequencers:
        applicationDataManager.saveData();

    }

}