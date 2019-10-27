package slogo.connector;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.backend.external_api.BackendManager;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.Visualization;

public class Connector {

    private static final double DURATION_MILLIS = 2;

    private Visualization myVisualization;
    private BackendManager myBackEndManager;
    private boolean moveStarted = false;
    private Timeline myAnimation;

    public Connector(Stage stage) {
        myVisualization = new Visualization(stage);
        myBackEndManager = new BackendManager("English", new TurtleHistory());
    }

    public void begin() {
        var frame = new KeyFrame(Duration.millis(DURATION_MILLIS), e -> step());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private void step() {
        String str = myVisualization.getInput();
        if(!str.equals("")) {
            System.out.println(str);
            myBackEndManager.setCommand(str);
            myVisualization.setHistory(myBackEndManager.getHistory());
            moveStarted = true;
        }
        if(moveStarted) {
            myVisualization.update();
        }
    }
}
