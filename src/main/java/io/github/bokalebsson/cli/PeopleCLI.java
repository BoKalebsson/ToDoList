package io.github.bokalebsson.cli;

import io.github.bokalebsson.dao.impl.PeopleDAO;

import java.util.Scanner;

public class PeopleCLI {

    private final PeopleDAO peopleDAO;
    private final Scanner scanner;

    public PeopleCLI(PeopleDAO peopleDAO, Scanner scanner) {
        this.peopleDAO = peopleDAO;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();

            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createPerson();
                case "2" -> listAllPeople();
                case "3" -> findById();
                case "4" -> findByName();
                case "5" -> updatePerson();
                case "6" -> deletePerson();
                case "0" -> running = false;
                default -> System.out.println("⚠️ Invalid option. Try again!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nPerson Menu:");
        System.out.println("1. Create person");
        System.out.println("2. List all people");
        System.out.println("3. Find person by ID");
        System.out.println("4. Find person by name");
        System.out.println("5. Update person");
        System.out.println("6. Delete person");
        System.out.println("0. Back to main menu");
    }

    private void createPerson() {}
    private void listAllPeople() {}
    private void findById() {}
    private void findByName() {}
    private void updatePerson() {}
    private void deletePerson() {}
}
