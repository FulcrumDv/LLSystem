package entities;

import java.time.LocalDate;

//This class describes structure for a loan object in the library
public class Loans {
    private final String barcode;
    private final String userID;
    private final String title;
    private final String mediaType;
    // Local date allows the program to get the current data and time which can be used to calculate the due date
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private int numberOfRenews;
    private boolean isRenewable;
    private LocalDate dateReturned;

    public Loans(String barcode, String userID, String title, String mediaType, LocalDate loanDate, LocalDate dueDate) {
        this.barcode = barcode;
        this.userID = userID;
        this.title = title;
        this.mediaType = mediaType;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.numberOfRenews = 0;
        this.isRenewable = true;
        this.dateReturned = null;
    }

    // Getters
    public String getUserID() {
        return this.userID;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public LocalDate getLoanDate() {
        return this.loanDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public int getNumberOfRenews() {
        return this.numberOfRenews;
    }

    public void setIsRenewable(boolean isRenewable) {
        this.isRenewable = isRenewable;
    }

    public LocalDate getDateReturned() {
        return this.dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public void incrementNumberOfRenews() {
        this.numberOfRenews++;
    }


    // setter for dueDate can be used for extending loan period
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
