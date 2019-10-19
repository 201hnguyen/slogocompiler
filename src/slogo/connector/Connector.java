package slogo.connector;

import javafx.animation.Animation;
//import slogo.backend.external_api.BackendManager;

public class Connector {
    //private BackendManager backendManager;
    private Animation myAnimation;

    public String myLanguage;
    public String myUserInput;



    public Connector() {
        myLanguage = getLanguage(); // from visualization.java
        myUserInput = getUserInput(); // from visualization.java

    }

    private void update() {

    }

    public String getLanguage() {
        return "English"; //for testing
    }

    public String getUserInput() {
        return "fd 50"; //for testing
    }
}
