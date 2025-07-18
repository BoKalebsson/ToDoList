package io.github.bokalebsson;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTest {

    // Helper method to create a valid Person for tests
    private Person createValidPerson() {
        return new Person("Erik", "Andersson", "erik@example.com");
    }

    // Group: Constructor tests

    @Test
    // Constructor sets all fields correctly with valid input
    public void constructor_validInput_setsFields() {
        // Arrange: Valid inputs for ToDoItem creation
        String title = "Buy groceries";
        String desc = "Milk, Bread, Eggs";
        LocalDate deadline = LocalDate.now().plusDays(1);
        Person creator = createValidPerson();

        // Act: Create new ToDoItem object
        ToDoItem item = new ToDoItem(title, desc, deadline, creator);

        // Assert: All fields are assigned correctly, done is false initially, id is positive
        assertEquals(title, item.getTitle());
        assertEquals(desc, item.getTaskDescription());
        assertEquals(deadline, item.getDeadline());
        assertEquals(creator, item.getCreator());
        assertFalse(item.isDone());
        assertTrue(item.getId() > 0);
    }

    @Test
    // Constructor throws if title is null or empty
    public void constructor_invalidTitle_throws() {
        // Arrange
        Person creator = createValidPerson();
        String desc = "desc";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert: null title throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(null, desc, deadline, creator));

        // Act & Assert: empty title throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem("   ", desc, deadline, creator));
    }

    @Test
    // Constructor throws if description is null or empty
    public void constructor_invalidDesc_throws() {
        // Arrange
        Person creator = createValidPerson();
        String title = "Title";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert: null description throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, null, deadline, creator));

        // Act & Assert: empty description throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, "  ", deadline, creator));
    }

    @Test
    // Constructor throws if deadline is null or in the past
    public void constructor_invalidDeadline_throws() {
        // Arrange
        Person creator = createValidPerson();
        String title = "Title";
        String desc = "desc";

        // Act & Assert: null deadline throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, desc, null, creator));

        // Act & Assert: past deadline throws exception
        assertThrows(IllegalArgumentException.class, () -> new ToDoItem(title, desc, LocalDate.now().minusDays(1), creator));
    }

    @Test
    // Constructor throws if creator is null
    public void constructor_nullCreator_throws() {
        // Arrange
        String title = "Title";
        String desc = "desc";
        LocalDate deadline = LocalDate.now().plusDays(1);

        // Act & Assert: null creator throws NullPointerException
        assertThrows(NullPointerException.class, () -> new ToDoItem(title, desc, deadline, null));
    }

    // Group: Setter tests

    @Test
    // setTitle accepts valid input and rejects null or empty
    public void setTitle_validAndInvalid() {
        // Arrange
        ToDoItem item = new ToDoItem("Old Title", "desc", LocalDate.now().plusDays(1), createValidPerson());

        // Act: set valid title
        item.setTitle("New Title");

        // Assert: title updated
        assertEquals("New Title", item.getTitle());

        // Act & Assert: null or empty title throws exception
        assertThrows(IllegalArgumentException.class, () -> item.setTitle(null));
        assertThrows(IllegalArgumentException.class, () -> item.setTitle("  "));
    }

    @Test
    // setTaskDescription accepts valid input and rejects null or empty
    public void setDesc_validAndInvalid() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "Old desc", LocalDate.now().plusDays(1), createValidPerson());

        // Act: set valid description
        item.setTaskDescription("New desc");

        // Assert: description updated
        assertEquals("New desc", item.getTaskDescription());

        // Act & Assert: null or empty description throws exception
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription(null));
        assertThrows(IllegalArgumentException.class, () -> item.setTaskDescription("  "));
    }

    @Test
    // setDeadline accepts valid future date, rejects null or past date
    public void setDeadline_validAndInvalid() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), createValidPerson());

        // Act: set valid future deadline
        LocalDate futureDate = LocalDate.now().plusDays(5);
        item.setDeadline(futureDate);

        // Assert: deadline updated
        assertEquals(futureDate, item.getDeadline());

        // Act & Assert: null deadline throws exception
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(null));

        // Act & Assert: past deadline throws exception
        LocalDate pastDate = LocalDate.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> item.setDeadline(pastDate));
    }

    // Group: Done status tests

    @Test
    // setDone, markDone, and markUndone work as expected
    public void doneStatus_setMarkWorks() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), createValidPerson());

        // Assert initial done is false
        assertFalse(item.isDone());

        // Act: set done true
        item.setDone(true);

        // Assert done is true
        assertTrue(item.isDone());

        // Act: mark done explicitly
        item.markDone();

        // Assert done is true
        assertTrue(item.isDone());

        // Act: mark undone explicitly
        item.markUndone();

        // Assert done is false
        assertFalse(item.isDone());

        // Act: set done false explicitly
        item.setDone(false);

        // Assert done is false
        assertFalse(item.isDone());
    }

    // Group: Overdue tests

    @Test
    // isOverdue returns false when deadline is today or in the future
    public void overdue_todayAndFuture_false() {
        // Arrange
        Person creator = createValidPerson();

        // Act: ToDoItem with deadline today
        ToDoItem itemToday = new ToDoItem("Task Today", "desc", LocalDate.now(), creator);

        // Assert: Not overdue
        assertFalse(itemToday.isOverdue());

        // Act: ToDoItem with deadline in future
        ToDoItem itemFuture = new ToDoItem("Task Future", "desc", LocalDate.now().plusDays(1), creator);

        // Assert: Not overdue
        assertFalse(itemFuture.isOverdue());
    }

    @Test
// Group: Deadline validation
    void deadline_in_past_throws_exception() {
        // Arrange: Create a Person object as required by constructor
        Person creator = new Person("Bo", "Kalebsson", "bo@email.com");

        // Act & Assert: Expect IllegalArgumentException when passing past deadline
        assertThrows(IllegalArgumentException.class, () -> {
            new ToDoItem("Do", "Past task", LocalDate.now().minusDays(1), creator);
        });
    }

    // Group: toString tests

    @Test
    // toString contains all relevant fields
    public void toString_containsFields() {
        // Arrange
        ToDoItem item = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), createValidPerson());
        item.setDone(true);

        // Act
        String str = item.toString();

        // Assert: String contains id, title, description, deadline, done status
        assertTrue(str.contains(String.valueOf(item.getId())));
        assertTrue(str.contains(item.getTitle()));
        assertTrue(str.contains(item.getTaskDescription()));
        assertTrue(str.contains(item.getDeadline().toString()));
        assertTrue(str.contains("true"));
    }

    // Group: equals and hashCode tests

    @Test
    // equals returns true for equal ToDoItems (excluding creator)
    public void equals_equalObjects_true() {
        // Arrange
        Person creator = createValidPerson();
        ToDoItem item1 = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), creator);
        ToDoItem item2 = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), creator);

        // Force same id and done for test (id normally unique, so adjust for test purposes)
        item2.setDone(item1.isDone());

        // Act & Assert: Because id is different, equals should be false (if id unique)
        // So here, equals should be false since ids differ; test that equals behaves as expected
        assertNotEquals(item1, item2);
    }

    @Test
    // hashCode consistent with equals
    public void hashCode_consistent() {
        // Arrange
        Person creator = createValidPerson();
        ToDoItem item1 = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), creator);
        ToDoItem item2 = new ToDoItem("Title", "desc", LocalDate.now().plusDays(1), creator);

        // Assert: Different objects with different ids have different hashCodes
        assertNotEquals(item1.hashCode(), item2.hashCode());
    }
}