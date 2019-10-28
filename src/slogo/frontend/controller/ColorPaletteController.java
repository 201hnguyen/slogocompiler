package slogo.frontend.controller;

import javafx.scene.paint.Color;
import slogo.frontend.DisplayScreen;

import java.util.Map;

public class ColorPaletteController implements NodeController {
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

    @Override
    public Map<String, String> getChangedValues() {
        return null;
    }

    @Override
    public void setLanguage(String language) {
        return;
    }
}
