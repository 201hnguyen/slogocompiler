package slogo.backend.utils;

public class TurtleMovement {

    private int turtleID;
    private Movement movement;
    private DrawStatus drawStatus;
    private PenStatus penStatus;

    public TurtleMovement(int turtleID, Movement movement, DrawStatus drawStatus, PenStatus penStatus) {
        this.turtleID = turtleID;
        this.movement = movement;
        this.drawStatus = drawStatus;
        this.penStatus = penStatus;
        System.out.println(penStatus.isPenDown() + ", " + penStatus.isPenDownChanged() + "movement");
    }

    public int getTurtleID() {
        return turtleID;
    }

    public Movement getMovement() {
        return movement;
    }

    public DrawStatus getDrawStatus() {
        return drawStatus;
    }

    public PenStatus getPenStatus() {
        return penStatus;
    }
}
