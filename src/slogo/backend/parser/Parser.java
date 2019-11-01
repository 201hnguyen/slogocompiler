package slogo.backend.parser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Takes in a String of SLogo commands and checks if they 
 *
 * @author Amber Johnson, Eric Han, and Robert C. Duvall
 */
public class Parser {

    private static final String SYNTAX = "Syntax";
    private static final String WHITESPACE = "\\s+";
    private static final String COMMENT = "#";
    private static final String RESOURCES_PACKAGE = "resources.languages/";

    private String myFullInput = "";
    private ResourceBundle myResource;
    private List<String> mySplitInput = new ArrayList<>();
    private List<Map.Entry<String, Pattern>> myLanguageEntries = new ArrayList<>();
    private List<Map.Entry<String, Pattern>> mySyntaxEntries = new ArrayList<>();
    private String myCommandsTranslated = "";

    public Parser(String full_input, String language) {
        myFullInput = full_input;
        addPatterns(myLanguageEntries, language);
        addPatterns(mySyntaxEntries, SYNTAX);
    }

    /**
     * Returns the key of an entry in any language resource file
     * Returns the value of an entry in the syntax resource file
     * Used in Parser.translateCommands()
     *
     * @param single_cmd
     * @return
     */
    public String getResourceKey(String single_cmd) throws ParserException{
        //for instructions
        for (Map.Entry<String, Pattern> e : myLanguageEntries) {
            if (match(single_cmd, e.getValue())) {
                return e.getKey();
            }
        }
        //for symbols
        for (Map.Entry<String, Pattern> e : mySyntaxEntries) {
            if (match(single_cmd, e.getValue())) {
                return single_cmd;
            }
        }
        throw new ParserException(single_cmd);
    }

    /**
     * Calls splitInput method to split global variable into mySplitInput
     * Checks if any of the commands did not match the entries in the resource file
     * Returns a String of the concatenated commands translated (
     *
     * @return
     */
    public String translateCommands() throws ParserException {
        initialize();
        splitInput();
        for (String s : mySplitInput) {
            myCommandsTranslated = myCommandsTranslated + getResourceKey(s) + " ";
        }
        return myCommandsTranslated;
    }

    // Adds key-value entries to a list from resources.languages in the constructor
    private void addPatterns(List resourceEntries, String language) {
        myResource = ResourceBundle.getBundle(RESOURCES_PACKAGE + language);
        for (String key : Collections.list(myResource.getKeys())) {
            String regex = myResource.getString(key);
            resourceEntries.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    // Clears variables used in Parser.translateCommands()
    private void initialize () {
        myCommandsTranslated = "";
        mySplitInput.clear();
    }

    // Splits myFullInput in Parser.translateCommands()
    private void splitInput () {
        String[] splitByNewLine = myFullInput.split("[\\r\\n]");
        for (String split : splitByNewLine) {
            addToMySplitInput(split);
        }
    }

    // Processes comments in Parser.translateCommands()
    private void addToMySplitInput (String s){
        String trimmed = s.trim();
        if (trimmed.equals("")) {
            return;
        }
        String[] arr = trimmed.split(WHITESPACE);
        for (String command : arr) {
            if (command.equals(COMMENT)) {
                return;
            }
            mySplitInput.add(command);
        }
    }

    // Returns true if text matches regular expression deliminator in Parser.getResourceKey()
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}

