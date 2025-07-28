package io.github.bokalebsson;

import io.github.bokalebsson.data.impl.AppUserDAOCollection;
import io.github.bokalebsson.data.impl.PersonDAOCollection;
import io.github.bokalebsson.data.util.FileStorageManager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Code testing before DAO:
    /*  // Initialize and display a Person instance:
        System.out.println("Initialize and display a Person instance: \n");
        Person person1 = new Person("Erik", "Andersson", "erik@gmail.com");
        System.out.println(person1);

        Person person2 = new Person("Greger", "Pettersson", "greger@gmail.com");
        System.out.println(person2);

        // Initialize and display a ToDoItem instance:
        System.out.println("Initialize and display a ToDoItem instance: \n");
        ToDoItem item1 = new ToDoItem("Grocery shopping!", "Go and buy milk.", LocalDate.now() , person1);
        System.out.println(item1);

        ToDoItem item2 = new ToDoItem("Go to the library!", "Loan a book about pancakes.", LocalDate.of(2026,7, 4 ), person2);
        System.out.println(item2);

        // Initialize and display a ToDoItemTask instance:
        System.out.println("Initialize and display a ToDoItemTask instance: \n");
        ToDoItemTask task1 = new ToDoItemTask(item1, person1);
        System.out.println(task1);

        ToDoItemTask task2 = new ToDoItemTask(item2, person2);
        System.out.println(task2);

        // Simulate setting a task to done:
        System.out.println("Simulate setting a task to done: \n");
        item1.markDone();
        System.out.println(task1);

        // Simulate setting up an AppUser:
        AppUser OriginalUser = new AppUser("Original", "123456", AppRole.ROLE_APP_USER);
        System.out.println(OriginalUser);

        // Simulate setting up an AppAdmin:
        AppUser Admin = new AppUser("Admin", "654321", AppRole.ROLE_APP_ADMIN);
        System.out.println(Admin);

        // Simulate equals() true:
        AppUser a = new AppUser("Erik", "abc123", AppRole.ROLE_APP_USER);
        AppUser b = new AppUser("Erik", "123abc", AppRole.ROLE_APP_USER);
        System.out.println("Is user a equal to user b: " + a.equals(b));

        // Simulate equals() false:
        AppUser c = new AppUser("Erik", "abc123", AppRole.ROLE_APP_USER);
        AppUser d = new AppUser("Greger", "123abc", AppRole.ROLE_APP_USER);
        System.out.println("Is user a equal to user b: " + c.equals(d));

        // Print the hash code of the user OriginalUser:
        System.out.println("Hash code for user " + OriginalUser.getUsername() + ": " + OriginalUser.hashCode());

        // Print the hash code of the user Admin:
        System.out.println("Hash code for user " + Admin.getUsername() + ": " + Admin.hashCode());

        // Simulate testing the hashcode() and the equals():
        AppUser user1 = new AppUser("Greger", "password1", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("Greger", "password2", AppRole.ROLE_APP_USER);

        // Print hash codes
        System.out.println("Hash code for user1: " + user1.hashCode());
        System.out.println("Hash code for user2: " + user2.hashCode());

        // Check if hash codes are equal
        if (user1.hashCode() == user2.hashCode()) {
            System.out.println("Hash codes are equal, as expected.");
        } else {
            System.out.println("Hash codes are NOT equal, something is wrong.");
        }

        // Use equals() to check if the objects are equal
        if (user1.equals(user2)) {
            System.out.println("Objects are equal according to equals().");
        } else {
            System.out.println("Objects are NOT equal according to equals().");
        }
        printSpacer();*/

/*        // Code testing after DAO:

        // Initialize a collection instance:
        PersonDAOCollection persons = new PersonDAOCollection();

        // Initialize and display a Person instance:
        Person evert = new Person("Evert", "Karlsson", "evert@test.nu");
        System.out.println("Person 1 created: \n" + evert);

        // Adding the first person to the collection:
        persons.persist(evert);

        *//*
        * Finding and printing out a person with id from a collection
        * (This will trigger an exception if id is a negative number or zero):
        *//*
        System.out.println("Person found by id in collection: \n" + persons.findById(1));

        // Initialize and display another Person instance:
        Person elin = new Person("Elin", "Hansson", "elin@test.nu");
        System.out.println("Person 2 created: \n" + elin);

        // Adding the second person to the collection:
        persons.persist(elin);

        // Printing out the entire list of persons:
        System.out.println("All persons in the list: ");
        for (Person person : persons.findAll()){
            System.out.println(person);
        }

        *//*
         * Finding and printing out a person with email from a collection
         * (This will trigger an exception if the email is null, empty or not found)
         *//*
        System.out.println("Person found by email in collection: \n" + persons.findByEmail("elin@test.nu"));

        // Simulating removing a person by id from the collection:
        persons.remove(1);

        // Printing out the entire list of persons after removing one:
        System.out.println("All persons in the list, after calling the remove(): ");
        for (Person person : persons.findAll()){
            System.out.println(person);
        }

        // Initialize a collection instance:
        AppUserDAOCollection users = new AppUserDAOCollection();

        // Creating some AppUsers:
        AppUser user1 = new AppUser("Leif", "123456", AppRole.ROLE_APP_USER);
        AppUser user2 = new AppUser("Sofie", "654321", AppRole.ROLE_APP_ADMIN);

        // Adding the AppUsers to the collection:
        users.persist(user1);
        users.persist(user2);

        // Printing out the entire list of AppUsers:
        System.out.println("All AppUsers in the list: ");
        for (AppUser appUser : users.findAll()) {
            System.out.println(appUser);
        }

        *//*
         * Finding and printing out a appUser with username from the collection:
         * (This will trigger an exception if the username is null, empty or not found)
         *//*
        System.out.println("Person found by username in collection: \n" + users.findByUsername("Leif"));

        *//*
         * Simulating removing appUser with username from the collection:
         * (This will trigger an exception if the username is null, empty or not found)
         *//*
        users.remove("Leif");

        // Printing out the entire list of AppUsers after removing one:
        System.out.println("All AppUsers in the list, after calling the remove(): ");
        for (AppUser appUser : users.findAll()) {
            System.out.println(appUser);
        }*/

/*        // Code testing with Jackson and FileStorage Manager with Persons:
        FileStorageManager fileStorageManager = new FileStorageManager();

        // Create a list with Persons:
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Erik", "Svensson", "erik@example.com"));
        persons.add(new Person("Anna", "Bengtsson", "anna@example.com"));

        // Define the file to save to / load from:
        File file = new File("persons.json");

        // Trying to save the list to a file:
        try {
            fileStorageManager.saveListToFile(persons, file, Person.class);
            System.out.println("The list was saved to file. \n");
        } catch (IOException e) {
            System.out.println("Something went wrong trying to save the file: " + e.getMessage());
        }

        // Try to read from the file:
        try {
            List<Person> loadedPersons = fileStorageManager.loadListFromFile(file, Person.class);
            System.out.println("The list loaded from file: \n");
            for (Person p : loadedPersons) {
                System.out.println(p);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong trying to load the file: " + e.getMessage());
        }*/

        // Code testing with Jackson and FileStorage Manager with ToDoItems:
        FileStorageManager fileStorageManager = new FileStorageManager();

        // Add a person that needs to be the creator:
        Person creator = new Person("Elin", "Hansson", "elin@example.com");

        // Create some ToDoItems:
        ToDoItem item1 = new ToDoItem("Cleaning", "Vacuum and mop the floors", LocalDate.now().plusDays(1), creator);
        ToDoItem item2 = new ToDoItem("Study", "Repeat Java", LocalDate.now().plusDays(2), creator);
        ToDoItem item3 = new ToDoItem("Exercise", "Run 5 km", LocalDate.now().plusDays(3), creator);

        // Mark one as done:
        item2.setDone(true);

        // Create a list with ToDoItems:
        List<ToDoItem> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        File file = new File("todoitems.json");

        try {
            fileStorageManager.saveListToFile(items, file, ToDoItem.class);
            System.out.println("ToDoItems was saved to file. \n");
        } catch (IOException e) {
            System.err.println("Something went wrong trying to save the file: " + e.getMessage());
        }

        try {
            List<ToDoItem> loadedItems = fileStorageManager.loadListFromFile(file, ToDoItem.class);
            System.out.println("The list loaded from file: \n");
            for (ToDoItem item : loadedItems) {
                System.out.println(item);
            }
        } catch (IOException e) {
            System.err.println("Something went wrong trying to load the file: " + e.getMessage());
        }

    }

    public static void printSpacer() {
        System.out.println("\n");
    }

}