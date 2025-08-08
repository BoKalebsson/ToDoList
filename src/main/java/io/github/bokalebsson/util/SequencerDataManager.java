package io.github.bokalebsson.util;

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
            System.out.println("Loading sequencer file: " + filePath + " ... File not found, using defaults. ✅");
            return;
        }

        try (FileInputStream inputStream = new FileInputStream(filePath.toFile())) {
            properties.load(inputStream);

            int personId = Integer.parseInt(properties.getProperty("personId", "0"));
            int todoItemId = Integer.parseInt(properties.getProperty("todoItemId", "0"));
            int todoTaskId = Integer.parseInt(properties.getProperty("todoTaskId", "0"));

            if (personId >= 1) {
                PersonIdSequencer.setCurrentId(personId);
            }
            if (todoItemId >= 1) {
                ToDoItemIdSequencer.setCurrentId(todoItemId);
            }
            if (todoTaskId >= 1) {
                ToDoItemTaskIdSequencer.setCurrentId(todoTaskId);
            }

            System.out.println("Loading sequencer file: " + filePath + " ... Done! ✅");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Loading sequencer file: " + filePath + " ... Failed to load, using defaults. ✅");
        }
    }

    // Save current values from the sequencers to the properties file
    public void save() {
        properties.setProperty("personId", String.valueOf(PersonIdSequencer.getCurrentId()));
        properties.setProperty("todoItemId", String.valueOf(ToDoItemIdSequencer.getCurrentId()));
        properties.setProperty("todoTaskId", String.valueOf(ToDoItemTaskIdSequencer.getCurrentId()));

        try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            properties.store(outputStream, "Sequencer ID values");
            System.out.println("Saving sequencer file: " + filePath + " ... Done! ✅");
        } catch (IOException e) {
            System.err.println("Saving sequencer file: " + filePath + " ... Failed to save! ❌" + e.getMessage());
        }
    }
}