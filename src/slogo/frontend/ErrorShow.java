package slogo.frontend;

import javafx.scene.control.Alert;

/**
 * Implementation of NewScreen. Called when the user input is not valid,
 * and creates a new Alert window that informs the user what the error is
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

    /**
     * Shows user the error
     */
    @Override
    public void show() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }
}
