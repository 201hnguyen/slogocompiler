package slogo.frontend;


public class DropDownController {

    private static final String INITIAL_LANGUAGE = "English";
    private static final int INITIAL_IMAGENUM = 1;

    private String language;
    private int imageNum;
    private DisplayScreen displayScreen;

    public DropDownController(DisplayScreen displayScreen) {
        language = INITIAL_LANGUAGE;
        imageNum = INITIAL_IMAGENUM;
    }

    public void chooseImage(String imageNum) {
        this.imageNum = Integer.parseInt(imageNum);
    }

    public void chooseLanguage(String language) {
        this.language = language;
    }

    public int getImageNum() {
        return imageNum;
    }

    public String getLanguage() {
        return language;
    }
}
