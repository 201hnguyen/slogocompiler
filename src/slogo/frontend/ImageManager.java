package slogo.frontend;

import javafx.scene.image.Image;

import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;

public class ImageManager {

    private static final String RESOURCE_PATH = "resources.ImageBundle";

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);

    public Image getImage(int imageIndex) {
        System.out.println(resourceBundle.getKeys());
        for(String key : Collections.list(resourceBundle.getKeys())) {
            int index = Integer.parseInt(resourceBundle.getString(key));
            System.out.println(index + "," +imageIndex);
            if(resourceBundle.getString(key).equals(imageIndex+"")) {
                return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(key)));
            }
        }
        return null;
    }
}
