package io.github.bokalebsson.dao.sequencers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonIdSequencerTest {

    @BeforeEach
    void setUp() {
        PersonIdSequencer.reset();
    }

    // Group: nextId()

    @Test
    public void returnsOneInitially() {

        // Arrange & Act: Get the first ID to initialize the counter
        int firstId = PersonIdSequencer.nextId();

        // Assert:
        assertEquals(1, firstId, "nextId() should return 1 on first call.");
    }

    @Test
    public void returnsTwoAfterOneCall() {
        // Arrange: Get the first ID to initialize the counter
        PersonIdSequencer.nextId(); // Should return 1

        // Act: Get the second ID
        int secondId = PersonIdSequencer.nextId(); // Should return 2

        // Assert: Verify that the second call returns 2
        assertEquals(2, secondId, "nextId() should return 2 on second call");
    }

    // Group: reset() behavior

    @Test
    public void resetSetsIdToOne() {

        // Arrange: Call nextId() a few times to increment the counter
        PersonIdSequencer.nextId(); // 1
        PersonIdSequencer.nextId(); // 2
        PersonIdSequencer.nextId(); // 3

        // Act: Reset the sequencer
        PersonIdSequencer.reset();
        int resetId = PersonIdSequencer.nextId(); // Should return 1 again

        // Assert: After reset, the ID should start from 1
        assertEquals(1, resetId, "After reset(), nextId() should return 1.");
    }

    // Group: repeated reset()
    @Test
    public void multipleResetsStartFromOne() {
        // Arrange
        PersonIdSequencer.nextId(); // 1

        // Act
        PersonIdSequencer.reset();
        PersonIdSequencer.reset();
        PersonIdSequencer.reset();
        int idAfterResets = PersonIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterResets, "Multiple reset() calls should still result in nextId() returning 1.");
    }

    // Group: reset() early
    @Test
    public void resetAfterFirstCallRestartsFromOne() {
        // Arrange
        PersonIdSequencer.nextId(); // 1

        // Act
        PersonIdSequencer.reset();
        int idAfterReset = PersonIdSequencer.nextId();

        // Assert
        assertEquals(1, idAfterReset, "After reset(), nextId() should start again from 1.");
    }

    // Group: Multiple calls to nextId()

    @Test
    public void returnsCorrectValueAfterMultipleCalls() {

        // Arrange: Call nextId() multiple times
        PersonIdSequencer.nextId(); // 1
        PersonIdSequencer.nextId(); // 2
        PersonIdSequencer.nextId(); // 3
        PersonIdSequencer.nextId(); // 4
        PersonIdSequencer.nextId(); // 5

        // Act: Call nextId() again to get the 6th ID
        int sixthId = PersonIdSequencer.nextId();

        // Assert: The returned value should be 6
        assertEquals(6, sixthId, "nextId() should return 6 after 5 previous calls.");
    }

}