package io.github.bokalebsson.dao;

import io.github.bokalebsson.model.AppUser;

import java.util.Collection;

public interface AppUserDAO {

    /**
     * Adds a new AppUser to the data store.
     * @param appUser the AppUser object to persist
     * @return the persisted AppUser object, possibly with updated fields like generated id
     */
    AppUser persist(AppUser appUser);

    /**
     * Finds and returns an AppUser by their unique username.
     * @param username the username of the AppUser
     * @return the AppUser object if found, otherwise null (or Optional depending on implementation)
     */
    AppUser findByUsername(String username);

    /**
     * Returns all AppUser objects currently stored.
     * @return a collection containing all AppUsers
     */
    Collection<AppUser> findAll();

    /**
     * Updates an existing AppUser in the data store.
     * @param appUser the AppUser object with updated data
     * @return the updated AppUser object
     */
    AppUser update(AppUser appUser);

    /**
     * Removes an AppUser from the data store by their username.
     * @param username the username of the AppUser to remove
     */
    void remove(String username);

}
