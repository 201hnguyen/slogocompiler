package slogo.frontend;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TurtleViewManager {
    private List<TurtleView> turtleViewList = new ArrayList<>();
    private Pane turtlePane;
    private Image image;

    public TurtleViewManager(Pane pane) {
        turtlePane = pane;
        setImage("START.png");
    }

    private void addTurtleView(int myID) {
        if(!hasTurtleView(myID)) {
            TurtleView turtleView = new TurtleView(image, myID, turtlePane.getWidth(), turtlePane.getHeight());
            turtleViewList.add(turtleView);
            turtlePane.getChildren().add(turtleView);
            turtleView.setSpeed(10);
        }
    }

    private boolean hasTurtleView(int myID) {
        for(TurtleView turtleView : turtleViewList) {
            if(turtleView.getMyID() == myID) {
                return true;
            }
        }
        return false;
    }

    public Image setImage(String fileName) {
        return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)));
        //return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("START.png")));
    }
}
