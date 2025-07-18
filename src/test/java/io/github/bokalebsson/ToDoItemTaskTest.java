package io.github.bokalebsson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTaskTest {

    private ToDoItem item;
    private Person person;

    // Arrange reusable objects before each test
    @BeforeEach
    void setUp() {
        person = new Person("Erik", "Andersson", "erik@email.com");
        item = new ToDoItem("Clean", "Kitchen cleaning", LocalDate.now().plusDays(1), person);
    }

    // Group: Constructor and initialization
    @Test
    void createWithAssignee() {
        // Act: Create task with an assignee
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Assert: Values should match input
        assertNotNull(task.getId());
        assertEquals(item, task.getToDoItem());
        assertEquals(person, task.getAssignee());
        assertTrue(task.isAssigned());
    }

    @Test
    void createWithoutAssignee() {
        // Act: Create task with null assignee
        ToDoItemTask task = new ToDoItemTask(item, null);

        // Assert: Assigned should be false, assignee null
        assertNull(task.getAssignee());
        assertFalse(task.isAssigned());
    }

    @Test
    void createWithNullItemThrows() {
        // Assert: Creating with null item should throw
        assertThrows(NullPointerException.class, () -> new ToDoItemTask(null, person));
    }

    // Group: Setter behavior
    @Test
    void setValidItem() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);
        ToDoItem newItem = new ToDoItem("Laundry", "Wash clothes", LocalDate.now().plusDays(2), person);

        // Act: Set new item
        task.setToDoItem(newItem);

        // Assert: Item should be updated
        assertEquals(newItem, task.getToDoItem());
    }

    @Test
    void setNullItemThrows() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Assert: Setting null item throws
        assertThrows(IllegalArgumentException.class, () -> task.setToDoItem(null));
    }

    @Test
    void setAssigneeUpdatesFlag() {
        // Arrange: Create task with null assignee
        ToDoItemTask task = new ToDoItemTask(item, null);

        // Act: Set assignee
        task.setAssignee(person);

        // Assert: Flag is updated
        assertEquals(person, task.getAssignee());
        assertTrue(task.isAssigned());
    }

    @Test
    void setAssigneeToNullUnassigns() {
        // Arrange: Create task with assignee
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Act: Set assignee to null
        task.setAssignee(null);

        // Assert: Assignee removed, flag false
        assertNull(task.getAssignee());
        assertFalse(task.isAssigned());
    }

    // Group: Equals and hashCode
    @Test
    void equalsSameObject() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Assert: Should equal itself
        assertEquals(task, task);
    }

    @Test
    void equalsNullObject() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Assert: Should not equal null
        assertNotEquals(null, task);
    }

    @Test
    void equalsDifferentType() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Assert: Should not equal object of different type
        assertNotEquals("some string", task);
    }

    @Test
    void equalsDifferentIdFails() {
        // Arrange: Create two tasks with different IDs
        ToDoItemTask task1 = new ToDoItemTask(item, person);
        ToDoItemTask task2 = new ToDoItemTask(item, person);

        // Assert: Different ID → not equal
        assertNotEquals(task1, task2);
    }

    @Test
    void hashConsistentWithEquals() {
        // Arrange: Create two equal references
        ToDoItemTask task = new ToDoItemTask(item, person);
        ToDoItemTask sameRef = task;

        // Assert: Same hash if same object
        assertEquals(task.hashCode(), sameRef.hashCode());
    }

    // Group: toString output
    @Test
    void toStringIncludesExpectedData() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(item, person);

        // Act: Generate toString
        String result = task.toString();

        // Assert: Key data should be present
        assertTrue(result.contains("ToDoItemTask"));
        assertTrue(result.contains("Id: " + task.getId()));
        assertTrue(result.contains("Assigned: true"));
        assertTrue(result.contains(item.toString()));
    }
}