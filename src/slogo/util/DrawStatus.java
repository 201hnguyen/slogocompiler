package slogo.util;

public class DrawStatus {

    private boolean visible;
    private int backGround;
    private int imageNum;

    private boolean visibleChanged;
    private boolean backGroundChanged;
    private boolean imageChanged;
    private boolean eraseScreen = false;


    public DrawStatus(boolean visible, int backGround, int imageNum, boolean eraseScreen) {
        this.visible = visible;
        this.backGround = backGround;
        this.imageNum = imageNum;
        this.eraseScreen = eraseScreen;
    }

    public DrawStatus(DrawStatus drawStatus) {
        this.visible = drawStatus.isTurtleVisible();
        this.backGround = drawStatus.getBackGround();
        this.imageNum = drawStatus.getImageNum();
        this.eraseScreen = drawStatus.screenToBeErased();
        this.visibleChanged = drawStatus.isVisibleChanged();
        this.imageChanged = drawStatus.isImageChanged();
        this.backGroundChanged = drawStatus.isBackGroundChanged();
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

    public void setTurtleVisible(boolean isVisible) {
        this.visible = isVisible;
    }

    public void setBackGround(int backGround) {
        this.backGround = backGround;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    public void compareAndSetChanged(DrawStatus other) {
        visibleChanged = !(other.isTurtleVisible() == visible);
        backGroundChanged = !(other.getBackGround() == backGround);
        imageChanged = !(other.getImageNum() == imageNum);
        eraseScreen = !other.screenToBeErased() && eraseScreen;
    }

    public boolean screenToBeErased() {
        return eraseScreen;
    }
}
