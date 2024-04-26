package entities;
// This class holds the structure for a user object in the library

public class Users {
    private String userID;
    private String firstName;
    private String lastName;
    private String email;

    public Users(String userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName= firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters

    public String getUserID(){
        return this.userID;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }
}
