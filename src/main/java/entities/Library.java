package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.time.LocalDate;

/* This class holds the necessary functions to manage the users and loans of the library
 * It holds functions to add loans, remove loans, get all loans that a user has loaned out and loans of all users
 * Uses lists to store Users and Loans objects
 *
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

    public Library(){
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        this.returnedLoans = new ArrayList<>();
        this.items= new ArrayList<>();
        this.allLoansOfUser = new ArrayList<>();
    }

    // Getters
    public List<Users> getUsers(){
        return users;
    }

    public List<Loans> getLoans(){
        return loans;
    }

    public List<LibraryItems> getItems(){
        return items;
    }

    public List<Loans> getReturnedLoans(){
        return returnedLoans;
    }

    public List<Loans> getAllLoansOfUser(){
        return allLoansOfUser;
    }


    // Add user, parameters follow structure of the CSV file
    public void addUser(String userID, String name, String email){
        Users user = new Users(userID, name, email);
        this.users.add(user);
    }


    public LibraryItems searchForItem(String barcode){
        // Using a for-each loop for readability
        try{
            for (LibraryItems item : items){
                if(item.getBarcode().equals(barcode)){
                    return item;
                }
            }
        }catch (Exception e){
            logger.warning("Error when searching for item: " + e);
        }

        return null;

    }

    // Check if user exists (needed for loaning)
   public boolean checkUserExists(String userID){
        for (Users user : users){
            if (user.getUserID().equals(userID)){
                return true;
            }
        }
        return false;
   }


    // Loan out an item by supplying userId and barcode
    public void loanItem(String barcode, String userID){
        try{
            LibraryItems item = searchForItem(barcode);
            if (item.isLendable(barcode)){
                // Sets loanDate to current date and dueDate to loanDate + loanPeriod
                LocalDate loanDate = LocalDate.now();
                // Determines loan period based on the instance of the object (book/multimedia)
                LocalDate dueDate;
                if (item instanceof Books){
                    dueDate = loanDate.plusDays(((Books) item).getLoanPeriod());
                }else{
                    dueDate = loanDate.plusDays(((Multimedia) item).getLoanPeriod());
                }
                // Creates a new loan object
                // marks as not lendable
                // adds loan the loans arrayList
                Loans loan = new Loans(barcode, userID, item.getTitle(), loanDate, dueDate);
                item.loanItem(userID, barcode);
                loans.add(loan);
            }
        }catch (Exception e){
            logger.warning("Error loaning item: " + e);
        }

    }


    // Return loan
    public void returnLoan(String barcode) {
        try {
            for (Loans loan : loans) {
                // Gets barcode through getter method (encapsulation)
                if (loan.getBarcode().equals(barcode)) {
                    loans.remove(loan);
                    // Adds that loan to returned loans allowing there to be history/log of all loans
                    returnedLoans.add(loan);

                    // Finding the LibraryItems object (book or multimedia) and making it available for lending
                    // calling searchForItem on the object on the corresponding object
                    LibraryItems item = searchForItem(barcode);
                    // Checking if object is empty
                    if (item == null){
                        System.out.println(barcode + " not found!");
                    }else{
                        // Calls returnItem from libraryItems on the barcode of object
                        item.returnItem(barcode);
                    }
                }
            }
        }
        catch (Exception e){
            logger.warning("Error returning loan: " + e);
        }
    }

    // Get all loans of a specific user
    public void userLoans(String userID){
        // First gets user id corresponding to the loan and adds to allLoansOfUser ArrayList
        try{
            for(Loans loan : loans){
                if(loan.getUserID().equals(userID)){
                    allLoansOfUser.add(loan);
                }
            }

            // Displaying the current Loans
            for (Loans loan : loans){
                System.out.println("Barcode: " + loan.getBarcode() +
                        "Title: " + loan.getTitle() +
                        "Start Date: " + loan.getLoanDate() +
                        "Due Date: " + loan.getDueDate());
            }

        }catch(Exception e){
            logger.warning("Error displaying current loans: " + e);
        }
    }

    // Get all items that are currently on loan

    // Get all loans that are overdue






}
