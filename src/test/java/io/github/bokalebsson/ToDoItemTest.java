package io.github.bokalebsson;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTest {

    @Test
    public void testConstructor() {
        // Arrange – valid test data
        String title = "Buy groceries";
        String desc = "Milk, Bread, Eggs";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act – create ToDoItem object
        ToDoItem item = new ToDoItem(title, desc, deadline);

        // Assert – verify all fields are set correctly
        assertEquals(title, item.getTitle());
        assertEquals(desc, item.getTaskDescription());
        assertEquals(deadline, item.getDeadline());
        assertFalse(item.isDone());
        assertNotNull(item.getId());
    }

    @Test
    public void constructorThrowsIfTitleNull() {
        // Expect exception if title is null
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem(null, "desc", LocalDate.now().plusDays(1));
        });
    }

    @Test
    public void constructorThrowsIfTitleEmpty() {
        // Expect exception if title is empty or blank
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("   ", "desc", LocalDate.now().plusDays(1));
        });
    }

    @Test
    public void constructorThrowsIfDescNull() {
        // Expect exception if taskDescription is null
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("Title", null, LocalDate.now().plusDays(1));
        });
    }

    @Test
    public void constructorThrowsIfDescEmpty() {
        // Expect exception if taskDescription is empty or blank
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("Title", "   ", LocalDate.now().plusDays(1));
        });
    }

    @Test
    public void constructorThrowsIfDeadlineNull() {
        // Expect exception if deadline is null
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("Title", "desc", null);
        });
    }

    @Test
    public void constructorThrowsIfDeadlinePast() {
        // Expect exception if deadline is before today
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("Title", "desc", LocalDate.now().minusDays(1));
        });
    }

    @Test
    public void testSetTitle() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        String newTitle = "New Title";

        // Act
        item.setTitle(newTitle);

        // Assert
        assertEquals(newTitle, item.getTitle());
    }

    @Test
    public void setTitleThrowsIfInvalid() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));

        // Act & Assert for null
        assertThrows(IllegalArgumentException.class, () -> item.setTitle(null));

        // Act & Assert for empty
        assertThrows(IllegalArgumentException.class, () -> item.setTitle("  "));
    }

    @Test
    public void testSetTaskDescription() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        String newDesc = "New Description";

        // Act
        item.setTaskDescription(newDesc);

        // Assert
        assertEquals(newDesc, item.getTaskDescription());
    }

    @Test
    public void setDescThrowsIfInvalid() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));

        // Act & Assert for null
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription(null));

        // Act & Assert for empty
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription("  "));
    }

    @Test
    public void testSetDeadline() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        LocalDate newDeadline = LocalDate.now().plusDays(5);

        // Act
        item.setDeadline(newDeadline);

        // Assert
        assertEquals(newDeadline, item.getDeadline());
    }

    @Test
    public void setDeadlineThrowsIfNull() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(null));
    }

    @Test
    public void setDeadlineThrowsIfPast() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        LocalDate pastDate = LocalDate.now().minusDays(2);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(pastDate));
    }

    @Test
    public void testSetDone() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));

        // Act
        item.setDone(true);

        // Assert
        assertTrue(item.isDone());

        // Act
        item.setDone(false);

        // Assert
        assertFalse(item.isDone());
    }

    @Test
    public void testMarkDone() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));

        // Act
        item.markDone();

        // Assert
        assertTrue(item.isDone());
    }

    @Test
    public void testMarkUndone() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        item.markDone();

        // Act
        item.markUndone();

        // Assert
        assertFalse(item.isDone());
    }

    @Test
    public void testToString() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1));
        item.setDone(true);

        // Act
        String str = item.toString();

        // Debug output (optional)
        System.out.println(str);

        // Assert – verify string contains all attributes
        assertTrue(str.contains(item.getId()));
        assertTrue(str.contains(item.getTitle()));
        assertTrue(str.contains(item.getTaskDescription()));
        assertTrue(str.contains(item.getDeadline().toString()));
        assertTrue(str.contains("true"));
    }


}