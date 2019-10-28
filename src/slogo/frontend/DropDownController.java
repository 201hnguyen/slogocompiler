package slogo.frontend;


public class DropDownController {

    private static final String INITIAL_LANGUAGE = "English";
    private static final int INITIAL_IMAGE_NUM = 1;

    private String language;
    private int imageNum;
    private DisplayScreen displayScreen;

    public DropDownController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
        language = INITIAL_LANGUAGE;
        imageNum = INITIAL_IMAGE_NUM;
    }

    public void chooseImage(String imageNum) {
        System.out.println("selected");
        this.imageNum = Integer.parseInt(imageNum);
        displayScreen.setImage(this.imageNum);
    }

    public void chooseLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
