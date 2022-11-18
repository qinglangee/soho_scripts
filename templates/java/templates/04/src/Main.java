

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/login.fxml"));
        Pane pane = loader.<Pane>load();

        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);

        // ScreenController screen = ScreenController.getInstance();
        // screen.setScene(scene);
        
        stage.show();
    }

    
    public static void main(String[] args) {
        Application.launch(args);
        
    }
}