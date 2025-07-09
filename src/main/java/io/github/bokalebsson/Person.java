package io.github.bokalebsson;

import java.util.Objects;

public class Person {

    private static int personIdCounter = 0;

    // Attributes:
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    // Constructor:
    public Person(String firstName, String lastName, String email) {
        this.id = ++personIdCounter;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    // Getters:
    public int getId() {
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

    public AppUser getCredentials() {
        return this.credentials;
    }

    //Setters:
    public void setFirstName(String firstName){
        if (firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("Firstname cannot be null or empty.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        if (lastName == null || lastName.trim().isEmpty()){
            throw new IllegalArgumentException("Lastname cannot be null or empty.");
        }
        this.lastName = lastName;
    }

    /*
    * TODO: We should enter some sort of more robust validation for email.
    * Like: Check for a "." after the @-sign, or invalid chars.
    */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain a '@'.");
        }
        this.email = email;
    }

    public void setCredentials(AppUser credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials cannot be null.");
        }
        this.credentials = credentials;
    }

    // Operations:
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPersonInfo() {
        return String.format(
                "Fullname: %s %s%nEmail: %s",
                firstName, lastName, email
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- Person Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("Name: ").append(getFullName()).append("\n");
        sb.append("Email: ").append(getEmail()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        // Step 1: Check if both references point to the same object in memory.
        if (this == o) return true;

        // Step 2: If the other object is null, they cannot be equal.
        if (o == null) return false;

        // Step 3: Check if the other object is of type Person.
        if (!(o instanceof Person)) return false;

        // Step 4: Cast the object to Person so we can access its fields.
        Person other = (Person) o;

        // Step 5: Compare fields that define identity (excluding credentials).
        return this.id == other.id &&
                this.firstName.equals(other.firstName) &&
                this.lastName.equals(other.lastName) &&
                this.email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

}
