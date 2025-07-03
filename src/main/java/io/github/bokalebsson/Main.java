package io.github.bokalebsson;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Initialize and display a Person instance:
        Person person1 = new Person("Erik", "Andersson", "erik@gmail.com");
        System.out.println(person1.getSummary());
        printSpacer();

        Person person2 = new Person("Greger", "Pettersson", "greger@gmail.com");
        System.out.println(person2.getSummary());
        printSpacer();

        // Initialize and display a ToDoItem instance:
        ToDoItem item1 = new ToDoItem("Grocery shopping!", "Go and buy milk.", LocalDate.now() , person1);
        System.out.println(item1.getSummary());
        printSpacer();

        ToDoItem item2 = new ToDoItem("Go to the library!", "Loan a book about pancakes.", LocalDate.of(2025,7, 4 ), person2);
        System.out.println(item2.getSummary());
        printSpacer();

        // Initialize and display a ToDoItemTask instance:
        ToDoItemTask task1 = new ToDoItemTask(item1, person1);
        System.out.println(task1.getSummary());
        printSpacer();

        ToDoItemTask task2 = new ToDoItemTask(item2, person2);
        System.out.println(task2.getSummary());
        printSpacer();

    }

    public static void printSpacer() {
        System.out.println("\n");
    }

}