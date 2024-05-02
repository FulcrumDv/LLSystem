import entities.Users;
import org.junit.jupiter.api.Test;
import tools.ReadCSV;
import tools.WriteCSV;
import entities.LibraryItems;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import entities.Loans;
import java.util.ArrayList;


public class FileHandlingTest {

    // ReadCSV tests

    @Test
    void testReadItemsCSV(){
        ReadCSV readCSV = new ReadCSV();
        List<LibraryItems> items = readCSV.readItems("src/main/resources/ITEMS.csv");
        assertNotNull(items);
        /* show that items are being read from the first to last barcode
        First barcode is 25832497 */
        assertEquals("25832497", items.getFirst().getBarcode());

        // Last barcode is 577774437
        assertEquals("577774437", items.getLast().getBarcode());
    }

    @Test
    void testReadUsersCSV(){
        ReadCSV readCSV = new ReadCSV();
        List<Users> users = readCSV.readUsers("src/main/resources/USERS.csv");
        assertNotNull(users);
        /* show that users are being read from the first to last barcode
        First userID is "B00447489" */
        assertEquals("B00447489", users.getFirst().getUserID());

        // Last UserID is "B00909463"
        assertEquals("B00909463", users.getLast().getUserID());
    }

    @Test
    void testReadLoans(){
        WriteCSV writeCSV = new WriteCSV();
        List<Loans> loansToWrite = new ArrayList<>();
        loansToWrite.add(new Loans("602130529", "Suzann Burgoin", "purus sit", "Book", LocalDate.now(), LocalDate.now().plusDays(30)));
        writeCSV.writeLoans("src/test/ReadingLoansTest.csv", loansToWrite);

        ReadCSV readCSV = new ReadCSV();
        List<Loans> loans = readCSV.readLoans("src/test/ReadingLoansTest.csv");
        // Check that there are things actually being read from the file
        assertNotNull(loans);

        assertEquals("602130529", loans.getFirst().getBarcode());
        assertEquals("Suzann Burgoin", loans.getFirst().getUserID());
        assertEquals("purus sit", loans.getFirst().getTitle());
        assertEquals("Book", loans.getFirst().getMediaType());
        assertEquals(LocalDate.now(), loans.getFirst().getLoanDate());
        assertEquals(LocalDate.now().plusDays(30), loans.getFirst().getDueDate());

    }

    // WriteCSV tests

    @Test
    void testWriteLoansCSV(){
        WriteCSV writeCSV = new WriteCSV();
        // Create a list of loans and add a loan to it to test
        List<Loans> loansToWrite = new ArrayList<>();
        loansToWrite.add(new Loans("602130529", "Suzann Burgoin", "purus sit", "Book", LocalDate.now(), LocalDate.now().plusDays(30)));

        // Write the loans to the file
        writeCSV.writeLoans("src/test/WritingLoansTest.csv", loansToWrite);

        // Read the loans from the file into a list
        ReadCSV readCSV = new ReadCSV();
        List<Loans> loansRead = readCSV.readLoans("src/test/WritingLoansTest.csv");

        // loop through bot of the lists and compare if they match
        for (int i = 0; i < loansToWrite.size(); i++){
            assertEquals(loansToWrite.get(i).getBarcode(), loansRead.get(i).getBarcode());
            assertEquals(loansToWrite.get(i).getUserID(), loansRead.get(i).getUserID());
            assertEquals(loansToWrite.get(i).getTitle(), loansRead.get(i).getTitle());
            assertEquals(loansToWrite.get(i).getMediaType(), loansRead.get(i).getMediaType());
            assertEquals(loansToWrite.get(i).getLoanDate(), loansRead.get(i).getLoanDate());
            assertEquals(loansToWrite.get(i).getDueDate(), loansRead.get(i).getDueDate());
        }

    }
}
