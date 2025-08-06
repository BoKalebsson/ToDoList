public class MainCLI {

    private final Scanner scanner = new Scanner(System.in);

    private final PersonCLI personCLI;
    private final ToDoItemCLI toDoItemCLI;
    private final ToDoItemTaskCLI toDoItemTaskCLI;
    private final AppUserCLI appUserCLI;

    public MainCLI(PersonDAOCollection personDAO, ToDoItemDAOCollection toDoItemDAO,
                   ToDoItemTaskDAOCollection toDoItemTaskDAO, AppUserDAOCollection appUserDAO) {
        this.personCLI = new PersonCLI(personDAO, scanner);
        this.toDoItemCLI = new ToDoItemCLI(toDoItemDAO, personDAO, scanner);
        this.toDoItemTaskCLI = new ToDoItemTaskCLI(toDoItemTaskDAO, toDoItemDAO, personDAO, scanner);
        this.appUserCLI = new AppUserCLI(appUserDAO, scanner);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("==== Main Menu ====");
            System.out.println("1. Manage Persons");
            System.out.println("2. Manage ToDoItems");
            System.out.println("3. Manage ToDoItemTasks");
            System.out.println("4. Manage AppUsers");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": personCLI.run(); break;
                case "2": toDoItemCLI.run(); break;
                case "3": toDoItemTaskCLI.run(); break;
                case "4": appUserCLI.run(); break;
                case "0":
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}