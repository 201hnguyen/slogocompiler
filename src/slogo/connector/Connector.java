package slogo.connector;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.backend.external_api.BackendManager;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.Visualization;

public class Connector {
    private Visualization myVisualization;
    private BackendManager myBackEndManager;
    private boolean moveStarted = false;

    public Connector(Stage stage) {
        myVisualization = new Visualization(stage);
        myBackEndManager = new BackendManager("English", new TurtleHistory());
    }

    public void begin() {
        var frame = new KeyFrame(Duration.millis(2), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
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
