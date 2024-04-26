package userInterface;
import java.util.Scanner;

public class MenuDisplay {
    private final MenuService menuService;

    public MenuDisplay() {
        this.menuService = new MenuService();
    }

    public void startMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Ascii art that displays "Library Loan System"
            System.out.println("""


                    ╔─────────────────────────────────────────────────────────────────────────────────╗
                    │ _    _ _                        _                     ___         _             │
                    │| |  (_) |__ _ _ __ _ _ _ _  _  | |   ___  __ _ _ _   / __|_  _ __| |_ ___ _ __  │
                    │| |__| | '_ \\ '_/ _` | '_| || | | |__/ _ \\/ _` | ' \\  \\__ \\ || (_-<  _/ -_) '  \\ │
                    │|____|_|_.__/_| \\__,_|_|  \\_, | |____\\___/\\__,_|_||_| |___/\\_, /__/\\__\\___|_|_|_|│
                    │                          |__/                             |__/                  │
                    ╚─────────────────────────────────────────────────────────────────────────────────╝

                    """);

            // Display's the menu options for the librarian to select
            System.out.println("Welcome! Please select one of the following options below by entering the corresponding number:");
            System.out.println("1. Issue a loan");
            System.out.println("2. Return a loan");
            System.out.println("3. Renew a loan");
            System.out.println("4. Display loans on a user");
            System.out.println("5. Display all loans");
            System.out.println("6. Display all users");
            System.out.println("7. Display a history of returned loans");
            System.out.println("8. Quit without saving");
            System.out.println("9. Save and Quit");
            System.out.print("> ");
            int userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    menuService.issueLoanSelection();
                    System.out.println("Would you like to create another loan? (Y/N): ");
                    String createAnotherLoan = input.next();
                    if (createAnotherLoan.equals("Y") || createAnotherLoan.equals("y")) {
                        menuService.issueLoanSelection();
                    }

                    ConsoleManager.clearConsole();
                    break;

                case 2:
                    menuService.returnLoanSelection();
                    System.out.println("Would you like to return another loan? (Y/N): ");
                    String returnAnotherLoan = input.next();
                    if (returnAnotherLoan.equals("Y") || returnAnotherLoan.equals("y")) {
                        menuService.returnLoanSelection();
                    }

                    ConsoleManager.clearConsole();
                    break;

                case 3:
                    menuService.renewLoanSelection();
                    System.out.println("Would you like to renew another loan? (Y/N): ");
                    String renewAnotherLoan = input.next();
                    if (renewAnotherLoan.equals("Y") || renewAnotherLoan.equals("y")) {
                        menuService.renewLoanSelection();
                    }

                    ConsoleManager.clearConsole();
                    break;

                case 4:
                    menuService.displayLoansOnUserSelection();
                    System.out.println("Would you like to display loans of another user? (Y/N): ");
                    String displayAnotherUser = input.next();
                    if (displayAnotherUser.equals("Y") || displayAnotherUser.equals("y")) {
                        menuService.displayLoansOnUserSelection();
                    }

                    ConsoleManager.clearConsole();
                    break;
                case 5:
                    menuService.displayAllLoansSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 6:
                    menuService.displayAllUsersSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 7:
                    menuService.displayHistoryLoansSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 8:
                    menuService.quitWithoutSavingSelection();
                    running = false;
                    break;
                case 9:
                    menuService.saveAndQuit();
                    running = false;
                    break;

                default:
                    System.out.println("Invalid selection! Please enter a valid number from the menu.");
                    break;
            }

        }
    }
}
