package io.github.bokalebsson.data.sequencers;

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
    public void shouldReturnOneInitially() {

        // Arrange & Act: Get the first ID to initialize the counter
        int firstId = PersonIdSequencer.nextId();

        // Assert:
        assertEquals(1, firstId, "nextId() should return 1 on first call.");
    }

    @Test
    public void shouldReturnTwoAfterOneCall() {
        // Arrange: Get the first ID to initialize the counter
        PersonIdSequencer.nextId(); // Should return 1

        // Act: Get the second ID
        int secondId = PersonIdSequencer.nextId(); // Should return 2

        // Assert: Verify that the second call returns 2
        assertEquals(2, secondId, "nextId() should return 2 on second call");
    }

    // Group: reset() behavior

    @Test
    public void reset_SetsIdBackToOne() {

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


}
