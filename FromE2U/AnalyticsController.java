package FromE2U;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * 
 * @author Artur Nascimento.
 */
public class AnalyticsController 
{
    @FXML
    private ListView<Double> listAnalytics;
        
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private final Inventory x = new Inventory();
   
    /**
     * Once the inventory button is clicked switch to the inventory page. 
     * @param event perform switch action.
     * @throws IOException 
     */
    public void switchToInventory(javafx.event.ActionEvent event) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();        
    }
    /**
     * Method used to retrieve, cost, revenue and profit from the inventory. 
     * @param event load analytic button is clicked, run the code below.  
     */
    @FXML
    void loadAnalytics(MouseEvent event) 
    {
        listAnalytics.getItems().clear();
        x.readFile();
        x.calculateReport();
        listAnalytics.getItems().add(x.getCost());
        listAnalytics.getItems().add(x.getRevenue());
        listAnalytics.getItems().add(x.getProfit());
    } 
}
