package slogo.backend.utils;

public class PenStatus {
    private boolean penDown;
    private int penSize;
    private int penColor;

    private boolean penDownChanged;
    private boolean penSizeChanged;
    private boolean penColorChanged;

    public PenStatus(boolean penDown, int penSize, int penColor) {
        this.penDown = penDown;
        this.penSize = penSize;
        this.penColor = penColor;
        penDownChanged = false;
        penSizeChanged = false;
        penColorChanged = false;
    }

    public PenStatus(PenStatus other) {
        penDown = other.isPenDown();
        penSize = other.getPenSize();
        penColor = other.getPenColor();
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

    public boolean isPenDownChanged() {
        return penDownChanged;
    }

    public boolean isPenSizeChanged() {
        return penSizeChanged;
    }

    public boolean isPenColorChanged() {
        return penColorChanged;
    }

    public void update(PenStatus other) {
        compareAndSetChanged(other);
        penDown = other.isPenDown();
        penColor = other.getPenColor();
        penSize = other.getPenSize();
    }

    private void compareAndSetChanged(PenStatus other) {
        penDownChanged = !(other.isPenDown() == penDown);
        penSizeChanged = !(other.getPenSize() == penSize);
        penColorChanged = !(other.getPenColor() == penColor);
    }
}
