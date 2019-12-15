package slogo.frontend.creater;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.frontend.controller.ImageVBoxController;
import slogo.frontend.controller.NodeController;
import slogo.frontend.turtlescreen.ImageManager;

import java.util.Collections;
import java.util.Map;
import java.util.ResourceBundle;

public class ImageVBoxCreator extends HBox implements ChangeableNode {
    private static final double INSET_PADDING = 10;
    private static final double SPACING = 25;
    private static final double Y_LAYOUT = 430;
    private static final double X_LAYOUT = 20;
    private static final String RESOURCE_PATH = "resources.frontend.ButtonResource";
    private static final String LANGUAGE_INDEX_PATH = "resources.frontend.changingfeature.LanguageIndex";
    private static final String IMAGE_RESOURCE = "resources.frontend.dropdown.Image";
    private static final String BUTTON_NAMES = "resources.frontend.changingfeature.ButtonNames";
    private static final String INITIAL_LANGUAGE = "English";

    private ResourceBundle resourceBundle;
    private String language = INITIAL_LANGUAGE;
    private ResourceBundle languageBundle = ResourceBundle.getBundle(LANGUAGE_INDEX_PATH);
    private ResourceBundle imageBundle = ResourceBundle.getBundle(IMAGE_RESOURCE);
    private ImageManager myImageManager;
    private Image myImage;
    private ImageView imageView;
    private NodeController myImageVBoxController;


    public ImageVBoxCreator(NodeController nodeController) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        myImageVBoxController = nodeController;
        myImageManager = new ImageManager();
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(INSET_PADDING, INSET_PADDING, INSET_PADDING, INSET_PADDING));
        setSpacing(SPACING);
        setLayoutY(Y_LAYOUT);
        setLayoutX(X_LAYOUT);
        imageView = new ImageView();
        addImages();
    }

    @Override
    public Map<String, String> getChangedValues() {
        return null;
    }

    @Override
    public void setLanguage(String language) {
        this.language = language;
    }

    private void getImage(ImageManager imageManager, int imageIndex) {
        myImageManager = imageManager;
        myImage = myImageManager.getImage(imageIndex);
    }

    private void addImages() {
        this.getChildren().clear();
        for (String key : Collections.list(imageBundle.getKeys())) {
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            //imageView.setPreserveRatio(true);
            imageView = new ImageView(imageBundle.getString(key));
            this.getChildren().add(imageView);
        }
    }

}
