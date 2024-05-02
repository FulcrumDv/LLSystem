package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import entities.Loans;


public class WriteCSV {

    Logger logger = Logger.getLogger(WriteCSV.class.getName());

    public void writeLoans(String filepath, List<Loans> loans) {
        try (FileWriter outputFile = new FileWriter(filepath)) {
            outputFile.append("Barcode,UserID,Title,MediaType,LoanDate,DueDate,NumberOfRenews\n");
            for (Loans loan : loans) {
                outputFile.append(loan.getBarcode());
                outputFile.append(",");
                outputFile.append(loan.getUserID());
                outputFile.append(",");
                outputFile.append(loan.getTitle());
                outputFile.append(",");
                outputFile.append(loan.getMediaType());
                outputFile.append(",");
                outputFile.append(String.valueOf(loan.getLoanDate()));
                outputFile.append(",");
                outputFile.append(String.valueOf(loan.getDueDate()));
                outputFile.append(",");
                outputFile.append(String.valueOf(loan.getNumberOfRenews()));
                outputFile.append("\n");
            }
        } catch (IOException e) {
            logger.warning("Error writing to file: " + e);
        }
    }
}

