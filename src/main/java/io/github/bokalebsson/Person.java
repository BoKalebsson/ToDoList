package io.github.bokalebsson;

import java.util.UUID;

public class Person {

    // Attributes:
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    // Constructor:
    public Person(String firstName, String lastName, String email) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters:
    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }


}
