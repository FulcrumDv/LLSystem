import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import entities.Library;

public class LibraryTest {
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

    void testCheckUserExists(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.checkUserExists("B00447489"));
    }

    @Test
    void testSearchforItem(){
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertEquals("25832497", library.searchForItem("25832497").getBarcode());
    }


}
