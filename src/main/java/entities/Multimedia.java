package entities;
import java.util.logging.Logger;
// Multimedia is a subclass of LibraryItems and Inherits the properties of LibraryItems
public class Multimedia extends LibraryItems {
    Logger logger = Logger.getLogger(Multimedia.class.getName());
    private String artist;
    // renewLimit is const, so will not change
    private static final int renewLimit = 3;
    public Multimedia(String barcode, String artist, String title, String year, String isbn) {
        super(barcode, title, year, isbn);
        this.artist = artist;
    }

    // Getter
    public String getAuthor() {
        return this.artist;
    }

    // Implemention of overriden book specific methods
    @Override
    public void loanItem(String userID, String barcode) {
        super.loanItem(userID, barcode);
        super.setLoanPeroid(7);
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
