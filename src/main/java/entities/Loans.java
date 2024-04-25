package entities;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

/**
 *  Describes the loans and users that are associated with the loans
 */
public class Loans {
    String barcode;
    String userID;
    String title;
    String mediaType;


    // Local date allows the program to get the current data and time which can be used to calculate the due date
    LocalDate loanDate;
    LocalDate dueDate;
    int numberOfRenews;

    public Loans(String barcode, String userID, String title, String mediaType, LocalDate loanDate, LocalDate dueDate){
        this.barcode = barcode;
        this.userID = userID;
        this.title = title;
        this.mediaType = mediaType;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.numberOfRenews = 0;
    }

    // Getters
    public String getUserID(){
        return this.userID;
    }

    public String getBarcode(){
        return this.barcode;
    }

    public String getTitle(){
        return this.title;
    }

    public String getMediaType(){
        return this.mediaType;
    }

    public LocalDate getLoanDate(){
        return this.loanDate;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }

    public int getNumberOfRenews(){
        return this.numberOfRenews;
    }

    // may be irrelevant as there is now an increment method but could be used to alter to specific number if needed
    public int setNumberOfRenews(int numberOfRenews){
        return this.numberOfRenews = numberOfRenews;
    }

    public void incrementNumberOfRenews(){
        this.numberOfRenews++;
    }


    // setter for dueDate can be used for extending loan period
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    // getter for how many days are left on the loan
    public long getDaysLeft(){
        return ChronoUnit.DAYS.between(LocalDate.now(), this.dueDate);
    }


}
