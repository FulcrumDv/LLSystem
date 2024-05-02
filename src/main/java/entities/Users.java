package entities;
// This class holds the structure for a user object in the library

public class Users {
    private final String userID;
    private final String firstName;
    private final String lastName;
    private final String email;

    public Users(String userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters

    public String getUserID() {
        return this.userID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }
}
