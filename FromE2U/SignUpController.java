package FromE2U;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * @author Zach Goldstein. 
 */
public class SignUpController {
    private Scene signInScene;

    @FXML
    private Label errorText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox isSellerCheckBox;

     /**
     * Signs up with the credentials.
     * If an account already exists with the username and password then it displays an error.
     */
    @FXML
    protected void onSignUpButtonClick() throws Account.AccountAlreadyExists, IOException {
        Account account;

        if (isSellerCheckBox.isSelected()) {
            account = new Seller(usernameField.getText(), passwordField.getText());
        }
        else {
            account = new Customer(usernameField.getText(), passwordField.getText());
        }

        try {
            account.signUp();
            errorText.setText("Successfully signed up");
            errorText.setTextFill(Color.GREEN);
        } catch (Account.AccountAlreadyExists e) {
            errorText.setText("That account already exists.");
        } catch (IOException e) {
            e.printStackTrace();
            errorText.setText("Could not load database.");
        }
    }
    /**
     * Switches to the sign-in view
     * @param actionEvent
     */
    @FXML
    protected void onSwitchToSignInButtonClick(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(signInScene);
        primaryStage.show();
    }
    /**
     * @param scene The sign-in scene that will be used to switch to
     */
    public void setSignInScene(Scene scene) {
        signInScene = scene;
    }
}
