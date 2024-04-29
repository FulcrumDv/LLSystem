package entities;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.time.LocalDate;
import tools.ReadCSV;
import java.util.Iterator;

/* This class holds the necessary functions to manage the users and loans of the library
   - It holds functions to add loans, remove loans, get all loans that a user has loaned out and loans of all users
   - Uses lists to store Users and Loans objects
 */
public class Library {

    // Logging object for error handling

    Logger logger = Logger.getLogger(Library.class.getName());

    // may change some from ArrayList to hashmap

    private final List<Users> users;
    private final List<Loans> loans;
    private final List<LibraryItems> items;
    // This is my way of keeping a history when a loan is returned
    private final List<Loans> returnedLoans;
    private final List<Loans> allLoansOfUser;
    private final String libraryName = "Ulster University Library";

    // Constructor
    public Library(String ItemFilePath, String UserFilePath) {
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.returnedLoans = new ArrayList<>();
        this.items = new ArrayList<>();
        this.allLoansOfUser = new ArrayList<>();

        ReadCSV readCSV = new ReadCSV();
        this.items.addAll(readCSV.readItems(ItemFilePath));
        this.users.addAll(readCSV.readUsers(UserFilePath));
    }

    public List<Loans> getLoans(){
        return loans;
    }

    // Methods for managing the library System
    public LibraryItems searchForItem(String barcode) {
        // Using a for-each loop for readability
        try {
            for (LibraryItems item : items) {
                if (item.getBarcode().equals(barcode)) {
                    return item;
                }
            }
        } catch (Exception e) {
            logger.warning("Error when searching for item: " + e);
        }

        System.out.println("Item not found!");
        return null;

    }

    // Check if user exists (needed for loaning)
    public boolean checkUserExists(String userID) {
        boolean userExists = false;
        for (Users user : users) {
            // default setting false to avoid returning true if user is not found
            if (user.getUserID().equals(userID)) {
                userExists = true;
                break;
            }
        }

        if (!userExists) {
            System.out.println("User not found!");
        }

        return userExists;
    }


    // Loan out an item by supplying userId and barcode
    public boolean loanItem(String barcode, String userID) {
        boolean loanSuccess = false;
        try {
            LibraryItems item = searchForItem(barcode);
            if (checkUserExists(userID)) {
                if (item.isLendable(barcode)) {
                    // Sets loanDate to current date and dueDate to loanDate + loanPeriod
                    LocalDate loanDate = LocalDate.now();
                    // Determines loan period based on the instance of the object (book/multimedia)
                    LocalDate dueDate;
                    if (item instanceof Books) {
                        dueDate = loanDate.plusDays(item.getLoanPeriod());
                    } else {
                        dueDate = loanDate.plusDays(item.getLoanPeriod());
                    }
                    // Creates a new loan object
                    // marks as not lendable
                    // adds loan the loans arrayList
                    Loans loan = new Loans(barcode, userID, item.getTitle(), item.getMediaType(), loanDate, dueDate);
                    item.loanItem(userID, barcode);
                    loans.add(loan);
                    loanSuccess = true;

                } else {
                    System.out.println("Another user has already loaned this item!");
                }
            }
        } catch (Exception e) {
            logger.warning("Error loaning item: " + e);
        }
        return loanSuccess;
    }

    // Return loan
    public boolean returnLoan(String barcode) {
        try {
            Iterator<Loans> iterator = loans.iterator();
            while(iterator.hasNext()) {
                Loans loan = iterator.next();
                if (loan.getBarcode().equals(barcode)) {
                    returnedLoans.add(loan);
                    iterator.remove();
                    // Adds that loan to returned loans allowing there to be history/log of all loans

                    // Finding the LibraryItems object (book or multimedia) and making it available for lending
                    // calling searchForItem on the object on the corresponding object
                    LibraryItems item = searchForItem(barcode);
                    // Checking if object is empty
                    if (item == null) {
                        System.out.println(barcode + " not found!");
                    } else {
                        // Calls returnItem from libraryItems on the barcode of object
                        item.returnItem(barcode);
                        loan.setDateReturned(LocalDate.now());
                        return true;
                    }
                } else {
                    System.out.println("Can not return item, Loan not found!");
                }
            }
        } catch (Exception e) {
            logger.warning("Error returning loan: " + e);
        }
        return false;
    }

    // Renewing a loan
    public boolean renewLoan(String barcode){
        try{
            for (Loans loan : loans){
                if (loan.getBarcode().equals(barcode)){
                    // find item to so that the type instance of the object can be determined
                    if (searchForItem(barcode) instanceof Books){
                        // If no. of renews is less than
                        if (loan.getNumberOfRenews() < Books.getRenewLimit()){
                            loan.setDueDate(loan.getDueDate().plusDays(Books.getRenewPeriod()));
                            loan.incrementNumberOfRenews();
                            return true;
                        }else{
                            System.out.println("Number of renews exceeded! Book needs to be returned!");
                            loan.setIsRenewable(false);
                        }

                        // For readability, using else if to show that the item is a multimedia
                    }else if (searchForItem(barcode) instanceof Multimedia){
                        if (loan.getNumberOfRenews() < Multimedia.getRenewLimit()){
                            loan.setDueDate(loan.getDueDate().plusDays(Multimedia.getRenewPeriod()));
                            loan.incrementNumberOfRenews();
                            return true;
                        }else{
                            System.out.println("Number of renews exceeded! Book needs to be returned!");
                            loan.setIsRenewable(false);
                        }
                    }
                }else{
                    System.out.println("Cannot renew loan! Loan not found!");
                }
            }
        }catch (Exception e){
            logger.warning("Error renewing loan: " + e);
        }
        return false;
    }

    // Get all loans of a specific user
    public void userLoans(String userID){
        // First gets user id corresponding to the loan and adds to allLoansOfUser ArrayList
        try {
            for (Loans loan : loans) {
                if (loan.getUserID().equals(userID)) {
                    allLoansOfUser.add(loan);
                }
            }
            // Displaying the current Loans
            System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n", "Barcode", "User ID", "Title", "Media Type", "Loan Date", "Due Date", "Number of renews");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Loans loan : allLoansOfUser) {
                System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n",
                        loan.getBarcode(),
                        loan.getUserID(),
                        loan.getTitle(),
                        loan.getMediaType(),
                        loan.getLoanDate(),
                        loan.getDueDate(),
                        loan.getNumberOfRenews());
            }
        }catch(Exception e){
            logger.warning("Error displaying current loans: " + e);
        }
    }

    // Get all items that are currently on loan
    public void displayAllLoans(){
        try{
            System.out.printf("\n%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n", "Barcode", "User ID", "Title", "Media Type", "Loan Date", "Due Date", "Number of renews");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            for (Loans loan : loans){
                System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n",
                        loan.getBarcode(),
                        loan.getUserID(),
                        loan.getTitle(),
                        loan.getMediaType(),
                        loan.getLoanDate(),
                        loan.getDueDate(),
                        loan.getNumberOfRenews());
            }
        }catch (Exception e){
            logger.warning("Error displaying all loans: " + e);
        }
    }

    public void displayAllUsers(){
        try{
            System.out.printf("%-15s %-15s %-15s %-15s\n", "User ID", "First Name", "Last Name", "Email");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            for (Users user : users){
                System.out.printf("%-15s %-15s %-15s %-15s\n",
                        user.getUserID(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail());
            }
        }catch (Exception e){
            logger.warning("Error displaying all users: " + e);
        }
    }

    public void displayHistoryOfReturnedLoans(){
        try{
            System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s %-15s\n", "Barcode", "User ID", "Title", "Media Type", "Loan Date", "Due Date", "Date returned", "Number of renews");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (Loans loan : returnedLoans){
                System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s %-15s\n",
                        loan.getBarcode(),
                        loan.getUserID(),
                        loan.getTitle(),
                        loan.getMediaType(),
                        loan.getLoanDate(),
                        loan.getDueDate(),
                        loan.getDateReturned(),
                        loan.getNumberOfRenews());
            }
        }catch (Exception e){
            logger.warning("Error displaying all loans: " + e);
        }
    }

    public void LoanStatistics(){
        int bookLoansCount = 0;
        int multimediaLoansCount = 0;
        int renewedLoansCount = 0;
        // counts number of loans for books and multimedia
        for (Loans loan : loans){
            if (searchForItem(loan.getBarcode()) instanceof Books){
                bookLoansCount++;
            }else if (searchForItem(loan.getBarcode()) instanceof Multimedia){
                multimediaLoansCount++;
            }

            // counts number of loans that have been renewed more than once
            if (loan.getNumberOfRenews() > 1){
                renewedLoansCount++;
            }
        }
        // Displaying stats of loans
        System.out.println("            "+this.libraryName);
        System.out.println("------------------------------------------------------\n");
        System.out.println("Total loans: " + loans.size());
        System.out.println("Total Book Loans: " + bookLoansCount);
        System.out.println("Total Multimedia Loans: " + multimediaLoansCount);
        System.out.println("Percentage of loans renewed more than once: " + (double)renewedLoansCount/loans.size()*100 + "%");
    }
}


