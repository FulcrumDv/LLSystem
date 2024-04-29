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
            // Display the ACSCII art
            ConsoleManager.displayAsciiArt();
            // Display the menu options
            ConsoleManager.displaySelectionMenu();

            // Get the user input
            int userInput = input.nextInt();

            // Handle user input using switch cases
            switch (userInput) {
                case 1:
                    boolean createAnotherLoan = true;
                    while (createAnotherLoan) {
                        menuService.issueLoanSelection();
                        System.out.println("Would you like to create another loan? (Y/N): ");
                        String createAnotherLoanString = input.next();
                        if (createAnotherLoanString.equals("N") || createAnotherLoanString.equals("n")) {
                            createAnotherLoan = false;
                        }
                    }

                    ConsoleManager.clearConsole();
                    break;

                case 2:
                    boolean returnAnotherLoan = true;
                    while (returnAnotherLoan) {
                        menuService.returnLoanSelection();
                        System.out.println("Would you like to return another item? (Y/N): ");
                        String returnAnotherLoanString = input.next();
                        if (returnAnotherLoanString.equals("N") || returnAnotherLoanString.equals("n")) {
                            returnAnotherLoan = false;
                        }
                    }

                    ConsoleManager.clearConsole();
                    break;

                case 3:
                    boolean renewAnotherLoan = true;
                    while (renewAnotherLoan) {
                        menuService.renewLoanSelection();
                        System.out.println("Would you like to renew another loan? (Y/N): ");
                        String renewAnotherLoanString = input.next();
                        if (renewAnotherLoanString.equals("N") || renewAnotherLoanString.equals("n")) {
                            renewAnotherLoan = false;
                        }
                    }

                    ConsoleManager.clearConsole();
                    break;
                case 4:
                    menuService.lookUpItemSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 5:
                    menuService.displayLoansOnUserSelection();
                    System.out.println("Would you like to display loans of another user? (Y/N): ");
                    String displayAnotherUser = input.next();
                    if (displayAnotherUser.equals("Y") || displayAnotherUser.equals("y")) {
                        menuService.displayLoansOnUserSelection();
                    }

                    ConsoleManager.clearConsole();
                    break;
                case 6:
                    menuService.displayAllLoansSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 7:
                    menuService.displayAllUsersSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 8:
                    menuService.displayHistoryLoansSelection();
                    running = menuService.askUserToContinue();
                    break;
                case 9:
                    menuService.quitWithoutSavingSelection();
                    running = false;
                    break;
                case 10:
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
