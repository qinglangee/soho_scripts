package gui;

import java.util.Optional;

import controller.ScreenController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;

public class DialogUtil {

    public static void center(Dialog alert){
        Platform.runLater(()->{

            Scene scene = ScreenController.getInstance().getScene();
            double x1 = scene.getWindow().getX();
            double y1 = scene.getWindow().getY();
            double x = x1 + scene.getWidth() / 2 - alert.getDialogPane().getWidth() / 2;
            double y = y1 + scene.getHeight() / 2 - alert.getDialogPane().getHeight() / 2;
            alert.setX(x);
            alert.setY(y);
        });
    }

    public static void showInfomation(String info){
        Dialog dialog = new Dialog<>();
        dialog.show();
    }
    
    public static void showInfo(String info){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(info);
        // String s =;
        // alert.setContentText(s);

        
        center(alert);
        alert.show();
    }
    public static void showErr(String info){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(info);
        // String s =;
        // alert.setContentText(s);
        center(alert);
        alert.show();
    }

    public static boolean confirm(String info){
        ButtonType foo = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.INFORMATION, info, foo, bar);

        Optional<ButtonType> result = alert.showAndWait();

        return result.orElse(bar) == foo;
    }

    
    public static String input(String prompt){
        // TextInputDialog inputdialog = new TextInputDialog(prompt);
        TextInputDialog inputdialog = new TextInputDialog();
        inputdialog.setContentText(prompt);
        inputdialog.setHeaderText("Input Dialog");
        inputdialog.showAndWait();
        center(inputdialog);
        return inputdialog.getEditor().getText();
    }

}
