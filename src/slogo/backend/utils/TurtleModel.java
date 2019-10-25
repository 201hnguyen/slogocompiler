package slogo.backend.utils;

public class TurtleModel {
    private static final double INITIAL_ORIENTATION = 90d;
    private static final double INITIAL_DISPLACEMENT = 0d;

    private double xPos;
    private double yPos;
    private double orientation;
    private int myID;
    private DrawStatus drawStatus;
    private PenStatus penStatus;


    public TurtleModel(int myID, PenStatus penStatus, DrawStatus drawStatus) {
        this.myID = myID;
        xPos = INITIAL_DISPLACEMENT;
        yPos = INITIAL_DISPLACEMENT;
        orientation = INITIAL_ORIENTATION;
        this.penStatus = penStatus;
        this.drawStatus = drawStatus;
    }

    public int getMyID() {
        return myID;
    }

    protected void update(Movement movement, DrawStatus drawStatus, PenStatus penStatus) {
        /**TODO: Update the turtle according to the movement object
         */
        xPos = movement.getEndPosition().getX();
        yPos = movement.getEndPosition().getY();
        orientation = movement.getOrientation();
        this.drawStatus = drawStatus;
        this.penStatus = penStatus;
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

    public DrawStatus getDrawStatus() {
        return drawStatus;
    }

    public PenStatus getPenStatus() {
        return penStatus;
    }
}
