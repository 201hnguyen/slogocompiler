package slogo.backend.parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import static java.util.ResourceBundle.getBundle;

public class Parser {

    private static final String SYNTAX = "Syntax";
    private static final String WHITESPACE = "\\s+"; //used in splitInput(String input)
    private static final String COMMENT = "#";

    private static String myFullInput = "";
    private static List<String> mySplitInput = new ArrayList<>(); //used in splitInput()
    private static String myLanguagePath = ""; //where language resource files are located
    private static String myExampleFilePath = ""; //where
    private static List<Map.Entry<String, Pattern>> myLanguageEntries = new ArrayList<>(); // A list of map entries (command : regular expression pattern from Syntax.properties)
    private static List<Map.Entry<String, Pattern>> mySyntaxEntries = new ArrayList<>(); //
    private static ResourceBundle myLanguageResource; //used in addPattern()
    private static ResourceBundle myExampleFileResource; //used in constructor 2
    private static String myCommandsTranslated = "";

    //constructor 1: for use with command-line user-input
    public Parser(String full_input, String language) {
        myFullInput = full_input;
        myLanguagePath = "resources.languages/" + language;
        myLanguageResource = ResourceBundle.getBundle(myLanguagePath);
        addPatterns(myLanguageEntries, language);
        addPatterns(mySyntaxEntries, SYNTAX);
    }//end constructor

    //constructor 2: for use with .slogo or .txt files
    public Parser(String full_input, String language, String filename){
        Parser p = new Parser(full_input, language);
        myExampleFilePath = "resources.examples/" + filename;
        myExampleFileResource = getBundle(myExampleFilePath);
        try {
            FileReader f = new FileReader(myExampleFilePath);
            //TODO: read to string
        }
        catch (FileNotFoundException e) {
        }
    }

    //addPatterns for given by getting languages resource bundle
    private void addPatterns(List resourceEntries, String language) {
        for (String key : Collections.list(myLanguageResource.getKeys())) {
            String regex = myLanguageResource.getString(key);
            resourceEntries.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    public String translateCommands () {
        initialize();
        String ERROR = "Not all commands valid given selected language\n";

        splitInput();

        int badStr = 0;
        for (String s : mySplitInput) {
            badStr += s.trim().length() > 0 && getResourceKey(s).equals("NO MATCH") ? 1 : 0;
        }

        if (badStr == 0) {
            for (String s : mySplitInput) {
                myCommandsTranslated = myCommandsTranslated + getResourceKey(s) + " ";
            }
            return myCommandsTranslated;
        }
        else {
            return ERROR;
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
            //System.out.println("sdf"); //testing
            addToMySplitInput(split);
        }
    }

    //called in translateCommands
    private void addToMySplitInput(String s) {
        System.out.println(s);
        String trimmed = s.trim();
        if(trimmed.equals("")) {
            return;
        }
        String[] arr = trimmed.split(WHITESPACE);
        for(String command : arr) {
            if(command.equals("#")) {
                return;
            }
            System.out.println(command);
            mySplitInput.add(command);
        }
    }

    //called in translateCommands()
    public String getResourceKey(String single_cmd) {
        final String ERROR = "NO MATCH";
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
        // FIXME: perhaps throw an exception instead
        return ERROR;
    }//end method

    //called in getResourceKey()
    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }//end method

}//end class

