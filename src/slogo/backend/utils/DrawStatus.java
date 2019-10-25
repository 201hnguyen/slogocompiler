package slogo.backend.utils;

public class DrawStatus {
    private boolean visible;
    private boolean penDown;

    public DrawStatus(boolean visible, boolean penDown) {
        this.visible = visible;
        this.penDown = penDown;
    }

    public boolean isTurtleVisible() {
        return visible;
    }

    public boolean isPenDown() {
        return penDown;
    }
}
