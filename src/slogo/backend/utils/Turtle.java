package slogo.backend.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Turtle extends ImageView {
    private boolean showing;
    private boolean penDown;
    private double xPos = 0;
    private double yPos = 0;
    private double orientation = 90;
    private String myID;

    public Turtle(Image image, String myID) {
        super(image);
        this.myID = myID;
        xPos = 0;
        yPos = 0;
        orientation = 90;
        showing = true;
        penDown = true;
    }


    public boolean isPenDown() {
        return penDown;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
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

    protected void update(Movement movement) {
        /**TODO: Update the turtle according to the movement object
         */
    }
}
