import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController{

    @FXML
    TextField account;
    @FXML
    PasswordField password;

    @FXML
    Button login;

    @FXML
    public void initialize(){
        
    }

    public void login() {
    }

    public void toRegister(){
        // ScreenController.getInstance().activate("fxml/register.fxml");
    }
}
