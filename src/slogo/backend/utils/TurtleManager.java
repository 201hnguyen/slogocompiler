package slogo.backend.utils;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {
    private List<Turtle> myTurtles = new ArrayList<>();
    private Pane myTurtlePane;

    public TurtleManager(Pane myPane) {

        myTurtlePane = myPane;
    }

    public Turtle getTurtle(String turtleID) throws NullPointerException{
        for(Turtle turtle : myTurtles) {
            if(turtle.getMyID().equals(turtleID)) {
                return turtle;
            }
        }
        myTurtles.add(new Turtle())
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
