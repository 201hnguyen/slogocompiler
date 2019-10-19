package slogo;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.*;
import slogo.frontend.Visualization;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Visualization visualization= new Visualization();
        stage.setResizable(false);
        stage.setTitle("SLOGO");
        stage.setScene(visualization.getScene());
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}