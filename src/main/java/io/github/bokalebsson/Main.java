package io.github.bokalebsson;

import io.github.bokalebsson.dao.database.DBTodo;
import io.github.bokalebsson.dao.database.ToDoItems;
import io.github.bokalebsson.dao.database.ToDoItemsDAO;

import java.time.LocalDate;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        // Create a ToDoItemsDAO instance:
        ToDoItems todoDao = new ToDoItemsDAO();

        // Create a new DBTodo:
        DBTodo newTodo = new DBTodo(
                "Handla mat",
                "Mjölk, bröd, ost",
                LocalDate.of(2025, 9, 10),
                false,
                1
        );

        // Testing create()-method:
        try {
            DBTodo createdTodo = todoDao.create(newTodo);

            if (createdTodo != null) {
                System.out.println("✅ ToDo created successfully:\n" + createdTodo);
            } else {
                System.out.println("⚠️ ToDo could not be created.");
            }

            // Testing findAll()-method:
            Collection<DBTodo> todos = todoDao.findAll();

            if (todos.isEmpty()) {
                System.out.println("⚠️ No ToDos found in the database.");
            } else {
                System.out.println("✅ Found " + todos.size() + " ToDos:");
                for (DBTodo t : todos) {
                    System.out.println(t);
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Something went wrong:");
            e.printStackTrace();
        }


    }

}