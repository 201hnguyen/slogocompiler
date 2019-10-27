package slogo.frontend;

import slogo.frontend.reference.ReferenceWindow;

public class ButtonController {
    private boolean startButtonClicked = false;
    private boolean clearButtonClicked = false;

    public ButtonController() {
        /**
         * TODO: Constructor will have to get language as input parameter.
         */
    }

    public void startButtonAction() {
        startButtonClicked = true;
    }

    public void clearButtonAction() {
        clearButtonClicked = true;
    }

    public void helpButtonAction() {
        ReferenceWindow referenceWindow = new ReferenceWindow("English");
        referenceWindow.createNewWindow();
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
}
