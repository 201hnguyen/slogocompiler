package slogo.backend.utils;

public class TurtleMovement {

    private int turtleID;
    private Movement movement;
    private DrawStatus drawStatus;

    public TurtleMovement(int turtleID, Movement movement, DrawStatus drawStatus) {
        this.turtleID = turtleID;
        this.movement = movement;
        this.drawStatus = drawStatus;
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
}
