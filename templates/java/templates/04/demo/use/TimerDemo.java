
package use;

import controller.ScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerDemo  extends Application{
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        VBox vb = new VBox(root);
        Scene scene = new Scene(vb, 1000, 600);
        stage.setScene(scene);

        ScreenController screen = ScreenController.getInstance();
        screen.setScene(scene);

        Timeline clockLine = new Timeline();
        clockLine.setCycleCount(Timeline.INDEFINITE);
        KeyFrame timeFrame = new KeyFrame(Duration.seconds(1), e->{
            System.out.println("timer tick");;
        });
        clockLine.getKeyFrames().add(timeFrame);
        clockLine.play();

        
        stage.show();
    }

    public void candleClick(){
        System.out.println("candle click..");
    }
}
