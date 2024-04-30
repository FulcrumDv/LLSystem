package entities;

// This interface declares functionality for lendable items (books, multimedia) only
public interface LendableItem {
   void returnItem(String barcode);
   void loanItem(String userID, String barcode);
   boolean isLendable(String barcode);
   void notLendable(String userID, String barcode);
}
