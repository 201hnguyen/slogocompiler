package slogo.frontend.turtlescreen;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImageCollection extends TilePane {

    private static final String RESOURCE_PATH = "resources.frontend.dropdown.Image";
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private static final double WIDTH = 50;
    private static final double HEIGHT = 50;
    private static final double Y_AXIS = 15;
    private static final double X_AXIS = 550;


    public ImageCollection() {
        setPrefSize(WIDTH,HEIGHT);
        setLayoutY(Y_AXIS);
        setLayoutX(X_AXIS);
        imageCollector();
    }

    private void imageCollector() {
        for(String key : Collections.list(resourceBundle.getKeys())) {
            ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resourceBundle.getString(key)))));
            imageView.setFitHeight(HEIGHT);
            imageView.setFitWidth(WIDTH);
            getChildren().add(imageView);
    }
    }
}
