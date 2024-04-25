package userInterface;
import entities.Library;
import java.util.Scanner;

public class Menu {
    private Library library;

    public Menu() {
         this.library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
    }

    public void startMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while(running){
            // Ascii art that displays "Library Loan System"
            System.out.println("\n" +
                    "\n" +
                    "╔─────────────────────────────────────────────────────────────────────────────────╗\n" +
                    "│ _    _ _                        _                     ___         _             │\n" +
                    "│| |  (_) |__ _ _ __ _ _ _ _  _  | |   ___  __ _ _ _   / __|_  _ __| |_ ___ _ __  │\n" +
                    "│| |__| | '_ \\ '_/ _` | '_| || | | |__/ _ \\/ _` | ' \\  \\__ \\ || (_-<  _/ -_) '  \\ │\n" +
                    "│|____|_|_.__/_| \\__,_|_|  \\_, | |____\\___/\\__,_|_||_| |___/\\_, /__/\\__\\___|_|_|_|│\n" +
                    "│                          |__/                             |__/                  │\n" +
                    "╚─────────────────────────────────────────────────────────────────────────────────╝\n" +
                    "\n");

            // Display's the menu options for the librarian to select
            System.out.println("Welcome! Please select one of the following options below by entering the corresponding number:");
            System.out.println("1. Issue a loan");
            System.out.println("2. Return a loan");
            System.out.println("3. Renew a loan");
            System.out.println("4. Display loans on a user");
            System.out.println("5. Display all loans");
            System.out.println("6. Display all users");
            System.out.println("7. Quit");
            System.out.println("> ");
            int userInput = input.nextInt();

            switch (userInput) {
                case 1:
                    System.out.println("Do you want to issue loan (Y/N): ");
                    String loanAnswer = input.next();
                    if (loanAnswer.equals("Y") || loanAnswer.equals("y")) {
                        System.out.println("You've chosen to issue a loan, enter the user ID and the barcode of the item");
                        System.out.println("User ID: ");
                        String userID = input.next();
                        System.out.println("Barcode: ");
                        String barcode = input.next();
                        library.loanItem(barcode, userID);
                    }
                    break;
                case 2:
                    System.out.println("Do you want to return a loan? (Y/N): ");
                    String returnAnswer = input.next();
                    if (returnAnswer.equals("Y") || returnAnswer.equals("y")){
                        System.out.println("You've chosen to return a loan, please enter the barcode of the item you wish to return");
                        System.out.println("Barcode: ");
                        String barcodeForReturn = input.next();
                        library.returnLoan(barcodeForReturn);
                    }
                    break;
                case 3:
                    System.out.println("Dou want to renew a loan? (Y/N): ");
                    String renewAnswer = input.next();
                    if (renewAnswer.equals("Y") || renewAnswer.equals("y")){
                        System.out.println("You've chosen to renew a loan, please enter the barcode of the item you wish to renew");
                        System.out.println("Barcode: ");
                        String barcodeForRenew = input.next();
                        library.renewLoan(barcodeForRenew);
                    }
                    break;
                case 4:
                    System.out.println("Do you want to display loans of a user? (Y/N): ");
                    String displayAnswer = input.next();
                    if (displayAnswer.equals("Y") || displayAnswer.equals("y")){
                        System.out.println("You've chosen to display loans on a user, please enter the user ID");
                        System.out.println("User ID: ");
                        String userIDForDisplay = input.next();
                        library.userLoans(userIDForDisplay);
                    }
                    break;
                case 5:
                    System.out.println("Do you want to display all loans (Y/N): ");
                    String displayAllAnswer = input.next();
                    if (displayAllAnswer.equals("Y") || displayAllAnswer.equals("y")){
                        System.out.println("You've chosen to display all loans");
                        library.displayAllLoans();
                    }
                    break;
                case 6:
                    System.out.println("Do you want to display all users (Y/N): ");
                    String displayUsersAnswer = input.next();
                    if (displayUsersAnswer.equals("Y") || displayUsersAnswer.equals("y")){
                        System.out.println("You've chosen to display all users");
                        //library.displayAllUsers();
                    }
                    break;
                case 7:
                    System.out.println("Do you want to quit (Y/N): ");
                    String quitAnswer = input.next();
                    if (quitAnswer.equals("Y") || quitAnswer.equals("y")){
                        System.out.println("You've chosen to quit, goodbye!");
                        running = false;
                    }
                    break;
            }

        }
    }


}
