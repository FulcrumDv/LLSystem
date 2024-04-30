package userInterface;
import entities.Library;
import java.util.Objects;
import java.util.Scanner;
import entities.LibraryItems;
import tools.ReadCSV;
import tools.WriteCSV;
import entities.Loans;
import java.util.List;

class MenuService {

    private final Library library;

    public MenuService() {
        this.library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
    }

    // Method to check if file should be overwritten or appended
    public void checkIfFileEmpty(String filepath, List<Loans> loans) {
        ReadCSV readCSV = new ReadCSV();
        WriteCSV writeCSV = new WriteCSV();
        if (readCSV.isCSVEmpty(filepath)) {
            writeCSV.writeLoans(filepath, loans);
        } else {
            for (Loans loan : loans) {
                writeCSV.appendToLoans(filepath, loan);
            }
        }
    }

    Scanner input = new Scanner(System.in);
    /* ask user if they want to continue after choosing an option
       and calls clearConsole if that answer is yes
     */

    // boolean method to ask user if they want to continue
    public boolean askUserToContinue() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nDo you want to make another selection? (Y/N): ");
        String programContinue = input.next();
        if (Objects.equals(programContinue, "N") || Objects.equals(programContinue, "n")) {
            checkIfFileEmpty("src/main/resources/LOANS.csv", library.getLoans());
            library.getLoans().clear();
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
        if (returnAnswer.equals("Y") || returnAnswer.equals("y")) {
            System.out.println("\nEnter the barcode of the item you wish to return");
            System.out.print("Barcode: ");
            String barcodeForReturn = input.next();
            if (library.returnLoan(barcodeForReturn)) {
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
        if (displayAnswer.equals("Y") || displayAnswer.equals("y")) {
            System.out.println("Enter the user ID");
            System.out.print("User ID: ");
            String userIDForDisplay = input.next();
            library.userLoans(userIDForDisplay);
        }
    }

    // 6. Display all loans
    public void displayAllLoansSelection() {
        // Call the displayAllLoans method from the Library class
        library.displayAllLoans();

        // Check if there are any loans that have not been saved yet
        if (!library.getLoans().isEmpty()) {
            System.out.println("Some loans have not been saved yet, would you like to save them? (Y/N): ");
            String saveAnswer = input.next();
            if (saveAnswer.equals("Y") || saveAnswer.equals("y")) {
                checkIfFileEmpty("src/main/resources/LOANS.csv", library.getLoans());
                System.out.println("Loans saved successfully!");
            }else {
                System.out.println("Loans not saved.");
            }

            library.getLoans().clear();
        }

        // Asks the user if they want to display loan statistics
        System.out.print("\n\nDisplay Loan Statistics? (Y/N): ");
        String displayStatisticsAnswer = input.next();
        System.out.println();
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

    // 8. Save and quit
    public void saveAndQuit() {
        System.out.println("Are you sure you want to save and quit? (Y/N): ");
        String saveAnswer = input.next();
        if (saveAnswer.equals("Y") || saveAnswer.equals("y")) {
            System.out.println("Saving changes...");
            checkIfFileEmpty("src/main/resources/LOANS.csv", library.getLoans());
            System.out.println("Changes saved. Goodbye!");
        }
    }
}
