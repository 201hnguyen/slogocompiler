package slogo.connector;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.backend.exceptions.BackendException;
import slogo.backend.external_api.BackendManager;
import slogo.backend.parser.ParserException;
import slogo.backend.utils.TurtleHistory;
import slogo.frontend.Visualization;

import java.util.HashMap;
import java.util.Map;

public class Connector {

    private static final double DURATION_MILLIS = 10;

    private Map<Stage, Visualization> visualizationMap;
    private Map<Stage, BackendManager> backendManagerMap;
    private Timeline myAnimation;
    private boolean newWindow = false;

    public Connector(Stage stage) {
        visualizationMap = new HashMap<>();
        backendManagerMap = new HashMap<>();
        createNewWorkSpace(stage);
    }

    public void begin() {
        var frame = new KeyFrame(Duration.millis(DURATION_MILLIS), e -> step());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    //
    private void step()  {
        for(Stage stage : visualizationMap.keySet()) {
            try {
                update(visualizationMap.get(stage), backendManagerMap.get(stage));
            } catch (Exception e) {
                visualizationMap.get(stage).showError(e, e.getMessage());
            }
            if(newWindow) {
                break;
            }
        }
        if(newWindow) {
            newWindow = false;
            createWorkSpace();
        }
    }

    //
    private void update(Visualization myVisualization, BackendManager myBackEndManager) throws BackendException, ParserException {
        String input = myVisualization.getInput();
        if (!input.equals("")) {
            System.out.println(input);
            myBackEndManager.setLanguage(input, myVisualization.getLanguage());
            try {
                myBackEndManager.setCommand(input);
            } catch (BackendException e) {
                //TODO: handle this exception by throwing it to the front-end
            }
            myVisualization.setHistory(myBackEndManager.getHistory());
        }
        myVisualization.update();
        if(myVisualization.needNewWindow()) {
            newWindow = true;
        }
    }

    private void createWorkSpace() {
        Stage stage = new Stage();
        createNewWorkSpace(stage);
    }

    private void createNewWorkSpace(Stage stage) {
        Visualization myVisualization = new Visualization(stage);
        BackendManager myBackEndManager = new BackendManager(myVisualization.getInput(), myVisualization.getLanguage(), new TurtleHistory());
        visualizationMap.put(stage, myVisualization);
        backendManagerMap.put(stage, myBackEndManager);
        stage.setOnCloseRequest(e -> {
            visualizationMap.remove(stage);
            backendManagerMap.remove(stage);
        });
    }
}
