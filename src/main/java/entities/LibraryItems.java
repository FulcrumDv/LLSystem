package entities;

/* This is an abstract class that provides the basic structure for all library items
    * implements lendable item interface
    * gives default implementation for the returnItem and isLendable methods
 */
public abstract class LibraryItems implements LendableItem {
    private String barcode;
    private String title;
    private String year;
    private String isbn;
    private boolean isLendable;
    private int loanPeriod;
    public LibraryItems (String barcode, String title, String year, String ISBN) {
        this.barcode = barcode;
        this.title = title;
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

    public String getYear(){
        return this.year;
    }

    public String getISBN(){
        return this.isbn;
    }

    public int getLoanPeriod(){
        return this.loanPeriod;
    }

    // Setters

    public void setLoanPeroid(int loanPeriod){
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

   // default display of all lendable items
    public String getLendables(){
        return "Barcode: " + this.barcode +
        "Title: " + this.title +
        "Year: " + this.year +
        "ISBN: " + this.isbn;
    }

}
