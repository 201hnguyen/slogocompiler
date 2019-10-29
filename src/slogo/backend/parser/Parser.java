package slogo.backend.parser;


import slogo.frontend.FrontEndException;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Parser {

    private static final String SYNTAX = "Syntax";
    private static final String WHITESPACE = "\\s+";
    private static final String COMMENT = "#";
    private static final String RESOURCES_PACKAGE = "resources.languages/";

    private static String myFullInput = "";
    private static ResourceBundle myResource;
    private static List<String> mySplitInput = new ArrayList<>();
    private static List<Map.Entry<String, Pattern>> myLanguageEntries = new ArrayList<>();
    private static List<Map.Entry<String, Pattern>> mySyntaxEntries = new ArrayList<>();
    private static String myCommandsTranslated = "";


    public Parser(String full_input, String language) {
        myFullInput = full_input;
        addPatterns(myLanguageEntries, language);
        addPatterns(mySyntaxEntries, SYNTAX);
    }

    /**
     * Returns the key of an entry in any language resource file
     * Returns the value of an entry in the syntax resource file
     * called in translateCommands()
     * @param single_cmd
     * @return
     */
    public String getResourceKey(String single_cmd) {
        String str = "";
        //for instructions
        for (Map.Entry<String, Pattern> e : myLanguageEntries) {
            if (match(single_cmd, e.getValue())) {
                str = e.getKey();
            }
        }
        //for symbols
        for (Map.Entry<String, Pattern> e : mySyntaxEntries) {
            if (match(single_cmd, e.getValue())) {
                str = single_cmd;
            }
        }
        return str;
    }


//TODO: exception
    /**
     *
     * @return
     */
    public String translateCommands() {

        initialize();
        splitInput();
        int badStr = 0;

        for (String s : mySplitInput) {
            badStr += s.trim().length() > 0 && getResourceKey(s).equals("NO MATCH") ? 1 : 0;
        }

        try {
            if (badStr == 0) {
                for (String s : mySplitInput) {
                    myCommandsTranslated = myCommandsTranslated + getResourceKey(s) + " ";
                }
                return myCommandsTranslated;
            }
        }
        catch (Exception ex) {
            String message = "Not all commands valid given selected language\n";
            throw new FrontEndException(ex, message);
        }
        return "";
    }

    //addPatterns for given ArrayList by getting languages resource bundle
    //called in constructor
    private void addPatterns(List resourceEntries, String language) {
        myResource = ResourceBundle.getBundle(RESOURCES_PACKAGE + language);
        for (String key : Collections.list(myResource.getKeys())) {
            String regex = myResource.getString(key);
            resourceEntries.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    //called in translateCommands
    private void initialize() {
        myCommandsTranslated = "";
        mySplitInput.clear();
    }

    //called in translateCommands
    private void splitInput() {
        String[] splitByNewLine = myFullInput.split("[\\r\\n]");
        for(String split : splitByNewLine) {
            addToMySplitInput(split);
        }
    }

    //called in translateCommands
    private void addToMySplitInput(String s) {
        String trimmed = s.trim();
        if(trimmed.equals("")) {
            return;
        }
        String[] arr = trimmed.split(WHITESPACE);
        for(String command : arr) {
            if(command.equals(COMMENT)) {
                return;
            }
            //System.out.println(command); //testing
            mySplitInput.add(command);
        }
    }

    //called in getResourceKey()
    private boolean match(String text, Pattern regex) {
        String message = "The following command is not recognized for the given pattern: ";
        try {
            return regex.matcher(text).matches();
        }
        //catch (IllegalAccessException ex) {
        catch (Exception ex) {
            throw new FrontEndException(ex, message, text);
        }
    }

}

