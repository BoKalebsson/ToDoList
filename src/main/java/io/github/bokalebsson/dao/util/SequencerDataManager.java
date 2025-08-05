package io.github.bokalebsson.dao.util;

import io.github.bokalebsson.dao.sequencers.PersonIdSequencer;
import io.github.bokalebsson.dao.sequencers.ToDoItemIdSequencer;
import io.github.bokalebsson.dao.sequencers.ToDoItemTaskIdSequencer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class SequencerDataManager {

    private final Path filePath;
    private final Properties properties;

    public SequencerDataManager(String filename) {
        this.filePath = Paths.get(filename);
        this.properties = new Properties();
    }

    // Load values from the properties file and set them in the sequencers
    public void load() {
        if (!filePath.toFile().exists()) {
            System.out.println("No sequencer file found. Default values will be used.");
            return;
        }

        try (FileInputStream inputStream = new FileInputStream(filePath.toFile())) {
            properties.load(inputStream);

            int personId = Integer.parseInt(properties.getProperty("personId", "0"));
            int todoItemId = Integer.parseInt(properties.getProperty("todoItemId", "0"));
            int todoTaskId = Integer.parseInt(properties.getProperty("todoTaskId", "0"));

            PersonIdSequencer.setCurrentId(personId);
            ToDoItemIdSequencer.setCurrentId(todoItemId);
            ToDoItemTaskIdSequencer.setCurrentId(todoTaskId);

            System.out.println("Sequencer values loaded from file.");

        } catch (IOException | NumberFormatException e) {
            System.err.println("Failed to load sequencer values: " + e.getMessage());
        }
    }

    // Save current values from the sequencers to the properties file
    public void save() {
        properties.setProperty("personId", String.valueOf(PersonIdSequencer.getCurrentId()));
        properties.setProperty("todoItemId", String.valueOf(ToDoItemIdSequencer.getCurrentId()));
        properties.setProperty("todoTaskId", String.valueOf(ToDoItemTaskIdSequencer.getCurrentId()));

        try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            properties.store(outputStream, "Sequencer ID values");
            System.out.println("Sequencer values saved to file.");
        } catch (IOException e) {
            System.err.println("Failed to save sequencer values: " + e.getMessage());
        }
    }
}