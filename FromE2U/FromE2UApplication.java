package FromE2U;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 * @author Zach Goldstein. 
 */
public class FromE2UApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader signInLoader = 
                new FXMLLoader(FromE2UApplication.class.getResource("sign-in-view.fxml"));
        Scene signInScene = new Scene(signInLoader.load(), 400, 400);
        FXMLLoader signUpLoader = 
                new FXMLLoader(FromE2UApplication.class.getResource("sign-up-view.fxml"));
        Scene signUpScene = new Scene(signUpLoader.load(), 400, 400);
        SignInController registrationController = 
                (SignInController) signInLoader.getController();
        registrationController.setSignUpScene(signUpScene);
        SignUpController signUpController = 
                (SignUpController) signUpLoader.getController();
        signUpController.setSignInScene(signInScene);
        stage.setTitle("FromE2U");
        stage.setScene(signInScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}