package io.github.bokalebsson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    public void testConstructor() {
        // Arrange – creating test data
        String firstName = "Erik";
        String lastName = "Andersson";
        String email = "erik@example.com";

        // Act – create a Person-object
        Person person = new Person(firstName, lastName, email);

        // Assert – check if the person contains the correct values.
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(email, person.getEmail());
        assertNotNull(person.getId());
    }

    @Test
    public void testSetFirstNameNullOrEmpty() {
        // Arrange: Create a Person object with valid initial data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect IllegalArgumentException when setting firstName to null
        assertThrows(IllegalArgumentException.class, () -> {
            person.setFirstName(null);
        });

        // Act & Assert: Expect IllegalArgumentException when setting firstName to an empty string (only spaces)
        assertThrows(IllegalArgumentException.class, () -> {
            person.setFirstName("  ");
        });
    }

    @Test
    public void testSetLastNameNullOrEmpty() {
        // Arrange: Create a Person object with valid initial data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect IllegalArgumentException when setting lastName to null
        assertThrows(IllegalArgumentException.class, () -> {
            person.setLastName(null);
        });

        // Act & Assert: Expect IllegalArgumentException when setting lastName to an empty string (only spaces)
        assertThrows(IllegalArgumentException.class, () -> {
            person.setLastName("  ");
        });
    }

    @Test
    public void testSetEmailInvalid() {
        // Arrange: Create a Person object with valid initial data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act & Assert: Expect IllegalArgumentException when setting email to null
        assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail(null);
        });

        // Act & Assert: Expect IllegalArgumentException when setting email to an empty string (only spaces)
        assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail("  ");
        });

        // Act & Assert: Expect IllegalArgumentException when setting email without '@'
        assertThrows(IllegalArgumentException.class, () -> {
            person.setEmail("invalid.email.com");
        });
    }

    @Test
    public void testGetFullName() {
        // Arrange: Create a Person with given first and last name
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Get the full name
        String fullName = person.getFullName();

        // Assert: Verify that fullName is correctly concatenated
        assertEquals("Erik Andersson", fullName);
    }

    @Test
    public void testGetPersonInfo() {
        // Arrange: Create a Person with valid data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Get the person info string
        String info = person.getPersonInfo();

        // Assert: Verify that the returned string contains full name and email correctly formatted
        assertTrue(info.contains("Fullname: Erik Andersson"));
        assertTrue(info.contains("Email: erik@example.com"));
    }

    @Test
    public void testToString() {
        // Arrange: Create a Person with valid data
        Person person = new Person("Erik", "Andersson", "erik@example.com");

        // Act: Call the toString() method
        String str = person.toString();

        System.out.println(str);  // For debugging purposes – see the actual string output

        // Assert: Verify that the string contains the expected data
        // This test is flexible and does not enforce exact formatting,
        // making it easier to maintain if the toString format changes.
        assertTrue(str.contains("Erik"));
        assertTrue(str.contains("Andersson"));
        assertTrue(str.contains("erik@example.com"));
    }


}
