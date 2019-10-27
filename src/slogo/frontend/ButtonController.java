package slogo.frontend;

import slogo.frontend.reference.ReferenceWindow;
import slogo.frontend.reference.TableWindow;

public class ButtonController {
    private boolean startButtonClicked = false;
    private boolean clearButtonClicked = false;

    public ButtonController() {

    }

    public void startButtonAction() {
        startButtonClicked = true;
    }

    public void clearButtonAction() {
        clearButtonClicked = true;
    }

    public void helpButtonAction() {
        ReferenceWindow referenceWindow = new ReferenceWindow();
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
