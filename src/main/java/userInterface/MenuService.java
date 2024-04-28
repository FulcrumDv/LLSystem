package userInterface;
import entities.Library;
import java.util.Objects;
import java.util.Scanner;
import entities.LibraryItems;

public class MenuService {

    private final Library library;

    public MenuService() {
        this.library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
    }

    Scanner input = new Scanner(System.in);
    /* ask user if they want to continue after choosing an option
       and calls clearConsole if that answer is yes
     */

    public boolean askUserToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to make another selection? (Y/N): ");
        String programContinue = input.next();
        if (Objects.equals(programContinue, "N") || Objects.equals(programContinue, "n")) {

            System.out.println("Thank you!. Goodbye!");
            return false;
        }

        ConsoleManager.clearConsole();
        return true;
    }

    // 1. Issue a loan
    public void issueLoanSelection() {
        System.out.println("\nEnter the user ID and the barcode of the item");
        System.out.print("User ID: ");
        String userID = input.next();
        System.out.print("Barcode: ");
        String barcode = input.next();
        if (library.loanItem(barcode, userID)) {
            System.out.println("Loan created successfully!\n\n\n");
        }
    }
    // 2. Return a loan
    public void returnLoanSelection() {
        System.out.print("Do you want to return a loan? (Y/N): ");
        String returnAnswer = input.next();
        if (returnAnswer.equals("Y") || returnAnswer.equals("y")){
            System.out.println("\nEnter the barcode of the item you wish to return");
            System.out.print("Barcode: ");
            String barcodeForReturn = input.next();
            if(library.returnLoan(barcodeForReturn)){
                System.out.println("Loan returned successfully!");
            }
        }
    }

    // 3. Renew a loan
    public void renewLoanSelection() {
        System.out.print("Dou want to renew a loan? (Y/N): ");
        String renewAnswer = input.next();
        if (renewAnswer.equals("Y") || renewAnswer.equals("y")) {
            System.out.println("\nEnter the barcode of the item you wish to renew below");
            System.out.print("Barcode: ");
            String barcodeForRenew = input.next();
            if (library.renewLoan(barcodeForRenew)) {
                System.out.println("Loan renewed successfully!");
            }

        }
    }

    // 4. Look up Item
    public void lookUpItemSelection() {
        System.out.print("Do you want to look up an item? (Y/N): ");
        String lookUpAnswer = input.next();
        if (lookUpAnswer.equals("Y") || lookUpAnswer.equals("y")) {
            System.out.println("Enter the barcode of the item you wish to look up");
            System.out.print("Barcode: ");
            String barcodeForLookUp = input.next();
            LibraryItems item = library.searchForItem(barcodeForLookUp);
            if (item != null) {
                item.getInformationOnItems();
            } else {
                System.out.println("Barcode doesn't match any item in the library");
            }
        }
    }

    // 5. Display loans on a user
    public void displayLoansOnUserSelection() {
        System.out.print("Do you want to display loans of a user? (Y/N): ");
        String displayAnswer = input.next();
        if (displayAnswer.equals("Y") || displayAnswer.equals("y")){
            System.out.println("Enter the user ID");
            System.out.print("User ID: ");
            String userIDForDisplay = input.next();
            library.userLoans(userIDForDisplay);
        }
    }

    // 6. Display all loans
    public void displayAllLoansSelection(){
        System.out.print("Do you want to display all loans (Y/N): ");
        String displayAllAnswer = input.next();
        if (displayAllAnswer.equals("Y") || displayAllAnswer.equals("y")){
            library.displayAllLoans();
        }

        System.out.print("\n\nDisplay Loan Statistics? (Y/N): ");
        String displayStatisticsAnswer = input.next();
        if (displayStatisticsAnswer.equals("Y") || displayStatisticsAnswer.equals("y")){
            library.LoanStatistics();
        }
    }

    // 7. Display all users
    public void displayAllUsersSelection(){
        System.out.print("Do you want to display all users (Y/N): ");
        String displayUsersAnswer = input.next();
        if (displayUsersAnswer.equals("Y") || displayUsersAnswer.equals("y")){
            library.displayAllUsers();
        }
    }
    // 8. Display a history of returned loans
    public void displayHistoryLoansSelection(){
        System.out.println("Do you want to display a history of returned loans? (Y/N): ");
        String displayHistoryAnswer = input.next();
        if (displayHistoryAnswer.equals("Y") || displayHistoryAnswer.equals("y")){
            library.displayHistoryOfReturnedLoans();
        }
    }


    // 9. Quit without saving
    public void quitWithoutSavingSelection() {
        System.out.print("Are you sure you want to quit without saving? (Y/N): ");
        String quitAnswer = input.next();
        if (quitAnswer.equals("Y") || quitAnswer.equals("y")) {
            System.out.println("No changes made. Goodbye!");
        }
    }

    // 10. Save and quit
    public void saveAndQuit() {
        System.out.println("Are you sure you want to save and quit? (Y/N): ");
        String saveAnswer = input.next();
        if (saveAnswer.equals("Y") || saveAnswer.equals("y")) {
            System.out.println("Saving changes...");
            // write to file call
            System.out.println("Changes saved. Goodbye!");
        }
    }
}
