package io.github.bokalebsson.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    // Group: Constructor tests

    @Test
    // Constructor sets fields correctly
    public void constructorSetsFields() {
        // Arrange: Prepare valid input data for Person
        String firstName = "Erik";
        String lastName = "Andersson";
        String email = "erik@example.com";

        // Act: Create a new Person instance
        Person person = new Person(firstName, lastName, email);

        // Assert: Verify all fields are set as expected and id is positive
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
        assertTrue(person.getId() > 0);
    }

    @Test
    // IDs are unique for different persons
    public void idsAreUnique() {
        // Act: Create two different Person instances
        Person p1 = new Person("Anna", "Berg", "anna@example.com");
        Person p2 = new Person("Bob", "Svensson", "bob@example.com");

        // Assert: IDs should not be equal
        assertNotEquals(p1.getId(), p2.getId());
    }

    @Test
    // Throws if firstName invalid
    public void failInvalidFirstName() {
        // Arrange: Common valid parameters
        String lastName = "Andersson";
        String email = "test@example.com";

        // Act & Assert: Creating Person with null or empty firstName throws
        assertThrows(IllegalArgumentException.class, () -> new Person(null, lastName, email));
        assertThrows(IllegalArgumentException.class, () -> new Person("  ", lastName, email));
    }

    @Test
    // Throws if lastName invalid
    public void failInvalidLastName() {
        // Arrange: Common valid parameters
        String firstName = "Erik";
        String email = "test@example.com";

        // Act & Assert: Creating Person with null or empty lastName throws
        assertThrows(IllegalArgumentException.class, () -> new Person(firstName, null, email));
        assertThrows(IllegalArgumentException.class, () -> new Person(firstName, "  ", email));
    }

    @Test
    // Throws if email invalid
    public void failInvalidEmail() {
        // Arrange: Common valid parameters
        String firstName = "Erik";
        String lastName = "Andersson";

        // Act & Assert: Creating Person with null, empty, or invalid email throws
        assertThrows(IllegalArgumentException.class, () -> new Person(firstName, lastName, null));
        assertThrows(IllegalArgumentException.class, () -> new Person(firstName, lastName, " "));
        assertThrows(IllegalArgumentException.class, () -> new Person(firstName, lastName, "invalidemail.com"));
    }

    // Group: Setter tests

    @Test
    // setFirstName updates correctly, throws on invalid
    public void setFirstNameWorks() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Update firstName with a valid value
        person.setFirstName("Lars");

        // Assert: Verify firstName updated correctly
        assertEquals("Lars", person.getFirstName());

        // Act & Assert: Updating with invalid firstName throws exception
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName("  "));
    }

    @Test
    // setLastName updates correctly, throws on invalid
    public void setLastNameWorks() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Update lastName with a valid value
        person.setLastName("Svensson");

        // Assert: Verify lastName updated correctly
        assertEquals("Svensson", person.getLastName());

        // Act & Assert: Updating with invalid lastName throws exception
        assertThrows(IllegalArgumentException.class, () -> person.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setLastName("  "));
    }

    @Test
    // setEmail updates correctly, throws on invalid
    public void setEmailWorks() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Update email with a valid value
        person.setEmail("new.email@example.com");

        // Assert: Verify email updated correctly
        assertEquals("new.email@example.com", person.getEmail());

        // Act & Assert: Updating with invalid email throws exception
        assertThrows(IllegalArgumentException.class, () -> person.setEmail(null));
        assertThrows(IllegalArgumentException.class, () -> person.setEmail(" "));
        assertThrows(IllegalArgumentException.class, () -> person.setEmail("invalid.email.com"));
    }

    @Test
// setCredentials updates correctly, sets GUEST on null
    public void setCredentialsWorks() {
        // Arrange: Create Person and AppUser instances
        Person person = new Person("Erik", "Andersson", "erik@example.com");
        AppUser user = new AppUser("username", "password", AppRole.ROLE_APP_USER);

        // Act: Set credentials
        person.setCredentials(user);

        // Assert: Verify credentials set correctly
        assertEquals(user, person.getCredentials());

        // Act: Set null credentials (should default to GUEST)
        person.setCredentials(null);

        // Assert: Verify fallback to GUEST
        assertEquals(AppUser.GUEST, person.getCredentials());
    }

    // Group: Other methods

    @Test
    // getFullName returns combined first and last name
    public void fullNameIsCorrect() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Retrieve full name
        String fullName = person.getFullName();

        // Assert: Check full name format
        assertEquals("Erik Andersson", fullName);
    }

    @Test
    // getPersonInfo returns correct formatted string
    public void personInfoIsFormatted() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Retrieve person info string
        String info = person.getPersonInfo();

        // Assert: Check if string contains expected substrings
        assertTrue(info.contains("Fullname: Erik Andersson"));
        assertTrue(info.contains("Email: erik@example.com"));
    }

    @Test
    // toString contains relevant person info without credentials
    public void toStringContainsInfo() {
        // Arrange: Create a valid Person instance
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Convert person to string
        String str = person.toString();

        // Assert: Verify string contains id, name, email but not credentials
        assertTrue(str.contains("Id: " + person.getId()));
        assertTrue(str.contains("Name: Erik Andersson"));
        assertTrue(str.contains("Email: erik@example.com"));
        assertFalse(str.contains("credentials"));
    }

    // Group: equals and hashCode

    @Test
    // equals returns false for different persons (due to unique IDs)
    public void equalsDifferent() {
        // Arrange: Create two Persons with same fields
        Person p1 = new Person("Erik", "Andersson", "erik@example.com");
        Person p2 = new Person("Erik", "Andersson", "erik@example.com");

        // Assert: Persons are not equal due to different IDs
        assertNotEquals(p1, p2);
    }

    @Test
    // equals returns false for null and different types
    public void equalsFalseOnNullAndOther() {
        // Arrange: Create a valid Person
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Assert: Person not equal to null or unrelated object
        assertNotEquals(null, person);
        assertNotEquals("some string", person);
    }

// Group: ID assignment via sequencer

    @Test
    // IDs are positive numbers
    public void idsArePositive() {
        // Arrange & Act: Create a Person instance
        Person person = new Person("Anna", "Berg", "anna@example.com");

        // Assert: Check that id is greater than zero
        assertTrue(person.getId() > 0, "Person id should be positive.");
    }

    @Test
    // IDs increase sequentially for new persons
    public void idsIncreaseSequentially() {
        // Arrange & Act: Create multiple Person instances
        Person p1 = new Person("Anna", "Berg", "anna@example.com");
        Person p2 = new Person("Bob", "Svensson", "bob@example.com");
        Person p3 = new Person("Carl", "Nilsson", "carl@example.com");

        // Assert: Each subsequent id is greater than the previous one
        assertTrue(p2.getId() > p1.getId(), "Second person's id should be greater than first.");
        assertTrue(p3.getId() > p2.getId(), "Third person's id should be greater than second.");
    }
}