package slogo.backend.utils;

public class DrawStatus {
    private boolean visible;
    private boolean penDown;
    private int backGround;
    private int imageNum;

    public DrawStatus(boolean visible, boolean penDown, int backGround, int imageNum) {
        this.visible = visible;
        this.penDown = penDown;
        this.backGround = backGround;
        this.imageNum = imageNum;
    }

    public boolean isTurtleVisible() {
        return visible;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public int getBackGround() {
        return backGround;
    }

    public int getImageNum() {
        return imageNum;
    }
}
