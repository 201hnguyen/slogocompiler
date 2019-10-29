package slogo.frontend;


import javafx.scene.control.ScrollPane;

public class ScrollMaker extends ScrollPane {
    private static final double WIDTH = 200;
    private static final double HEIGHT = 100;

    public ScrollMaker(String key) {
        setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        setPrefSize(WIDTH, HEIGHT);

    }

}
