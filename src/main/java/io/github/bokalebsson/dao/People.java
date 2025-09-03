package io.github.bokalebsson.dao;

import io.github.bokalebsson.model.Person;

import java.util.Collection;

public interface People {

    /**
     * Persist a new Person.
     * Implementations should return the persisted instance (with generated id set if applicable).
     */
    Person create(Person person);

    /**
     * @return all persons (never null; may be empty).
     */
    Collection<Person> findAll();

    /**
     * Find a person by id.
     * @return the person if found, otherwise null.
     */
    Person findById(int id);

    /**
     * Find persons by name.
     * @return matching persons (never null; may be empty).
     */
    Collection<Person> findByName(String name);

    /**
     * Update an existing person (identified by its id).
     */
    Person update(Person person);

    /**
     * Delete a person by id.
     * @return true if a record was deleted; false otherwise.
     */
    boolean deleteById(int id);

}
