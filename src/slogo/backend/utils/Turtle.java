package slogo.backend.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends ImageView {

    private static final double INITIAL_ORIENTATION = 90d;
    private static final double INITIAL_DISPLACEMENT = 0d;

    private boolean showing;
    private boolean penDown;
    private double xPos;
    private double yPos;
    private double orientation;
    private String myID;

    public Turtle(Image image, String myID) {
        super(image);
        this.myID = myID;
        xPos = INITIAL_DISPLACEMENT;
        yPos = INITIAL_DISPLACEMENT;
        orientation = INITIAL_ORIENTATION;
        showing = true;
        penDown = true;
    }


    public boolean isPenDown() {
        return penDown;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getOrientation() {
        return orientation;
    }

    public boolean isShowing() {
        return showing;
    }

    public String getMyID() {
        return myID;
    }

    protected void update(Movement movement, DrawStatus drawStatus) {
        /**TODO: Update the turtle according to the movement object
         */
        xPos = movement.getEndPosition().getX();
        yPos = movement.getEndPosition().getY();
        orientation = movement.getOrientation();
        showing = drawStatus.isTurtleVisible();
        penDown = drawStatus.isPenDown();
    }
}
