package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.AppUser;
import io.github.bokalebsson.data.AppUserDAO;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUserDAOCollection implements AppUserDAO {

    private final Map<String, AppUser> users = new HashMap<>();

    @Override
    public AppUser persist(AppUser appUser) {

        // Check if appUser is null:
        if(appUser == null){
            throw new IllegalArgumentException("AppUser is not allowed to be null.");
        }

        // Get the appUsers username:
        String username = appUser.getUsername();

        // Check if appUser already exists:
        if(users.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        // Add the appUser to the map:
        users.put(username, appUser);

        return appUser;
    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return List.of();
    }

    @Override
    public void remove(String username) {

    }
}
