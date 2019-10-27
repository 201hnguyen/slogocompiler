package slogo.frontend.reference;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import java.util.ResourceBundle;

public class TableRowData {
    private static final String DESCRIPTION_FILE_NAME="CommandDescription";
    private static final String LANGUAGE_PATH = "language.";

    private ResourceBundle commandDescriptionResource;
    private ResourceBundle languageResource;
    private String command;
    private String description;
    private String parameters;
    private String returns;

    public TableRowData(String commandName, String path, String language) {
        commandDescriptionResource = ResourceBundle.getBundle(path+DESCRIPTION_FILE_NAME);
        languageResource = ResourceBundle.getBundle(path + LANGUAGE_PATH + language);
        command = languageResource.getString(commandName);
        String[] colData = commandDescriptionResource.getString(commandName).split(",");
        description = colData[0];
        parameters = colData[1];
        returns = colData[2];
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getParameters() {
        return parameters;
    }

    public String getReturns() {
        return returns;
    }
}
