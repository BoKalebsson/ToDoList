package io.github.bokalebsson.data.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoItemIdSequencerTest {

    @BeforeEach
    void setUp() {
        TodoItemIdSequencer.reset();
    }

    // Group: nextId()

    @Test
    public void returnsOneInitially() {

        // Arrange & Act: Get the first ID to initialize the counter
        int firstId = TodoItemIdSequencer.nextId();

        // Assert:
        assertEquals(1, firstId, "nextId() should return 1 on first call.");
    }

    @Test
    public void returnsTwoAfterOneCall() {
        // Arrange: Get the first ID to initialize the counter
        TodoItemIdSequencer.nextId(); // Should return 1

        // Act: Get the second ID
        int secondId = TodoItemIdSequencer.nextId(); // Should return 2

        // Assert: Verify that the second call returns 2
        assertEquals(2, secondId, "nextId() should return 2 on second call");
    }

    // Group: reset() behavior

    @Test
    public void resetSetsIdToOne() {

        // Arrange: Call nextId() a few times to increment the counter
        TodoItemIdSequencer.nextId(); // 1
        TodoItemIdSequencer.nextId(); // 2
        TodoItemIdSequencer.nextId(); // 3

        // Act: Reset the sequencer
        TodoItemIdSequencer.reset();
        int resetId = TodoItemIdSequencer.nextId(); // Should return 1 again

        // Assert: After reset, the ID should start from 1
        assertEquals(1, resetId, "After reset(), nextId() should return 1.");
    }

    // Group: repeated reset()
    @Test
    public void multipleResetsStartFromOne() {
        // Arrange
        TodoItemIdSequencer.nextId(); // 1

        // Act
        TodoItemIdSequencer.reset();
        TodoItemIdSequencer.reset();
        TodoItemIdSequencer.reset();
        int idAfterResets = TodoItemIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterResets, "Multiple reset() calls should still result in nextId() returning 1.");
    }

    // Group: reset() early
    @Test
    public void resetAfterFirstCallRestartsFromOne() {
        // Arrange
        TodoItemIdSequencer.nextId(); // 1

        // Act
        TodoItemIdSequencer.reset();
        int idAfterReset = TodoItemIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterReset, "After reset(), nextId() should start again from 1.");
    }

    // Group: Multiple calls to nextId()

    @Test
    public void returnsCorrectValueAfterMultipleCalls() {

        // Arrange: Call nextId() multiple times
        TodoItemIdSequencer.nextId(); // 1
        TodoItemIdSequencer.nextId(); // 2
        TodoItemIdSequencer.nextId(); // 3
        TodoItemIdSequencer.nextId(); // 4
        TodoItemIdSequencer.nextId(); // 5

        // Act: Call nextId() again to get the 6th ID
        int sixthId = TodoItemIdSequencer.nextId();

        // Assert: The returned value should be 6
        assertEquals(6, sixthId, "nextId() should return 6 after 5 previous calls.");
    }
}
