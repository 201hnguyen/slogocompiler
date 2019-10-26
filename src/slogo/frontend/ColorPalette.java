package slogo.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.time.temporal.ChronoField;

public class ColorPalette extends VBox {
    private static final double PICKER_WIDTH = 100;
    private static final double PICKER_HEIGHT = 40;
    private static final double CIRCLE_RADIUS = 50;
    private static final double INSET_PADDING = 10;
    private static final double YLAYOUT = 420;
    private static final double XLAYOUT = 630;
    private static final double SPACING = 10;

    private ColorPicker colorPicker;
    private CheckBox penBox;
    private CheckBox backgroundBox;
    private Circle colorCircle;
    private DisplayScreen displayScreen;

    public ColorPalette() {
        colorPicker = new ColorPicker();
        colorPicker.setMaxSize(PICKER_WIDTH,PICKER_HEIGHT);
        colorCircle = new Circle(CIRCLE_RADIUS);
        colorCircle.setFill(colorPicker.getValue());
        getChildren().addAll(colorCircle, colorPicker, checkBoxes());
        colorPicker.setOnAction(event -> {
            colorCircle.setFill(colorPicker.getValue());

        });
        setPadding(new Insets(INSET_PADDING));
        setLayoutY(YLAYOUT);
        setSpacing(SPACING);
        setLayoutX(XLAYOUT);
    }
    private HBox checkBoxes() {
        HBox checkBoxes = new HBox();
        penBox = new CheckBox("Pen");
        backgroundBox = new CheckBox("Background");
        checkBoxes.getChildren().addAll(penBox, backgroundBox);
        checkBoxes.setLayoutY(570);
        checkBoxes.setLayoutX(620);
        checkBoxes.setSpacing(10);
        return checkBoxes;
    }

    Color getColor() {  return colorPicker.getValue(); }
    CheckBox getPenBox() { return penBox; }
    CheckBox getBackgroundBox() { return backgroundBox; }
    ColorPicker getPalette() { return colorPicker; }
}
