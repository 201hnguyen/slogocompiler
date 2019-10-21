package slogo.backend;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class parser_Old {
    //test
    /**
     * Simple parser based on regular expressions that matches program strings to
     * kinds of language features.
     *
     * @author Amber Johnson and Robert Duvall
     */

    private static final String RESOURCES_PACKAGE =  "resources.languages/"; //where the language resource files are located

    private List<Map.Entry<String, Pattern>> myCommandTypes; // A list map entries (command : regular expression pattern from Syntax.properties)

    public String myCommandsTranslated;  // string of commands that have been translated using the correct resources.language file

    public String myLanguage; //The language option selected from the combo box in the GUI




    /**
     * Create an empty parser
     */
    public parser_Old(String userInput) {
        myCommandTypes = new ArrayList<Map.Entry<String, Pattern>>(); //need a method to populate this
       // myCommandTypes = addPatterns();
        myCommandsTranslated = ""; //string of translated commands
        addPatterns("English");
        addPatterns("Syntax");

    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public List addPatterns (String syntax) {
        //System.out.println("entered addPaterns()"); //testing
        //System.out.println(RESOURCES_PACKAGE); //testing

        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax); //makes the path for syntax.properties
        //System.out.println(RESOURCES_PACKAGE); //testing
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            myCommandTypes.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return myCommandTypes;
    }

    /**
     * Returns the type associated with the given text if one exists
     * returned value will be one of the keys in Syntax.properties
     * @param text
     */
    //TODO: add an exception
    public String getCommandType (String text) {
        // final String ERROR = "NO MATCH";
//        try {
        for (Map.Entry<String, Pattern> e : myCommandTypes) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return "Commands not found for selected language"; //TODO: move to cmdException.java
    }
//        //}
//       catch (IOException e) {
//        return ERROR;
//          throw new cmdException(e);
//       }


    /**
     * Returns true if the given text matches the given regular expression pattern
     * called in getSymbol() method
     * @param text
     * @param regex
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }

    /**
     * takes in
     * called in
     * @param map
     * @param commands
     * @return "translated string"
     */
    private String getCommandKeys (Map<String, Pattern> map, String commands) {

        return null;
    }



}
