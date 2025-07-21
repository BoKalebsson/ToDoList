package io.github.bokalebsson.data.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoItemIdSequencerTest {

    @BeforeEach
    void setUp() {
        ToDoItemIdSequencer.reset();
    }

    // Group: nextId()

    @Test
    public void returnsOneInitially() {

        // Arrange & Act: Get the first ID to initialize the counter
        int firstId = ToDoItemIdSequencer.nextId();

        // Assert:
        assertEquals(1, firstId, "nextId() should return 1 on first call.");
    }

    @Test
    public void returnsTwoAfterOneCall() {
        // Arrange: Get the first ID to initialize the counter
        ToDoItemIdSequencer.nextId(); // Should return 1

        // Act: Get the second ID
        int secondId = ToDoItemIdSequencer.nextId(); // Should return 2

        // Assert: Verify that the second call returns 2
        assertEquals(2, secondId, "nextId() should return 2 on second call");
    }

    // Group: reset() behavior

    @Test
    public void resetSetsIdToOne() {

        // Arrange: Call nextId() a few times to increment the counter
        ToDoItemIdSequencer.nextId(); // 1
        ToDoItemIdSequencer.nextId(); // 2
        ToDoItemIdSequencer.nextId(); // 3

        // Act: Reset the sequencer
        ToDoItemIdSequencer.reset();
        int resetId = ToDoItemIdSequencer.nextId(); // Should return 1 again

        // Assert: After reset, the ID should start from 1
        assertEquals(1, resetId, "After reset(), nextId() should return 1.");
    }

    // Group: repeated reset()
    @Test
    public void multipleResetsStartFromOne() {
        // Arrange
        ToDoItemIdSequencer.nextId(); // 1

        // Act
        ToDoItemIdSequencer.reset();
        ToDoItemIdSequencer.reset();
        ToDoItemIdSequencer.reset();
        int idAfterResets = ToDoItemIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterResets, "Multiple reset() calls should still result in nextId() returning 1.");
    }

    // Group: reset() early
    @Test
    public void resetAfterFirstCallRestartsFromOne() {
        // Arrange
        ToDoItemIdSequencer.nextId(); // 1

        // Act
        ToDoItemIdSequencer.reset();
        int idAfterReset = ToDoItemIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterReset, "After reset(), nextId() should start again from 1.");
    }

    // Group: Multiple calls to nextId()

    @Test
    public void returnsCorrectValueAfterMultipleCalls() {

        // Arrange: Call nextId() multiple times
        ToDoItemIdSequencer.nextId(); // 1
        ToDoItemIdSequencer.nextId(); // 2
        ToDoItemIdSequencer.nextId(); // 3
        ToDoItemIdSequencer.nextId(); // 4
        ToDoItemIdSequencer.nextId(); // 5

        // Act: Call nextId() again to get the 6th ID
        int sixthId = ToDoItemIdSequencer.nextId();

        // Assert: The returned value should be 6
        assertEquals(6, sixthId, "nextId() should return 6 after 5 previous calls.");
    }
}
