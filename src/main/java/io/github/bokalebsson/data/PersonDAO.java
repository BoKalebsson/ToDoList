package io.github.bokalebsson.data;

import io.github.bokalebsson.model.Person;

import java.util.Collection;

public interface PersonDAO {

    /**
     * Adds a new Person to the data store.
     * @param person the Person object to persist
     * @return the persisted Person object, possibly with updated fields like generated id
     */
    Person persist(Person person);

    /**
     * Finds and returns a Person by their unique id.
     * @param id the unique identifier of the Person
     * @return the Person object if found, otherwise null (or optional depending on implementation)
     */
    Person findById(int id);

    /**
     * Finds and returns a Person by their unique email address.
     * @param email the email address of the Person
     * @return the Person object if found, otherwise null
     */
    Person findByEmail(String email);

    /**
     * Returns all Person objects currently stored.
     * @return a collection containing all Persons
     */
    Collection<Person> findAll();

    /**
     * Updates an existing Person in the data store.
     * @param person the Person object with updated data
     * @return the updated Person object
     */
    Person update(Person person);

    /**
     * Removes a Person from the data store by their unique id.
     * @param id the unique identifier of the Person to remove
     */
    void remove(int id);

}
