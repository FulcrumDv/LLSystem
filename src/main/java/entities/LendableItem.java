package entities;

// This interface declares functionality for lendable items (books, multimedia) only
public interface LendableItem {
   void returnItem(String barcode);
   boolean isLendable(String barcode);

   // May change to list
   String getLendables();


}
