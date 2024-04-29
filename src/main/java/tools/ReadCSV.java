package tools;
import entities.LibraryItems;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import entities.Users;

// Tasks
/*
 * Read in csv file with error and exception handling
 * Use library items (books and multimeda) to determine items.csv contents
 * Use users to give store individual users
 *
 */
public class ReadCSV {

    Logger logger = Logger.getLogger(ReadCSV.class.getName());

    public static final String delimiter = ",";
    // Reads in the specified file and returns a list of LibraryItems
    public List<LibraryItems> readItems(String filepath){
        List<LibraryItems> items = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] tempArray = line.split(delimiter);
                // Checking the media type to see if its book or multimedia
                switch(tempArray[3]){
                    case "Book":
                        items.add(new entities.Books(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5]));
                        break;
                    case "Multimedia":
                        items.add(new entities.Multimedia(tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5]));
                        break;
                    default:
                        System.out.println(tempArray[4] + " is not a valid media type");
                        break;
                }
            }
        }catch (IOException e){
            logger.warning("Error reading file: " + e);
        }

        return items;
    }

    // Reads in the specified file and returns a list of Users
    public List<Users> readUsers(String filepath){
        List<Users> users = new ArrayList<>();
        try{
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] tempArray = line.split(delimiter);
                users.add(new entities.Users(tempArray[0], tempArray[1], tempArray[2], tempArray[3]));
            }
        }catch (IOException e){
            logger.warning("Error reading file: " + e);
        }

        return users;
    }




}
