import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import entities.Library;

public class LibraryTest {
    @Test
    void testLoanItem() {
        Library library = new Library("src/main/resources/ITEMS.csv", "src/main/resources/USERS.csv");
        assertTrue(library.loanItem("25832497", "B00447489"));
    }
}
