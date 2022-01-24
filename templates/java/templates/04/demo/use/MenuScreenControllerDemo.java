package use;

import controller.MenuScreenController;
import controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuScreenControllerDemo extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/menu_screen_controller_demo.fxml"));
        Pane pane = loader.<Pane>load();

        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);

        MenuScreenController screen = MenuScreenController.getInstance();
        screen.setScene(scene);
        
        stage.show();
    }

    public void panel01(){
        controller.MenuScreenController.getInstance().activate("/fxml/menu_screen_controller_demo01.fxml");
    }
    
    public void panel02(){
        controller.MenuScreenController.getInstance().activate("/fxml/menu_screen_controller_demo02.fxml");
    }
}
