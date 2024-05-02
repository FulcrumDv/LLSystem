import entities.Users;
import org.junit.jupiter.api.Test;
import tools.ReadCSV;
import entities.LibraryItems;
import entities.Users;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileHandlingTest {

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
        First barcode is B00000001 */
        assertEquals("B00447489", users.getFirst().getUserID());

        // Last barcode is B00999999
        assertEquals("B00909463", users.getLast().getUserID());
    }


}
