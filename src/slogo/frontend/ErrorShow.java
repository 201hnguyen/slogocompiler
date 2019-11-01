package slogo.frontend;

import javafx.scene.control.Alert;

/**
 * The class is used to help display errors in the front end by using alert.
 */

public class ErrorShow implements NewScreen{
    private Throwable ex;
    private String message;

    public ErrorShow(Throwable ex, String message){
        this.ex = ex;
        this.message = message;
    }

    public ErrorShow(String message) {
        this.message = message;
    }

    public void show() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
}
