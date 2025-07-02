package io.github.bokalebsson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoItemTaskTest {

    // Helper method to create a valid Person for tests
    private Person createValidPerson() {
        return new Person("Erik", "Andersson", "erik@example.com");
    }

    // Helper method to create a valid ToDoItem for tests
    private ToDoItem createValidToDoItem() {
        return new ToDoItem("Title", "Description", java.time.LocalDate.now().plusDays(1), createValidPerson());
    }

    // Group: Constructor tests

    @Test
    public void constructorAssignsIdAndFieldsCorrectly() {
        // Arrange: Prepare a valid ToDoItem and Person
        ToDoItem toDoItem = createValidToDoItem();
        Person assignee = createValidPerson();

        // Act: Create a ToDoItemTask with both toDoItem and assignee
        ToDoItemTask task = new ToDoItemTask(toDoItem, assignee);

        // Assert: Verify that id is positive, fields are assigned properly, and assigned flag is true
        assertTrue(task.getId() > 0, "ID should be greater than zero.");
        assertEquals(toDoItem, task.getToDoItem(), "ToDoItem should be set correctly.");
        assertEquals(assignee, task.getAssignee(), "Assignee should be set correctly.");
        assertTrue(task.isAssigned(), "Assigned flag should be true when assignee is not null.");
    }

    @Test
    public void constructorAllowsNullAssigneeAndSetsAssignedFalse() {
        // Arrange: Prepare a valid ToDoItem without an assignee
        ToDoItem toDoItem = createValidToDoItem();

        // Act: Create a ToDoItemTask with null assignee
        ToDoItemTask task = new ToDoItemTask(toDoItem, null);

        // Assert: Verify that assignee is null and assigned flag is false
        assertEquals(toDoItem, task.getToDoItem(), "ToDoItem should be set correctly.");
        assertNull(task.getAssignee(), "Assignee should be null.");
        assertFalse(task.isAssigned(), "Assigned flag should be false when assignee is null.");
    }

    @Test
    public void constructorThrowsIfToDoItemNull() {
        // Arrange, Act & Assert: Creating a ToDoItemTask with null toDoItem should throw NullPointerException
        assertThrows(NullPointerException.class, () -> new ToDoItemTask(null, createValidPerson()), "Constructor should throw NullPointerException if ToDoItem is null.");
    }

    // Group: Getter tests

    @Test
    public void gettersReturnExpectedValues() {
        // Arrange: Create task with valid ToDoItem and assignee
        ToDoItem toDoItem = createValidToDoItem();
        Person assignee = createValidPerson();
        ToDoItemTask task = new ToDoItemTask(toDoItem, assignee);

        // Act & Assert: Verify all getters return the correct values
        assertEquals(toDoItem, task.getToDoItem(), "getToDoItem() should return correct ToDoItem.");
        assertEquals(assignee, task.getAssignee(), "getAssignee() should return correct Person.");
        assertTrue(task.isAssigned(), "isAssigned() should be true.");
        assertTrue(task.getId() > 0, "getId() should return positive ID.");
    }

    // Group: Setter tests

    @Test
    public void setToDoItemUpdatesWhenValid() {
        // Arrange: Create task and a new ToDoItem to set
        ToDoItemTask task = new ToDoItemTask(createValidToDoItem(), null);
        ToDoItem newToDoItem = createValidToDoItem();

        // Act: Set the new ToDoItem
        task.setToDoItem(newToDoItem);

        // Assert: Verify the ToDoItem was updated
        assertEquals(newToDoItem, task.getToDoItem(), "setToDoItem() should update the ToDoItem field.");
    }

    @Test
    public void setToDoItemThrowsIfNull() {
        // Arrange: Create task
        ToDoItemTask task = new ToDoItemTask(createValidToDoItem(), null);

        // Act & Assert: Setting ToDoItem to null should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> task.setToDoItem(null), "setToDoItem() should throw IllegalArgumentException when given null.");
    }

    @Test
    public void setAssigneeUpdatesAssigneeAndAssignedFlag() {
        // Arrange: Create task with no assignee
        ToDoItemTask task = new ToDoItemTask(createValidToDoItem(), null);
        Person newAssignee = createValidPerson();

        // Act: Set a new assignee
        task.setAssignee(newAssignee);

        // Assert: Assignee is updated and assigned flag is true
        assertEquals(newAssignee, task.getAssignee(), "setAssignee() should update the assignee.");
        assertTrue(task.isAssigned(), "Assigned flag should be true after setting a non-null assignee.");

        // Act: Set assignee to null
        task.setAssignee(null);

        // Assert: Assignee is null and assigned flag is false
        assertNull(task.getAssignee(), "setAssignee() should allow setting assignee to null.");
        assertFalse(task.isAssigned(), "Assigned flag should be false after setting assignee to null.");
    }

    // Group: Operation tests

    @Test
    public void getSummaryReturnsFormattedStringWithAssignee() {
        // Arrange: Create task with assignee
        ToDoItem toDoItem = createValidToDoItem();
        Person assignee = createValidPerson();
        ToDoItemTask task = new ToDoItemTask(toDoItem, assignee);

        // Act: Get the summary string
        String summary = task.getSummary();

        // Assert: Summary contains expected parts including assignee info and assigned true
        assertTrue(summary.contains("ToDoItemTask{id: " + task.getId()), "Summary should contain the task id.");
        assertTrue(summary.contains(toDoItem.getSummary()), "Summary should include ToDoItem's summary.");
        assertTrue(summary.contains(assignee.getSummary()), "Summary should include assignee's summary.");
        assertTrue(summary.contains("Assigned: true"), "Summary should indicate assigned is true.");
    }

    @Test
    public void getSummaryReturnsFormattedStringWithoutAssignee() {
        // Arrange: Create task without assignee
        ToDoItem toDoItem = createValidToDoItem();
        ToDoItemTask task = new ToDoItemTask(toDoItem, null);

        // Act: Get the summary string
        String summary = task.getSummary();

        // Assert: Summary contains expected parts including "Assignee: 'null'" and assigned false
        assertTrue(summary.contains("ToDoItemTask{id: " + task.getId()), "Summary should contain the task id.");
        assertTrue(summary.contains(toDoItem.getSummary()), "Summary should include ToDoItem's summary.");
        assertTrue(summary.contains("Assignee: 'null'"), "Summary should indicate assignee is null.");
        assertTrue(summary.contains("Assigned: false"), "Summary should indicate assigned is false.");
    }
}