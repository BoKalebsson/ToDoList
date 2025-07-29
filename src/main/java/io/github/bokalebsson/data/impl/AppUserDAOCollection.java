package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.AppUser;
import io.github.bokalebsson.data.AppUserDAO;

import java.util.*;

public class AppUserDAOCollection implements AppUserDAO {

    private Map<String, AppUser> users;

    // Constructor: Default
    public AppUserDAOCollection() {
        this.users = new HashMap<>();
    }

    // Constructor: Load from Collection<AppUser> and build internal Map
    public AppUserDAOCollection(Collection<AppUser> appUserCollection) {
        // Check if the collection is null
        if (appUserCollection == null) {
            throw new IllegalArgumentException("AppUser collection cannot be null.");
        }

        // Create a new empty map to hold app users
        Map<String, AppUser> map = new HashMap<>();

        // Populate the map with each appUser from the collection
        for (AppUser appUser : appUserCollection) {
            String username = appUser.getUsername();

            // Check for duplicate usernames to avoid overwriting
            if (map.containsKey(username)) {
                throw new IllegalArgumentException("Duplicate username found: " + username);
            }

            map.put(username, appUser);
        }

        // Assign the map to our internal collection
        this.users = map;
    }

    @Override
    public AppUser persist(AppUser appUser) {

        // Check if appUser is null:
        if(appUser == null){
            throw new IllegalArgumentException("AppUser is not allowed to be null.");
        }

        // Get the appUsers username:
        String username = appUser.getUsername();

        // Check if appUser already exists in the collection:
        if(users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        // Add the appUser to the map:
        users.put(username, appUser);

        // Returns the appUser:
        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {

        if (username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        AppUser appUser = users.get(username);
        if (appUser == null) {
            throw new NoSuchElementException("No user found with username: " + username);
        }
        return appUser;
    }

    @Override
    public Collection<AppUser> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public AppUser update(AppUser appUser) {
        // 1. Check if the input is null
        if (appUser == null) {
            throw new IllegalArgumentException("AppUser cannot be null.");
        }

        // 2. Get the username
        String username = appUser.getUsername();

        // 3. Check if the username is valid
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        // 4. Check if an AppUser with this username exists
        if (!users.containsKey(username)) {
            throw new IllegalArgumentException("AppUser with username '" + username + "' does not exist.");
        }

        // 5. Update the appUser in the map
        users.put(username, appUser);

        // 6. Return the updated appUser
        return appUser;
    }

    @Override
    public void remove(String username) {

        if (username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        if(!users.containsKey(username)) {
            throw new IllegalArgumentException("No one found with the following username: " + username);
        }

        users.remove(username);
    }

}
