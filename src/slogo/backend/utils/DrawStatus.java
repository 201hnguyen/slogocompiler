package slogo.backend.utils;

public class DrawStatus {

    private boolean visible;
    private int backGround;
    private int imageNum;

    private boolean visibleChanged;
    private boolean backGroundChanged;
    private boolean imageChanged;


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

    public boolean isVisibleChanged() {
        return visibleChanged;
    }

    public boolean isBackGroundChanged() {
        return backGroundChanged;
    }

    public boolean isImageChanged() {
        return imageChanged;
    }

    public void update(DrawStatus other) {
        compareAndSetChanged(other);
        visible = other.isTurtleVisible();
        backGround = other.getBackGround();
        imageNum = other.getImageNum();
    }

    private void compareAndSetChanged(DrawStatus other) {
        visibleChanged = !(other.isTurtleVisible() == visible);
        backGroundChanged = !(other.getBackGround() == backGround);
        imageChanged = !(other.getImageNum() == imageNum);
    }
}
