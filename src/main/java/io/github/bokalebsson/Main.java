package io.github.bokalebsson;

import io.github.bokalebsson.dao.impl.AppUserDAOCollection;
import io.github.bokalebsson.dao.impl.PersonDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemDAOCollection;
import io.github.bokalebsson.dao.impl.ToDoItemTaskDAOCollection;
import io.github.bokalebsson.util.ApplicationDataManager;
import io.github.bokalebsson.model.*;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Application app = new Application("data");
        app.start();
    }

}