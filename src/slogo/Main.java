package slogo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.application.Application;
import slogo.backend.external_api.BackendManager;
import slogo.backend.parser.ParserForTest;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.Visualization;


public class Main extends Application {

    private Animation myAnimation;
    private BackendManager myBackEndManager;
    private boolean moveStarted = false;
    private Visualization visualization;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        visualization = new Visualization(stage);
        visualization.helpHost = getHostServices();
//        TurtleManager turtleManager = visualization.getTurtle();
        myBackEndManager = new BackendManager("English", new TurtleHistory());
//        myBackEndManager.setCommand("fd 50");
        var frame = new KeyFrame(Duration.millis(2), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step() {
        String str = visualization.getInput();
        if(!str.equals("")) {
            System.out.println("Entered main.step"); //testing
            System.out.println(str); //testing
            myBackEndManager.setCommand();
            visualization.setHistory(myBackEndManager.getHistory());
            moveStarted = true;
        }
        if(moveStarted) {
            visualization.update();
        }
    }
}