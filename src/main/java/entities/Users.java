package entities;

public class Users {
    private String userID;
    private String name;
    private String email;

    public Users(String userID, String name, String email) {
        this.userID = userID;
        this.name = name;
        this.email = email;
    }

    // Getters

    public String getUserID(){
        return this.userID;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }
}
