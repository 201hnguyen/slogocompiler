package slogo.frontend;

import slogo.frontend.reference.ReferenceWindow;

public class ButtonController {
    private boolean startButtonClicked = false;
    private boolean clearButtonClicked = false;
    private boolean newButtonClicked = false;
    private String animationStatus;
    private String language;

    public ButtonController(String language) {
        this.language = language;
    }

    public void startButtonAction() {
        startButtonClicked = true;
    }

    public void clearButtonAction() {
        clearButtonClicked = true;
    }

    public void helpButtonAction() {
        ReferenceWindow referenceWindow = new ReferenceWindow(language);
        referenceWindow.createNewWindow();
    }

    public void playButtonAction() {

    }

    public void stopButtonAction() {

    }

    public void stepButtonAction() {

    }

    public void newButtonAction() {
        newButtonClicked = true;
    }

    public boolean isStartButtonClicked() {
        boolean returnValue = startButtonClicked;
        startButtonClicked = false;
        return returnValue;
    }

    public boolean isClearButtonClicked() {
        boolean returnValue = clearButtonClicked;
        clearButtonClicked = false;
        return returnValue;
    }

    public boolean isNewButtonClicked() {
        boolean returnValue = newButtonClicked;
        newButtonClicked = false;
        return returnValue;
    }

    public String getAnimationStatus() {
        return animationStatus;
    }
}
