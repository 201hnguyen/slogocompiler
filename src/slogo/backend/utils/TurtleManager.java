package slogo.backend.utils;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private static double TURTLE_RELATIVE_HEIGHT = 0.025;

    private List<Turtle> myTurtles = new ArrayList<>();
    private List<Line> myLines = new ArrayList<>();
    private Pane myTurtlePane;
    private Image myTurtleImage;
    private double centralX;
    private double centralY;
    private double turtleWidth;
    private double turtleHeight;

    public TurtleManager(Pane myPane, Image image) {
        myTurtlePane = myPane;
        myTurtleImage = image;
        centralX = myTurtlePane.getWidth() / 2;
        centralY = myTurtlePane.getHeight() / 2;
        addTurtle("Turtle1");
    }

    public Turtle getTurtle(String turtleID){
        for(Turtle turtle : myTurtles) {
            if(turtle.getMyID().equals(turtleID)) {
                return turtle;
            }
        }
        /*if(myTurtles.isEmpty()) {
            addTurtle(turtleID);
        }
        return getTurtle(turtleID);*/
        System.out.println(hasTurtle("Turtle1"));
        return getTurtle("Turtle1");
        /** TODO: Fix this code when there are multiple turtles
         */
    }

    public void updateTurtle(String turtleID, Movement movement, DrawStatus drawStatus) {
        Turtle turtle = getTurtle(turtleID);
        double initialDegree = turtle.getOrientation();
        double initialXPos = turtle.getCentralX();
        double initialYPos = turtle.getCentralY();
        turtle.update(movement, drawStatus);
        System.out.println(turtle.getXPos() + ", " + turtle.getYPos() + ", " + turtle.getOrientation());

        turtle.setX(turtle.getXPos() + centralX - turtle.getBoundsInLocal().getWidth()/2);
        turtle.setY(centralY - turtle.getYPos() - turtle.getBoundsInLocal().getHeight()/2);
        turtle.setRotate(turtle.getRotate() + initialDegree - turtle.getOrientation());

        Line line = new Line(initialXPos, initialYPos, turtle.getCentralX(), turtle.getCentralY());
        if(turtle.isPenDown()) {
            myTurtlePane.getChildren().add(line);
        }
        turtle.setVisible(turtle.isShowing());
    }

    public List<Turtle> getAllTurtles() {
        List<Turtle> list = new ArrayList<>();
        list.addAll(myTurtles);
        return list;
    }

    public void addTurtle(String turtleID) {
//        Turtle removeTurtle = getTurtle(turtleID);
//        myTurtles.remove(removeTurtle);
//        myTurtlePane.getChildren().remove(removeTurtle);
        if(!hasTurtle(turtleID)) {
            Turtle turtle = new Turtle(myTurtleImage, "Turtle1");
            myTurtles.add(turtle);
            myTurtlePane.getChildren().add(turtle);
            turtle.setX(centralX - turtle.getBoundsInLocal().getWidth()/2);
            turtle.setY(centralY - turtle.getBoundsInLocal().getHeight()/2);
        }
    }

    public void initialize() {
        for(Turtle turtle : myTurtles) {
            turtle.setX(centralX - turtle.getBoundsInLocal().getWidth()/2);
            turtle.setY(centralY - turtle.getBoundsInLocal().getHeight()/2);
            turtle.setRotate(0);
        }
        clearAllLines();
    }

    public void setImage(Image image) {
        for(Turtle turtle : myTurtles) {
            turtle.setImage(image);
        }
        myTurtleImage = image;
    }

    public TurtleManager copyOfThis() {
        /**
         * TODO: for undoing.
         */
        return null;
    }

    private boolean hasTurtle(String turtleID) {
        for(Turtle turtle : myTurtles) {
            if(turtle.getMyID().equals(turtleID)) {
                return true;
            }
        }
        return false;
    }

    private void clearAllLines() {
        myTurtlePane.getChildren().removeAll(myLines);
        myLines.clear();
    }
}
