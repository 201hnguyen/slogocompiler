package slogo.backend.utils;

public class PenStatus {
    private boolean penDown;
    private int penSize;
    private int penColor;

    public PenStatus(boolean penDown, int penSize, int penColor) {
        this.penDown = penDown;
        this.penSize = penSize;
        this.penColor = penColor;
    }

    public int getPenSize() {
        return penSize;
    }

    public int getPenColor() {
        return penColor;
    }

    public boolean isPenDown() {
        return penDown;
    }
}
