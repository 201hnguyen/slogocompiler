package slogo.backend.utils;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TurtleManager {

    private static final String TURTLE_IMAGE_PATH = "resources/image/turtle.jpg";

    private List<Turtle> myTurtles = new ArrayList<>();
    private Pane myTurtlePane;
    private Image myTurtleImage;
    private double centralX;
    private double centralY;

    public TurtleManager(Pane myPane, Image image) {

        myTurtlePane = myPane;
        myTurtleImage = image;
        Turtle turtle = new Turtle(image, "Turtle1");
        myTurtles.add(turtle);
        myTurtlePane.getChildren().add(turtle);
        centralX = myTurtlePane.getWidth() / 2;
        centralY = myTurtlePane.getHeight() / 2;
        turtle.setX(centralX);
        turtle.setY(centralY);
    }

    public Turtle getTurtle(String turtleID){
        for(Turtle turtle : myTurtles) {
            if(turtle.getMyID().equals(turtleID)) {
                return turtle;
            }
        }
        if(myTurtles.isEmpty()) {
            Turtle newTurtle = new Turtle(new Image(new File(TURTLE_IMAGE_PATH).toURI().toString(),
                    10, 10, true, true), turtleID);
            myTurtles.add(newTurtle);
            myTurtlePane.getChildren().add(newTurtle);
            newTurtle.setVisible(true);
            return newTurtle;
        }
        return myTurtles.get(0);
        /** TODO: Fix this code when there are multiple turtles
         */
    }

    public void updateTurtle(String turtleID, Movement movement, DrawStatus drawStatus) {
        Turtle turtle = getTurtle(turtleID);
        double initialDegree = turtle.getOrientation();
        double initialXPos = turtle.getX();
        double initialYPos = turtle.getY();
        turtle.update(movement, drawStatus);
        System.out.println(turtle.getXPos() + ", " + turtle.getYPos() + ", " + turtle.getOrientation());

        turtle.setX(turtle.getXPos() + centralX);
        turtle.setY(centralY - turtle.getYPos());
        turtle.setRotate(turtle.getRotate() - initialDegree + turtle.getOrientation());

        Line line = new Line(initialXPos, initialYPos, turtle.getX(), turtle.getY());
        if(turtle.isPenDown()) {
            myTurtlePane.getChildren().add(line);
        }
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

    public void setImage() {
    }
}
