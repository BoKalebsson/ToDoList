package io.github.bokalebsson.data.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoItemTaskIdSequencerTest {

    @BeforeEach
    void setUp() {
        ToDoItemTaskIdSequencer.reset();
    }

    // Group: nextId()

    @Test
    public void returnsOneInitially() {

        // Arrange & Act: Get the first ID to initialize the counter
        int firstId = ToDoItemTaskIdSequencer.nextId();

        // Assert:
        assertEquals(1, firstId, "nextId() should return 1 on first call.");
    }

    @Test
    public void returnsTwoAfterOneCall() {
        // Arrange: Get the first ID to initialize the counter
        ToDoItemTaskIdSequencer.nextId(); // Should return 1

        // Act: Get the second ID
        int secondId = ToDoItemTaskIdSequencer.nextId(); // Should return 2

        // Assert: Verify that the second call returns 2
        assertEquals(2, secondId, "nextId() should return 2 on second call");
    }

    // Group: reset() behavior

    @Test
    public void resetSetsIdToOne() {

        // Arrange: Call nextId() a few times to increment the counter
        ToDoItemTaskIdSequencer.nextId(); // 1
        ToDoItemTaskIdSequencer.nextId(); // 2
        ToDoItemTaskIdSequencer.nextId(); // 3

        // Act: Reset the sequencer
        ToDoItemTaskIdSequencer.reset();
        int resetId = ToDoItemTaskIdSequencer.nextId(); // Should return 1 again

        // Assert: After reset, the ID should start from 1
        assertEquals(1, resetId, "After reset(), nextId() should return 1.");
    }

    // Group: repeated reset()
    @Test
    public void multipleResetsStartFromOne() {
        // Arrange
        ToDoItemTaskIdSequencer.nextId(); // 1

        // Act
        ToDoItemTaskIdSequencer.reset();
        ToDoItemTaskIdSequencer.reset();
        ToDoItemTaskIdSequencer.reset();
        int idAfterResets = ToDoItemTaskIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterResets, "Multiple reset() calls should still result in nextId() returning 1.");
    }

    // Group: reset() early
    @Test
    public void resetAfterFirstCallRestartsFromOne() {
        // Arrange
        ToDoItemTaskIdSequencer.nextId(); // 1

        // Act
        ToDoItemTaskIdSequencer.reset();
        int idAfterReset = ToDoItemTaskIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterReset, "After reset(), nextId() should start again from 1.");
    }

    // Group: Multiple calls to nextId()

    @Test
    public void returnsCorrectValueAfterMultipleCalls() {

        // Arrange: Call nextId() multiple times
        ToDoItemTaskIdSequencer.nextId(); // 1
        ToDoItemTaskIdSequencer.nextId(); // 2
        ToDoItemTaskIdSequencer.nextId(); // 3
        ToDoItemTaskIdSequencer.nextId(); // 4
        ToDoItemTaskIdSequencer.nextId(); // 5

        // Act: Call nextId() again to get the 6th ID
        int sixthId = ToDoItemTaskIdSequencer.nextId();

        // Assert: The returned value should be 6
        assertEquals(6, sixthId, "nextId() should return 6 after 5 previous calls.");
    }
}
