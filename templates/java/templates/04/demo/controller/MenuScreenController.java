package controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Use BorderPane as root of scene, put menu at top of BorderPane
 */
public class MenuScreenController extends ScreenController{
    private static MenuScreenController instance;
    BorderPane borderPane;

    public static MenuScreenController getInstance(){
        if(instance == null){
            instance = new MenuScreenController();
        }
        return (MenuScreenController)instance;
    }

    public void setScene(Scene scene){
        this.main = scene;
        borderPane = (BorderPane)main.getRoot(); // root of Scene must be BorderPane
    }

    /**
     * use BorderPane's center to show pane
     */
    public void activate(String name){
        if(screenMap.get(name) == null){
            loadPane(name);
        }
        if(screenMap.get(name) == null){
            System.out.println("Load and null:" + name);
            return;
        }
        borderPane.setCenter(screenMap.get(name));
    }
}
