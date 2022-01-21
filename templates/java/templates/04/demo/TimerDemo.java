import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimerDemo  extends Application {
    

    TextField speed;
    
    public static void main(String[] args) {
        Application.launch(args);
        
    }

    @Override
    public void start(Stage stage) throws Exception {
        speed = new TextField();
        VBox box = new VBox();
        Label label = new Label("speed:");
        box.getChildren().addAll(label, speed);

        stage.setScene(new Scene(box));
        stage.show();

        Timer tm = new Timer();
        tm.schedule(new subtimer(), 300, 10);
        
    }

    
    // subclass that extends the TimerTask
    private class subtimer extends TimerTask {
        // run method
        @Override
        public void run() {
            // method
            Platform.runLater(() -> {
                
                speed.setText(System.currentTimeMillis() + "");
            });
        }
    }
}
