package FromE2U;
import java.io.IOException;
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
 * @author Artur Nascimento.
 */
public class InventoryController implements Initializable 
{   
    @FXML
    private ListView<Product> listInventory;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputDesc;
    @FXML
    private TextField inputPId;
    @FXML
    private TextField inputQuantity;
    @FXML
    private TextField inputInvoice;
    @FXML
    private TextField inputPrice;
    @FXML
    private TextField upName;
    @FXML
    private TextField upDesc;
    @FXML
    private TextField upPId;
    @FXML
    private TextField upPrice;
    @FXML
    private TextField upInvoice;
    @FXML
    private TextField upQuantity;
    
    private final Inventory x = new Inventory();
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { }    
    
    /**
     * Method used to add products into the inventory list. 
     * @param event When add product button is clicked, run the code below. 
     */
    @FXML
    private void addItem(MouseEvent event) 
    {
        Product p = new Product(inputName.getText(), inputDesc.getText(), inputPId.getText(), 
            Double.parseDouble(inputPrice.getText()), Double.parseDouble(inputInvoice.getText()),
            Integer.parseInt(inputQuantity.getText()));
        x.addProduct(p); 
        listInventory.getItems().add(p);
    }
    /**
     * Method used to retrieve an inventory item with a given id and update it as required. 
     * @param event when update product button is clicked, run the code below.  
     */
    @FXML
    private void updateItem(MouseEvent event) 
    {
        Product update = new Product(upName.getText(), upDesc.getText(), upPId.getText(), 
            Double.parseDouble(upPrice.getText()), Double.parseDouble(upInvoice.getText()),
            Integer.parseInt(upQuantity.getText()));
        x.updateProduct(update);
    }
    /**
     * Method used to retrieve the information from the inventory into the screen. 
     * @param event when load inventory button is clicked, run the code below. 
     */
    @FXML
    void loadItem(MouseEvent event) 
    {
        listInventory.getItems().clear();
        x.readFile();
        ArrayList<Product> v = x.getInventory();
        
        for (int i = 0; i < v.size(); i++)
        {
            listInventory.getItems().add(v.get(i));
        }
    }
    /**
     * Once the analytic button is clicked switch to the analytic in page. 
     * @param event perform switch action. 
     * @throws IOException 
     */
    public void switchToAnalytics(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Analytics.fxml"));
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

