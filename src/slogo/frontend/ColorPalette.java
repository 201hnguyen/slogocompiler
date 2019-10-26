package slogo.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColorPalette extends VBox {
    private ColorPicker colorPicker;
    private static final double PICKER_WIDTH = 100;
    private static final double PICKER_HEIGHT = 40;
    private static final double CIRCLE_RADIUS = 50;
    private static final double INSET_PADDING = 10;
    private static final double YLAYOUT = 420;
    private static final double XLAYOUT = 630;
    private static final double SPACING = 10;

    public ColorPalette() {
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(PICKER_WIDTH,PICKER_HEIGHT);
        Circle colorCircle = new Circle(CIRCLE_RADIUS);
        colorCircle.setFill(colorPicker.getValue());
        setPadding(new Insets(INSET_PADDING));
        getChildren().addAll(colorCircle, colorPicker);
        colorCircle.setFill(colorPicker.getValue());
        setLayoutY(YLAYOUT);
        setSpacing(SPACING);
        setLayoutX(XLAYOUT);

    }
    Color getColor() {
        return colorPicker.getValue();

    }

    ColorPicker getPalette() {
        return colorPicker;
    }
}
