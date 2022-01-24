package use;

import controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BorderPaneDemo extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/border_pane_demo.fxml"));
        Pane pane = loader.<Pane>load();

        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);

        ScreenController screen = ScreenController.getInstance();
        screen.setScene(scene);
        
        stage.show();
    }

    public void candleClick(){
        System.out.println("candle click..");
    }
}
