package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.Person;
import io.github.bokalebsson.data.PersonDAO;

import java.util.*;

public class PersonDAOCollection implements PersonDAO {

    private final Map<Integer, Person> persons = new HashMap<>();

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

