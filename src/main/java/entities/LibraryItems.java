package entities;

/* This is an abstract class that provides the basic structure for all library items
   - it implements lendable item interface
   - gives default implementation for the returnItem and isLendable methods
 */
public abstract class LibraryItems implements LendableItem {
    private final String barcode;
    private final String title;
    private final String mediaType;
    private final String year;
    private final String isbn;
    private boolean isLendable;
    private int loanPeriod;

    public LibraryItems (String barcode, String title, String mediaType, String year, String ISBN) {
        this.barcode = barcode;
        this.title = title;
        this.mediaType = mediaType;
        this.year = year;
        this.isbn = ISBN;

        // items are lendable by default
        this.isLendable = true;
        // default loan period is 0 as object types (book/multimedia) will determine their own loan period
        this.loanPeriod = 0;
    }

    // Getters and setters
    public String getBarcode(){
        return this.barcode;
    }

    public String getTitle(){
        return this.title;
    }

    public String getMediaType(){
        return this.mediaType;
    }

    // abstract method allowing subclasses to implement their own version of getAuthor
    public abstract String getCreator();

    public int getLoanPeriod(){
        return this.loanPeriod;
    }

    // Setters

    public void setLoanPeriod(int loanPeriod){
        this.loanPeriod = loanPeriod;
    }

    // The default implementation of th LendableItem interface

    public boolean isLendable(String barcode){
        return this.isLendable;
    }

    public void returnItem(String barcode){
        // The logic is that if item is returned its now lendable
        this.isLendable = true;
    }

    public void loanItem(String userID, String barcode){
        this.isLendable = false;
    }

    public String printAvailability(){
        String availability;
        if (this.isLendable){
            availability = "available";
        }else{
            availability = "on loan";
        }
        return availability;
    }

    // default display of all lendable items
    public void getInformationOnItems(){
        System.out.printf("\n%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n", "Barcode", "Author", "Title", "Media Type", "Year", "ISBN", "Availability");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-35s %-15s %-15s %-15s %-15s\n", this.barcode, getCreator(), this.title, this.mediaType, this.year, this.isbn, printAvailability());
    }
}
