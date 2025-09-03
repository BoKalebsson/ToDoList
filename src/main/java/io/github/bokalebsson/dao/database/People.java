package io.github.bokalebsson.dao.database;

import java.util.Collection;

public interface People {

    /**
     * Persist a new Person.
     * Implementations should return the persisted instance (with generated id set if applicable).
     */
    DBPerson create(DBPerson dbPerson);

    /**
     * @return all persons (never null; may be empty).
     */
    Collection<DBPerson> findAll();

    /**
     * Find a person by id.
     * @return the person if found, otherwise null.
     */
    DBPerson findById(int id);

    /**
     * Find persons by name.
     * @return matching persons (never null; may be empty).
     */
    Collection<DBPerson> findByName(String name);

    /**
     * Update an existing person (identified by its id).
     */
    DBPerson update(DBPerson dbPerson);

    /**
     * Delete a person by id.
     * @return true if a record was deleted; false otherwise.
     */
    boolean deleteById(int id);

}
