package slogo.backend.utils;

public class DrawStatus {
    private boolean visible;
    private int backGround;
    private int imageNum;

    public DrawStatus(boolean visible, int backGround, int imageNum) {
        this.visible = visible;
        this.backGround = backGround;
        this.imageNum = imageNum;
    }

    public boolean isTurtleVisible() {
        return visible;
    }

    public int getBackGround() {
        return backGround;
    }

    public int getImageNum() {
        return imageNum;
    }
}
