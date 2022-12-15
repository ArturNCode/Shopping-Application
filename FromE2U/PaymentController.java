package FromE2U;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/** 
 * @author Artur Nascimento.
 */
public class PaymentController implements Initializable
{
    @FXML
    private ListView<String> totalDisplay;
    
    private double total = 0;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Method used to retrieve the total information to the user and display it. 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try
        {
            FileInputStream readData = new FileInputStream("profReveCosNew.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);
            
            double x = (Double) readStream.readObject();
            total = x; 

            readStream.close();
            readData.close();
        }
        catch(Exception e) { }
        
        totalDisplay.getItems().add("Total: " + total); 
    }    
    /**
     * Method used on click of the return button to call the switchToPayment method. 
     * @param event perform switch action. 
     */
    @FXML
    private void returnMain(MouseEvent event) { }
    /**
     * Method used to switch from the payment stage to the store. 
     * @param event when the button return is clicked, perform switch action. 
     * @throws IOException 
     */
    public void switchToPayment(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Store.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();         
    }
}
