package FromE2U;
/**
 * @author Zach Goldstein. 
 * Represents a Customer on FromE2U
 */
public class Customer extends Account {
    public Customer(String username, String password) {
        super(username, password, true);
    }
}
