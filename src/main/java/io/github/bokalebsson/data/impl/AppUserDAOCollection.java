package io.github.bokalebsson.data.impl;

import io.github.bokalebsson.AppUser;
import io.github.bokalebsson.data.AppUserDAO;

import java.util.Collection;
import java.util.List;

public class AppUserDAOCollection implements AppUserDAO {

    @Override
    public AppUser persist(AppUser appUser) {
        return null;
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
