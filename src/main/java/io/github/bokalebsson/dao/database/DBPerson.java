package io.github.bokalebsson.dao.database;

import java.util.Objects;

public class DBPerson {

    // Fields:
    private int id;
    private String firstName;
    private String lastName;

    // Constructors:
    public DBPerson(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public DBPerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters:
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setters:
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Methods:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-- Person Information --").append("\n");
        sb.append("Id: ").append(getId()).append("\n");
        sb.append("First name: ").append(getFirstName()).append("\n");
        sb.append("Last name: ").append(getLastName()).append("\n");
        sb.append("---------------------------").append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DBPerson dbPerson = (DBPerson) o;
        return id == dbPerson.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
