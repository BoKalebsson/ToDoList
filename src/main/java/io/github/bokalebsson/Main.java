package io.github.bokalebsson;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

               // Initialize and display a Person instance:
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
        printSpacer();


    }

    public static void printSpacer() {
        System.out.println("\n");
    }

}