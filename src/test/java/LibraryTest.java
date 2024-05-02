import entities.Books;
import entities.Multimedia;
import org.junit.jupiter.api.Test;

import entities.Library;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    // Testing loanItem, renewItem, returnItem, renewLoanLimit, checkUserExists, searchForItem
    @Test
    void testLoanItem() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("25832497", "B00447489"));
        assertTrue(library.loanItem("240453126", "B00187440"));
        assertTrue(library.loanItem("813844363", "B00986808"));
        assertTrue(library.loanItem("530038220", "B00000464"));
    }

    @Test
    void testRenewItem() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("25832497", "B00447489"));
        if(library.searchForItem("25832497").isLendable("25832497")) {
            assertTrue(library.renewLoan("25832497"));
        }

        assertTrue(library.loanItem("240453126", "B00187440"));
        if(library.searchForItem("240453126").isLendable("240453126")) {
            assertTrue(library.renewLoan("240453126"));
        }

        assertTrue(library.loanItem("813844363", "B00986808"));
        if(library.searchForItem("813844363").isLendable("813844363")) {
            assertTrue(library.renewLoan("813844363"));
        }

        assertTrue(library.loanItem("530038220", "B00000464"));
        if(library.searchForItem("530038220").isLendable("530038220")) {
            assertTrue(library.renewLoan("530038220"));
        }
    }

    @Test
    void testReturnItem() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("25832497", "B00447489"));
        if(library.searchForItem("25832497").isLendable("25832497")) {
            assertTrue(library.returnLoan("25832497"));
        }

        assertTrue(library.loanItem("240453126", "B00187440"));
        if(library.searchForItem("240453126").isLendable("240453126")) {
            assertTrue(library.returnLoan("240453126"));
        }

        assertTrue(library.loanItem("813844363", "B00986808"));
        if(library.searchForItem("813844363").isLendable("813844363")) {
            assertTrue(library.returnLoan("813844363"));
        }

        assertTrue(library.loanItem("530038220", "B00000464"));
        if(library.searchForItem("530038220").isLendable("530038220")) {
            assertTrue(library.returnLoan("530038220"));
        }
    }

    @Test
    void testRenewLoanLimitOnBook(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("25832497", "B00447489"));
        for (int i = 0; i < Books.getRenewLimit(); i++){
            assertTrue(library.renewLoan("25832497"));
        }
        assertFalse(library.renewLoan("25832497"));
    }

    @Test
    void testRenewLoanLimitOnMultimedia(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("382471060", "B00187440"));
        for (int i = 0; i < Multimedia.getRenewLimit(); i++){
            assertTrue(library.renewLoan("382471060"));
        }
        assertFalse(library.renewLoan("382471060"));
    }

    @Test
    void testCheckUserExists(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.checkUserExists("B00447489"));
    }

    @Test
    void testSearchforItem(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertEquals("25832497", library.searchForItem("25832497").getBarcode());
    }

    // Testing invalid barcodes and user IDs
    @Test
    void testLoanItemWithInvalidBarcode() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertFalse(library.loanItem("000000", "B00447489"));
    }

    @Test
    void testRenewItemWithInvalidBarcode() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertFalse(library.renewLoan("000000"));
    }

    @Test
    void testReturnItemWithInvalidBarcode() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertFalse(library.returnLoan("00000000"));
    }
    @Test
    void testCheckUserExistsWithInvalidUserID(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertFalse(library.checkUserExists("912111111"));
    }

    @Test
    void testSearchForItemWithInvalidBarcode(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertNull(library.searchForItem("00000000"));
    }


}
