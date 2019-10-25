package slogo.backend.utils;

public class PenStatus {
    private int penSize;
    private int penColor;

    public PenStatus(int penSize, int penColor) {
        this.penSize = penSize;
        this.penColor = penColor;
    }

    public int getPenSize() {
        return penSize;
    }

    public int getPenColor() {
        return penColor;
    }
}
