package io.github.bokalebsson;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        printSpacer();

        // Initialize and display a Person instance:
        System.out.println("Initialize and display a Person instance: \n");
        Person person1 = new Person("Erik", "Andersson", "erik@gmail.com");
        System.out.println(person1.getSummary());
        printSpacer();

        Person person2 = new Person("Greger", "Pettersson", "greger@gmail.com");
        System.out.println(person2.getSummary());
        printSpacer();

        // Initialize and display a ToDoItem instance:
        System.out.println("Initialize and display a ToDoItem instance: \n");
        ToDoItem item1 = new ToDoItem("Grocery shopping!", "Go and buy milk.", LocalDate.now() , person1);
        System.out.println(item1.getSummary());
        printSpacer();

        ToDoItem item2 = new ToDoItem("Go to the library!", "Loan a book about pancakes.", LocalDate.of(2026,7, 4 ), person2);
        System.out.println(item2.getSummary());
        printSpacer();

        // Initialize and display a ToDoItemTask instance:
        System.out.println("Initialize and display a ToDoItemTask instance: \n");
        ToDoItemTask task1 = new ToDoItemTask(item1, person1);
        System.out.println(task1.getSummary());
        printSpacer();

        ToDoItemTask task2 = new ToDoItemTask(item2, person2);
        System.out.println(task2.getSummary());
        printSpacer();

        // Simulate setting a task to done:
        System.out.println("Simulate setting a task to done: \n");
        item1.markDone();
        System.out.println(task1.getSummary());
        printSpacer();

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
        printSpacer();

        // Print the hash code of the user OriginalUser:
        System.out.println("Hash code for user " + OriginalUser.getUsername() + ": " + OriginalUser.hashCode());

        // Print the hash code of the user Admin:
        System.out.println("Hash code for user " + Admin.getUsername() + ": " + Admin.hashCode());
        printSpacer();
    }

    public static void printSpacer() {
        System.out.println("\n");
    }

}