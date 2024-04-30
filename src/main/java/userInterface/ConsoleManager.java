package userInterface;

class ConsoleManager {
    public static void clearConsole(){
        System.out.println("\r\n".repeat(50));
    }

    // ASCII art that displays "Library Loan System"
    public static void displayAsciiArt(){
        System.out.println("""


                    ╔─────────────────────────────────────────────────────────────────────────────────╗
                    │ _    _ _                        _                     ___         _             │
                    │| |  (_) |__ _ _ __ _ _ _ _  _  | |   ___  __ _ _ _   / __|_  _ __| |_ ___ _ __  │
                    │| |__| | '_ \\ '_/ _` | '_| || | | |__/ _ \\/ _` | ' \\  \\__ \\ || (_-<  _/ -_) '  \\ │
                    │|____|_|_.__/_| \\__,_|_|  \\_, | |____\\___/\\__,_|_||_| |___/\\_, /__/\\__\\___|_|_|_|│
                    │                          |__/                             |__/                  │
                    ╚─────────────────────────────────────────────────────────────────────────────────╝

                    """);

    }

    // Display's the menu options for the librarian to select
    public static void displaySelectionMenu(){
        System.out.println("Welcome! Please select one of the following options below by entering the corresponding number:");
        System.out.println("1. Issue a loan");
        System.out.println("2. Return a loan");
        System.out.println("3. Renew a loan");
        System.out.println("4. Look up a item");
        System.out.println("5. Display all loans");
        System.out.println("6. Display loans on a user");
        System.out.println("7. Display all users");
        System.out.println("8. Save and Quit");
        System.out.print("> ");
    }
}
