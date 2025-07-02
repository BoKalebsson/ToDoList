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


}
