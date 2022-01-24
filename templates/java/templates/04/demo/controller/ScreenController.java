package controller;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ScreenController {
    protected HashMap<String, Pane> screenMap = new HashMap<>();
    protected Scene main;

    private static ScreenController instance;


    public static ScreenController getInstance(){
        if(instance == null){
            instance = new ScreenController();
        }
        return instance;
    }

    public void setScene(Scene scene){
        this.main = scene;
    }

    public Scene getScene(){
        return main;
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
            URL url = getClass().getResource("css/menu.css");
            if(url != null){
                pane.getStylesheets().add(url.toExternalForm());
            }else{
                System.err.println("ERROR: css is not exist");
            }
            addScreen(path, pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage(){
        return (Stage)main.getWindow();
    }
}