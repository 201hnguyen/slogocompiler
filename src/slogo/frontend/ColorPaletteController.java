package slogo.frontend;

import javafx.scene.paint.Color;

public class ColorPaletteController {
    private DisplayScreen displayScreen;

    public ColorPaletteController(DisplayScreen displayScreen) {
        this.displayScreen = displayScreen;
    }

    public void penColorAction(Color color) {
        displayScreen.setLineColor(color);
    }

    public void backgroundAction(Color color) {
        displayScreen.setBackground(color);
    }
}
