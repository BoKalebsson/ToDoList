package io.github.bokalebsson;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTest {

    // Helper method to create a valid Person for tests
    private Person createValidPerson() {
        return new Person("Erik", "Andersson", "erik@example.com");
    }

    // Helper method to create a valid ToDoItem for tests
    private ToDoItem createValidToDoItem() {
        return new ToDoItem("Title", "Description", LocalDate.now().plusDays(1), createValidPerson());
    }

    // Group: Constructor tests
    @Test
    public void constructorCreatesValidToDoItem() {
        // Arrange - valid input data for ToDoItem
        String title = "Buy groceries";
        String desc = "Milk, Bread, Eggs";
        LocalDate deadline = LocalDate.now().plusDays(1);
        Person creator = createValidPerson();

        // Act - create ToDoItem
        ToDoItem item = new ToDoItem(title, desc, deadline, creator);

        // Assert - verify all fields are set correctly, done is false initially, id is positive
        assertEquals(title, item.getTitle());
        assertEquals(desc, item.getTaskDescription());
        assertEquals(deadline, item.getDeadline());
        assertEquals(creator, item.getCreator());
        assertFalse(item.isDone());
        assertTrue(item.getId() > 0);
    }

    @Test
    public void constructorThrowsIfTitleNullOrEmpty() {
        // Arrange
        Person creator = createValidPerson();
        String desc = "desc";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert - expect IllegalArgumentException if title is null
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(null, desc, deadline, creator));

        // Act & Assert - expect IllegalArgumentException if title is empty or whitespace
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem("  ", desc, deadline, creator));
    }

    @Test
    public void constructorThrowsIfDescriptionNullOrEmpty() {
        // Arrange
        Person creator = createValidPerson();
        String title = "Title";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert - expect IllegalArgumentException if description is null
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, null, deadline, creator));

        // Act & Assert - expect IllegalArgumentException if description is empty or whitespace
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, "  ", deadline, creator));
    }

    @Test
    public void constructorThrowsIfDeadlineNullOrPast() {
        // Arrange
        Person creator = createValidPerson();
        String title = "Title";
        String desc = "desc";

        // Act & Assert - expect IllegalArgumentException if deadline is null
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, desc, null, creator));

        // Act & Assert - expect IllegalArgumentException if deadline is before today
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, desc, LocalDate.now().minusDays(1), creator));
    }

    @Test
    public void constructorThrowsIfCreatorNull() {
        // Arrange
        String title = "Title";
        String desc = "desc";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert - expect NullPointerException if creator is null (due to Objects.requireNonNull)
        assertThrows(NullPointerException.class, () -> new ToDoItem(title, desc, deadline, null));
    }

    // Group: Setter tests
    @Test
    public void setTitleValidAndInvalid() {
        // Arrange - create valid ToDoItem
        ToDoItem item = createValidToDoItem();

        // Act - set valid title
        item.setTitle("New Title");

        // Assert - title should be updated
        assertEquals("New Title", item.getTitle());

        // Act & Assert - setting title to null should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> item.setTitle(null));

        // Act & Assert - setting title to empty string should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> item.setTitle("  "));
    }

    @Test
    public void setTaskDescriptionValidAndInvalid() {
        // Arrange - create valid ToDoItem
        ToDoItem item = createValidToDoItem();

        // Act - set valid task description
        item.setTaskDescription("New description");

        // Assert - description should be updated
        assertEquals("New description", item.getTaskDescription());

        // Act & Assert - setting task description to null should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription(null));

        // Act & Assert - setting task description to empty string should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription("  "));
    }

    @Test
    public void setDeadlineValidAndInvalid() {
        // Arrange - create valid ToDoItem
        ToDoItem item = createValidToDoItem();

        // Act - set valid deadline (future date)
        LocalDate futureDate = LocalDate.now().plusDays(5);
        item.setDeadline(futureDate);

        // Assert - deadline should be updated
        assertEquals(futureDate, item.getDeadline());

        // Act & Assert - setting deadline to null should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(null));

        // Act & Assert - setting deadline to past date should throw IllegalArgumentException
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(pastDate));
    }

    // Group: Done status tests
    @Test
    public void setDoneMarkDoneAndMarkUndone() {
        // Arrange - create valid ToDoItem
        ToDoItem item = createValidToDoItem();

        // Assert - initially done is false
        assertFalse(item.isDone());

        // Act - set done to true
        item.setDone(true);

        // Assert - done should be true
        assertTrue(item.isDone());

        // Act - call markDone method
        item.markDone();
        assertTrue(item.isDone());

        // Act - call markUndone method
        item.markUndone();

        // Assert - done should be false
        assertFalse(item.isDone());

        // Act - set done to false explicitly
        item.setDone(false);

        // Assert - done should be false
        assertFalse(item.isDone());
    }

    // Group: String representation tests
    @Test
    public void toStringContainsAllFields() {
        // Arrange - create valid ToDoItem and mark done
        ToDoItem item = createValidToDoItem();
        item.setDone(true);

        // Act - get string representation
        String str = item.toString();

        // Assert - string contains all important fields and creator summary
        assertTrue(str.contains(String.valueOf(item.getId())));
        assertTrue(str.contains(item.getTitle()));
        assertTrue(str.contains(item.getTaskDescription()));
        assertTrue(str.contains(item.getDeadline().toString()));
        assertTrue(str.contains("true"));
        assertTrue(str.contains(item.getCreator().getSummary()));
    }

    @Test
    public void getSummaryContainsAllFields() {
        // Arrange - create valid ToDoItem
        ToDoItem item = createValidToDoItem();

        // Act - get summary string
        String summary = item.getSummary();

        // Assert - summary contains id, title, description, deadline, done status and creator summary
        assertTrue(summary.contains("ToDoItem{id: " + item.getId()));
        assertTrue(summary.contains(item.getTitle()));
        assertTrue(summary.contains(item.getTaskDescription()));
        assertTrue(summary.contains(item.getDeadline().toString()));
        assertTrue(summary.contains(String.valueOf(item.isDone())));
        assertTrue(summary.contains(item.getCreator().getSummary()));
    }
}