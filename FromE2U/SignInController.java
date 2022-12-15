package FromE2U; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Zach Goldstein. 
 * Controller for the Sign-In view
 */
public class SignInController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Scene signUpScene;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onSignInButtonClick(javafx.event.ActionEvent event) 
    {
        welcomeText.setText("Welcome to JavaFX Application!");
        Account account;

        try {
            account = Account.signIn(usernameField.getText(), passwordField.getText());
            System.out.println("Successfully signed in with username: " + account);
            welcomeText.setText("Successfully signed in with username: " + account.getUsername());
            if (account.getIsCustomer())
            {
                root = FXMLLoader.load(getClass().getResource("Store.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root); 
                stage.setScene(scene);
                stage.show();                  
            }
            else
            {
                root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root); 
                stage.setScene(scene);
                stage.show();                
            }
        } catch(ClassNotFoundException e) {
            System.out.println("That account does not exist. Try signing up.");
            welcomeText.setText("That account does not exist. Try signing up.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSwitchToSignUpButtonClick(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(signUpScene);
        primaryStage.show();
    }

    public void setSignUpScene(Scene scene) {
        signUpScene = scene;
    }
}