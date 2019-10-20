package slogo.backend.utils;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private static final String TURTLE_IMAGE_PATH = "../../../resources/image/turtle.jpg";

    private List<Turtle> myTurtles = new ArrayList<>();
    private Pane myTurtlePane;

    public TurtleManager(Pane myPane) {

        myTurtlePane = myPane;
    }

    public Turtle getTurtle(String turtleID){
        for(Turtle turtle : myTurtles) {
            if(turtle.getMyID().equals(turtleID)) {
                return turtle;
            }
        }
        Turtle newTurtle = new Turtle(new Image(TURTLE_IMAGE_PATH, 10, 10, true, true), turtleID);
        myTurtles.add(newTurtle);
        myTurtlePane.getChildren().add(newTurtle);
        return newTurtle;
        //return null; there will be an exception later.
        //return myTurtles.get(0); // for testing.
    }

    public void updateTurtle(String turtleID, Movement movement, DrawStatus drawStatus) {
        Turtle turtle = getTurtle(turtleID);
        /** TODO: draw a line and add to the turtlePane
         */
    }

    public List<Turtle> getAllTurtles() {
        List<Turtle> list = new ArrayList<>();
        list.addAll(myTurtles);
        return list;
    }

    public void addTurtle(String turtleID) {
        Turtle removeTurtle = getTurtle(turtleID);
        myTurtles.remove(removeTurtle);
        myTurtlePane.getChildren().remove(removeTurtle);
        /**
         * TODO: add new turtle to myTurtlePane and myTurtles
         */
    }

    public void initialize() {

    }
}
