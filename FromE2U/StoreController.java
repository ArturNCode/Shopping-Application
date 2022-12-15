package FromE2U;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * @author Artur Nascimento
 */
public class StoreController implements Initializable 
{
    @FXML
    private ListView<String> storeList;
    @FXML
    private ListView<String> description;
    @FXML
    private ListView<String> storeCart;    
    @FXML
    private TextField productSearch;
    @FXML
    private TextField cartQuantity;
    @FXML
    private TextField cartID;
    @FXML
    private TextField cartRemove;
    @FXML
    private TextField updateQuantity;
    @FXML
    private TextField currentID;

    private Inventory x = new Inventory();
    public ArrayList<Product> cart = new ArrayList<>();
    public ArrayList<Integer> quantity = new ArrayList<>(); 
    public double total = 0; 
    
    private Stage stage;
    private Scene scene;
    private Parent root;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) { }    

    /**
     * Method used to retrieve the stored inventory information file for display. 
     * @param event When load inventory button is clicked, run the code below. 
     */
    @FXML
    private void loadInventory(MouseEvent event) 
    {
        storeList.getItems().clear();
        x.readFile();
        ArrayList<Product> v = x.getInventory();
        
        for (int i = 0; i < v.size(); i++)
        {
           storeList.getItems().add("ID: " +  v.get(i).getProductID() + "  | Name: " + 
                   v.get(i).getName() + "  | Price: " + v.get(i).getPrice());
        }
    }
    /**
     * Method used to retrieve quantity and description of item being searched for. 
     * @param event when the search button is clicked, run the code below. 
     */
    @FXML
    private void searchInventory(MouseEvent event) 
    {
        description.getItems().clear();
        Product z = (Product) x.findProduct(productSearch.getText());
        description.getItems().add("Description: " + z.getDescription() + "  |  "
                + "Quantity: " + z.getQuantity()); 
    }
    /**
     * Method used to retrieve specific product and desired quantity and display in the cart.  
     * @param event when the add button is clicked, run the code below. 
     */
    @FXML
    void addToCart(MouseEvent event) 
    {
        Product a = (Product) x.findProduct(cartID.getText());
        if ( a.getQuantity() <  Integer.parseInt(cartQuantity.getText()) )
        {
            cart.add(a);
            quantity.add(a.getQuantity());
            storeCart.getItems().add("ID: " + a.getProductID() +  " | " + a.getName() 
                    + "  x  " +  a.getQuantity());
        }
        else
        {
            cart.add(a);
            quantity.add(Integer.parseInt(cartQuantity.getText()));
            storeCart.getItems().add("ID: " + a.getProductID() +  " | " + a.getName() 
                    + "  x  " + cartQuantity.getText());
        }
    }
    /**
     * Method used to delete an item from the cart and remove it from the cart 
     * list view. 
     * @param event when the remove button is clicked, run the code below. 
     */
    @FXML
    void removeItem(MouseEvent event) 
    {   
        Product b = (Product) x.findProduct(cartRemove.getText());
        for (int i = 0; i < cart.size(); i++)
        {
            if (b.getProductID().equals(cart.get(i).getProductID()))
            {
                cart.remove(i);
            }
        }        
        int selectedID = storeCart.getSelectionModel().getSelectedIndex();
        storeCart.getItems().remove(selectedID);
    }
    /**
     * Method used to get the specific item ID and replace the quantity with a 
     * brand new one. 
     * @param event when the update button is clicked, run the code below. 
     */
    @FXML
    void updateCart(MouseEvent event) 
    {
        int selectedID = storeCart.getSelectionModel().getSelectedIndex();
        storeCart.getItems().remove(selectedID);
        for (int i = 0; i < cart.size(); i++)
        {
            if (currentID.getText().equals(cart.get(i).getProductID()))
            {
                quantity.set(i, Integer.parseInt(updateQuantity.getText()));
                storeCart.getItems().add("ID: " + cart.get(i).getProductID() + 
                        " | " + cart.get(i).getName() + "  x  " + 
                quantity.get(i));
            }
        } 
    }
    /**
     * Method used to link the switchToPayment stage to the store. 
     * @param event when the pay button is clicked, perform switch action. 
     */
    @FXML
    void pay(MouseEvent event) { }
    /**
     * Calculate the total of the transaction, the difference between items and save 
     * it to the file. 
     * @param event when the calculate price button is clicked, run the code below. 
     */
    @FXML
    void calculatePrice(MouseEvent event) 
    {
        for (int i = 0; i < cart.size(); i++)
        {
            total += cart.get(i).getPrice() * quantity.get(i); 
        }
        storeCart.getItems().add("Total: " + String.valueOf(total));
                ArrayList<Product> b = x.getInventory();
        for(int i = 0; i < cart.size(); i++)
        {
            for(int l = 0; l < b.size(); l++)
            {
                if(b.get(l).getProductID().equals(cart.get(i).getProductID()))
                {
                    b.get(l).setQuantity(b.get(l).getQuantity() - quantity.get(i)); 
                }
            }
        }
        try
        {
            FileOutputStream writeData = new FileOutputStream("inventory.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            
            writeStream.writeObject(b);
            writeStream.flush();
            writeStream.close();
            writeData.close();
        }
        catch(IOException e) { }
        try
        {
            FileOutputStream writeData = new FileOutputStream("profReveCosNew.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            
            writeStream.writeObject(total);
            writeStream.flush();
            writeStream.close();
            writeData.close();
        }
        catch(IOException e) { }
    }
    /**
     * Once pay button is clicked switch to the payment stage. 
     * @param event perform switch action. 
     * @throws IOException 
     */
    public void switchToPayment(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();         
    }
    /**
     * Once the logout button is clicked switch to the sign in page. 
     * @param event perform switch action. 
     * @throws IOException 
     */
    public void switchToSignIn(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("sign-in-view.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();         
    }
}
