package io.github.bokalebsson.dao.impl;

import io.github.bokalebsson.model.Person;
import io.github.bokalebsson.dao.PersonDAO;

import java.util.*;

public class PersonDAOCollection implements PersonDAO {

    private Map<Integer, Person> persons = new HashMap<>();

    // Constructor: Default
    public PersonDAOCollection() {
        this.persons = new HashMap<>();
    }

    // Constructor: Load from Collection<Person> and build internal Map
    public PersonDAOCollection(Collection<Person> personCollection) {
        // Check if the collection is null
        if (personCollection == null) {
            throw new IllegalArgumentException("Person collection cannot be null.");
        }

        // Create a new empty map to hold persons
        Map<Integer, Person> map = new HashMap<>();

        // Populate the map with each person from the collection
        for (Person person : personCollection) {
            int id = person.getId();

            // Check for duplicate IDs to avoid overwriting
            if (map.containsKey(id)) {
                throw new IllegalArgumentException("Duplicate person ID found: " + id);
            }

            map.put(id, person);
        }

        // Assign the map to our internal collection
        this.persons = map;
    }

    // Helper methods: Converts a given list of Person objects into the internal map representation.
    public void setItems(List<Person> personList) {
        persons.clear();
        for (Person person : personList) {
            persons.put(person.getId(), person);
        }
    }

    @Override
    public Person persist(Person person) {

        // Check if person is null:
        if(person == null){
            throw new IllegalArgumentException("Person is not allowed to be null.");
        }

        // Get the person's id:
        int personId = person.getId();

        if (persons.containsKey(personId)) {
            throw new IllegalArgumentException("Person already exists: " + personId);
        }

        // Check for duplicate email
        for (Person existingPerson : persons.values()) {
            if (existingPerson.getEmail().equals(person.getEmail())) {
                throw new IllegalArgumentException("A person with this email already exists: " + person.getEmail());
            }
        }

        // Add the person to the collection:
        persons.put(personId, person);

        // Return the person:
        return person;
    }

    @Override
    public Person findById(int id) {

        // Check if id is negative or zero:
        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        // Check if the id is in the map:
        if(!persons.containsKey(id)) {
            throw new IllegalArgumentException("No person found with id: " + id);
        }

        // Return the person:
        return persons.get(id);
    }

    @Override
    public Person findByEmail(String email) {

        // Basic check for the search. More robust checks are handled by the Person-class.
        if (email == null || email.trim().isEmpty()){
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }

        for(Person person : persons.values()){

            if(person.getEmail().equals(email)) {
                return person;
            }
        }
        throw new IllegalArgumentException("No one found with the following email: " + email);
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    @Override
    public Person update(Person person) {
        // 1. Check if the input is null
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null.");
        }

        // 2. Get the person's id
        int id = person.getId();

        // 3. Check if the id is valid
        if (id <= 0) {
            throw new IllegalArgumentException("Person id must be greater than zero.");
        }

        // 4. Check if person with this id exists
        if (!persons.containsKey(id)) {
            throw new IllegalArgumentException("Person with id " + id + " does not exist.");
        }

        // 5. Update the person in the map
        persons.put(id, person);

        // 6. Return the updated person
        return person;
    }

    @Override
    public void remove(int id) {

        if(id <= 0){
            throw new IllegalArgumentException("Id is not allowed to be zero or negative.");
        }

        if(!persons.containsKey(id)) {
            throw new IllegalArgumentException("No one found with the following id: " + id);
        }
        persons.remove(id);

    }

}

