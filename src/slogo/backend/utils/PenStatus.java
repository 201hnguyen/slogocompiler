package slogo.backend.utils;

public class PenStatus {
    private boolean penDown;
    private double penSize;
    private int penColor;

    private boolean penDownChanged;
    private boolean penSizeChanged;
    private boolean penColorChanged;

    public PenStatus(boolean penDown, double penSize, int penColor) {
        this.penDown = penDown;
        this.penSize = penSize;
        this.penColor = penColor;
        penDownChanged = false;
        penSizeChanged = false;
        penColorChanged = false;
    }

    public PenStatus(PenStatus penStatus) {
        this.penDown = penStatus.isPenDown();
        this.penSize = penStatus.getPenSize();
        this.penColor = penStatus.getPenColor();
        this.penDownChanged = penStatus.isPenDownChanged();
        this.penSizeChanged = penStatus.isPenSizeChanged();
        this.penColorChanged = penStatus.isPenColorChanged();
    }

    public double getPenSize() {
        return penSize;
    }

    public int getPenColor() {
        return penColor;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public boolean isPenDownChanged() {
        return penDownChanged;
    }

    public boolean isPenSizeChanged() {
        return penSizeChanged;
    }

    public boolean isPenColorChanged() {
        return penColorChanged;
    }

    public void setPenDown(boolean penDown) {this.penDown = penDown;}

    public void setPenColor(int penColor) {this.penColor = penColor;}

    public void setPenSize(double penSize) {this.penSize = penSize;}

    public void compareAndSetChanged(PenStatus other) {
        penDownChanged = !(other.isPenDown() == penDown);
        penSizeChanged = !(other.getPenSize() == penSize);
        penColorChanged = !(other.getPenColor() == penColor);
    }
}
