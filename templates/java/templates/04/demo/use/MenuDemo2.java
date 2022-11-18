package use;

import controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuDemo2  extends Application{
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TextArea area = new TextArea();
        MenuBar bar = new MenuBar();
      
        Menu menu1 = new Menu("Level");
        MenuItem easyLevel = new MenuItem("Easy");
        MenuItem normalLevel = new MenuItem("Normal");
        MenuItem hardLevel = new MenuItem("Hard");
        menu1.getItems().addAll(easyLevel, normalLevel, hardLevel);
        Menu menu2 = new Menu("Cheat");
        String[] colors = new String[]{ "red", "blue", "black", 
            "yellow", "purple", "orange", "green", "brown"
        };
        for(String color : colors){
            MenuItem item = new MenuItem(color);
            item.setOnAction(e->{
              area.setText(item.getText());
            });
            menu2.getItems().add(item);
        }
        bar.getMenus().addAll(menu1, menu2);

        Group root = new Group(area);
        VBox vb = new VBox(bar, root);
        Scene scene = new Scene(vb, 1000, 600);
        stage.setScene(scene);

        ScreenController screen = ScreenController.getInstance();
        screen.setScene(scene);
        
        stage.show();
    }

    public void candleClick(){
        System.out.println("candle click..");
    }
}
