package slogo;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.paint.Color;
import javafx.stage.*;
import slogo.frontend.Visualization;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Visualization visualization = new Visualization(stage);
        visualization.helpHost = getHostServices();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
