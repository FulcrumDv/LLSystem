package entities;
import java.util.logging.Logger;

// Books is a subclass of LibraryItems and Inherits the properties of LibraryItems
public class Books extends LibraryItems{

    Logger logger = Logger.getLogger(Books.class.getName());
    private String author;

    // renewLimit is const, so will not change
    private static final int renewLimit = 3;

    public Books(String barcode, String author, String title, String year, String isbn){
        super(barcode, title, year, isbn);
        this.author = author;
    }

    // Getter
    public String getAuthor(){
        return this.author;
    }

    @Override
    public void loanItem(String userID, String barcode) {
        super.loanItem(userID, barcode);
        super.setLoanPeriod(30);
    }

    @Override
    public void returnItem(String barcode){
        super.returnItem(barcode);
    }

    public void incrementNumberOfRenews(Loans loan){
        try {if(loan.getNumberOfRenews() < renewLimit){
            loan.incrementNumberOfRenews();
        }else{
            System.out.println("Number of renews exceeded! Book MUST be returned!");
        }

        }catch (Exception e){
            logger.warning("Error with incrementing renews value: " + e);
        }
    }
}
