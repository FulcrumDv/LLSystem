package entities;

// Books is a subclass of LibraryItems and Inherits the properties of LibraryItems
public class Books extends LibraryItems{

    private final String author;
    // renewLimit is const, so will not change
    private static final int renewLimit = 3;
    private final int loanPeriod = 30;
    private static final int renewPeriod = 14;

    public Books(String barcode, String author, String title, String mediaType, String year, String isbn){
        super(barcode, title, mediaType, year, isbn);
        this.author = author;
    }

    public static int getRenewLimit(){
        return renewLimit;
    }

    public static int getRenewPeriod(){
        return renewPeriod;
    }


    public int getLoanPeriod(){
        return loanPeriod;
    }

    @Override
    public String getCreator(){
        return this.author;
    }

    @Override
    public void loanItem(String userID, String barcode) {
        super.loanItem(userID, barcode);
        super.setLoanPeriod(loanPeriod);
    }

    @Override
    public void returnItem(String barcode){
        super.returnItem(barcode);
    }

}
