package entities;
import java.util.logging.Logger;
// Multimedia is a subclass of LibraryItems and Inherits the properties of LibraryItems
public class Multimedia extends LibraryItems {
    Logger logger = Logger.getLogger(Multimedia.class.getName());

    private String artist;
    // renewLimit is const, so will not change
    private static final int renewLimit = 3;
    private static final int loanPeriod = 7;
    private static final int renewPeriod = 7;

    public Multimedia(String barcode, String artist, String title, String mediaType, String year, String isbn) {
        super(barcode, title, mediaType, year, isbn);
        this.artist = artist;
    }

    // Getter
    public String getAuthor() {
        return this.artist;
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


    // Implemention of overriden book specific methods
    @Override
    public void loanItem(String userID, String barcode) {
        super.loanItem(userID, barcode);
        super.setLoanPeriod(loanPeriod);
    }

    @Override
    public void returnItem(String barcode) {
        super.returnItem(barcode);
    }

    public void incrementNumberOfRenews(Loans loan) {
        try{
            if (loan.getNumberOfRenews() < renewLimit) {
                loan.incrementNumberOfRenews();
            } else {
                System.out.println("Number of renews exceeded! Book MUST be returned!");
            }
        }catch (Exception e){
           logger.warning("Error with incrementing number of renews: " + e);
        }

    }
}
