package io.github.bokalebsson;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    // Group: Constructor tests

    @Test
    // Tests that the constructor sets all fields correctly with valid input
    public void testConstructor() {
        // Arrange: Prepare valid input data for Person creation
        String firstName = "Erik";
        String lastName = "Andersson";
        String email = "erik@example.com";

        // Act: Create a new Person object using the constructor
        Person person = new Person(firstName, lastName, email);

        // Assert: Verify the Person's fields match the input values and id is assigned
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
        assertTrue(person.getId() > 0);
    }

    @Test
    // Tests that two different Person objects have unique IDs
    public void testUniqueIds() {
        // Arrange & Act: Create two different Person objects
        Person p1 = new Person("Anna", "Berg", "anna@example.com");
        Person p2 = new Person("Bob", "Svensson", "bob@example.com");

        // Assert: Verify the two persons have different IDs
        assertNotEquals(p1.getId(), p2.getId());
    }

    @Test
    // Tests constructor throws IllegalArgumentException when firstName is null
    public void constructorThrowsIfFirstNameNull() {
        // Arrange, Act & Assert: Expect exception when firstName is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Person(null, "Andersson", "erik@example.com");
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when firstName is empty or whitespace
    public void constructorThrowsIfFirstNameEmpty() {
        // Arrange, Act & Assert: Expect exception when firstName is empty or whitespace
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("   ", "Andersson", "erik@example.com");
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when lastName is null
    public void constructorThrowsIfLastNameNull() {
        // Arrange, Act & Assert: Expect exception when lastName is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Erik", null, "erik@example.com");
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when lastName is empty or whitespace
    public void constructorThrowsIfLastNameEmpty() {
        // Arrange, Act & Assert: Expect exception when lastName is empty or whitespace
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Erik", "   ", "erik@example.com");
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when email is null
    public void constructorThrowsIfEmailNull() {
        // Arrange, Act & Assert: Expect exception when email is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Erik", "Andersson", null);
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when email is empty or whitespace
    public void constructorThrowsIfEmailEmpty() {
        // Arrange, Act & Assert: Expect exception when email is empty or whitespace
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Erik", "Andersson", "   ");
        });
    }

    @Test
    // Tests constructor throws IllegalArgumentException when email does not contain '@'
    public void constructorThrowsIfEmailNoAtSymbol() {
        // Arrange, Act & Assert: Expect exception when email lacks '@'
        assertThrows(IllegalArgumentException.class, () -> {
            new Person("Erik", "Andersson", "invalid.email.com");
        });
    }

    // Group: Setter tests

    @Test
    // Tests setFirstName accepts valid input
    public void testSetFirstNameValid() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Change firstName to a new valid value
        person.setFirstName("Lars");

        // Assert: Verify that firstName has been updated correctly
        assertEquals("Lars", person.getFirstName());
    }

    @Test
    // Tests setFirstName throws IllegalArgumentException for null or empty input
    public void testSetFirstNameNullOrEmpty() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect exceptions for invalid firstName values
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName("  "));
    }

    @Test
    // Tests setLastName accepts valid input
    public void testSetLastNameValid() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Change lastName to a new valid value
        person.setLastName("Svensson");

        // Assert: Verify that lastName has been updated correctly
        assertEquals("Svensson", person.getLastName());
    }

    @Test
    // Tests setLastName throws IllegalArgumentException for null or empty input
    public void testSetLastNameNullOrEmpty() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect exceptions for invalid lastName values
        assertThrows(IllegalArgumentException.class, () -> person.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setLastName("  "));
    }

    @Test
    // Tests setEmail accepts valid input
    public void testSetEmailValid() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Change email to a new valid value
        person.setEmail("new.email@example.com");

        // Assert: Verify that email has been updated correctly
        assertEquals("new.email@example.com", person.getEmail());
    }

    @Test
    // Tests setEmail throws IllegalArgumentException for null, empty, or invalid input
    public void testSetEmailInvalid() {
        // Arrange: Create a valid Person object
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect exceptions for invalid email values
        assertThrows(IllegalArgumentException.class, () -> person.setEmail(null));
        assertThrows(IllegalArgumentException.class, () -> person.setEmail("  "));
        assertThrows(IllegalArgumentException.class, () -> person.setEmail("invalid.email.com"));
    }

    // Group: Operation tests

    @Test
    // Tests getFullName returns concatenated firstName and lastName
    public void testGetFullName() {
        // Arrange: Create a Person with known names
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Retrieve full name
        String fullName = person.getFullName();

        // Assert: Verify fullName matches expected format
        assertEquals("Erik Andersson", fullName);
    }

    @Test
    // Tests getPersonInfo returns a formatted string with full name and email
    public void testGetPersonInfo() {
        // Arrange: Create a Person with known info
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Retrieve person info string
        String info = person.getPersonInfo();

        // Assert: Verify the info string contains correct full name and email
        assertTrue(info.contains("Fullname: Erik Andersson"));
        assertTrue(info.contains("Email: erik@example.com"));
    }

    @Test
    // Tests toString returns a formatted string including all fields
    public void testToString() {
        // Arrange: Create a Person with known data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Convert Person to string
        String str = person.toString();

        // Assert: Verify string contains expected values and formatting
        assertTrue(str.contains("Person ID: " + person.getId()));
        assertTrue(str.contains("First Name: Erik"));
        assertTrue(str.contains("Last Name: Andersson"));
        assertTrue(str.contains("Email: erik@example.com"));
    }

    @Test
    public void testGetSummary() {
        // Arrange
        Person person = new Person("Erik", "Andersson", "erik@gmail.com");

        // Act
        String summary = person.getSummary();

        // Assert
        String expected = String.format(
                "--Person Information--%nID: %d%nName: %s %s%nEmail: %s%n--------------------",
                person.getId(), "Erik", "Andersson", "erik@gmail.com"
        );
        assertEquals(expected, summary);
    }
}