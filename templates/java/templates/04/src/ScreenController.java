import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    private static ScreenController instance;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public static ScreenController getInstance(){
        if(instance == null){
            instance = new ScreenController(null);
        }
        return instance;
    }

    public void setScene(Scene scene){
        this.main = scene;
    }

    protected void addScreen(String name, Pane pane){
         screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        if(screenMap.get(name) == null){
            loadPane(name);
            
        }
        if(screenMap.get(name) == null){
            System.out.println("Load and null:" + name);
            return;
        }
        main.setRoot(screenMap.get(name));
    }

    public void loadPane(String path){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Pane pane;
        try {
            pane = loader.<Pane>load();
            pane.getStylesheets().add(getClass().getResource("css/menu.css").toExternalForm());
            addScreen(path, pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage(){
        return (Stage)main.getWindow();
    }
}