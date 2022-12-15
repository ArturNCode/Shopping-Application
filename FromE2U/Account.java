package FromE2U;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author Zach Goldstein. 
 * Implements the shared functionality between the Customer and Seller
 */
public abstract class Account implements Serializable {

    private String username;
    private String password;
    private Boolean isCustomer;
    static private transient String filePath = "accounts.json";

    public class AccountAlreadyExists extends Exception {}

    public Account(String username, String password, Boolean isCustomer) {
        this.username = username;
        this.password = password;
        this.isCustomer = isCustomer;
    }
    /**
     * Saves the current Account instance to the database
     * @throws IOException
     */    
    void upsert() throws IOException {
        // Deserialize the array of accounts from accounts.ser
        ArrayList<Account> accounts = deserializeAccounts();
        System.out.println("Accounts array: " + accounts.toString());
        // Initialize output stream for writing to accounts.ser
        FileOutputStream fileOut = new FileOutputStream("accounts.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        // Find current accounts from the accounts array and update it
        int index = accounts.indexOf(this);
        if(index != -1) {
            accounts.set(index, this);
        }
        else {
            accounts.add(this);
        }
        out.writeObject(accounts);
        out.close();
        fileOut.close();
    }
    /**
     * {@return the username of the account}
     */
    public String getUsername() {
        return username;
    }
    /**
     * {@return the password of the account}
     */
    public String getPassword() {
        return password;
    }
    /**
     * {@return if the account is a customer or not}
     */
    public Boolean getIsCustomer() {
        return isCustomer;
    }
     /**
     * Saves a new account to the database. If the account already exists then it 
     * does not save it to the database and
     * throws a AccountAlreadyExists exception.
     * @throws AccountAlreadyExists The account already exists in the database
     * @throws IOException
     */
    public void signUp() throws AccountAlreadyExists, IOException {
        try {
            signIn(username, password);
        } catch (IOException e) {
            throw new IOException();
        } catch (ClassNotFoundException e) {
            upsert();
            return;
        }
        throw new AccountAlreadyExists();
    }
    /**
     * @param username The username of the account to sign in to
     * @param password The password of the account to sign in to
     * @return The account with the matching username and password
     * @throws IOException
     * @throws ClassNotFoundException Could not find an account with the username and password
     */
    static public Account signIn(String username, String password) throws IOException, 
            ClassNotFoundException {
        ArrayList<Account> accounts = deserializeAccounts();
        // Get the first matching account from the ArrayList accounts
        // If no accounts are found it throws a ClassNotFoundException
        return accounts.stream()
                .filter(account1 -> account1.username.equals(username) && 
                        account1.password.equals(password))
                .findFirst()
                .orElseThrow(new Supplier<ClassNotFoundException>() {
                    @Override
                    public ClassNotFoundException get() {
                        return new ClassNotFoundException();
                    }
                });
    }
     /**
     * Pretty prints an Account object
     * @return The pretty printed object
     */
    public String toString() {
        return String.format("Username: %s, Password: %s, isCustomer: %b", getUsername(), 
                getPassword(), getIsCustomer());
    }
    /**
     * Compares two Account objects by the username.
     * @param other The other Account object to compare
     * @return True if both have the same usernames. Otherwise, False if the usernames 
     * do not match.
     */
    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Account)) return false;

        Account account = (Account) other;
        System.out.println("other username: " + this.username);
        return Objects.equals(account.getUsername(), this.getUsername());
    }
    /**
     * Deserializes the list of Account objects in the database
     * @return An ArrayList of the Account objects currently in the database
     * @throws IOException
     */
    private static ArrayList<Account> deserializeAccounts() throws IOException {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        ArrayList<Account> accounts = new ArrayList<Account>();

        try {
            fileIn = new FileInputStream("accounts.ser");
            in = new ObjectInputStream(fileIn);
            accounts = (ArrayList<Account>) in.readObject();
        } catch(FileNotFoundException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            if(in != null) in.close();
            if(fileIn != null) fileIn.close();
        }
        return accounts;
    }
}