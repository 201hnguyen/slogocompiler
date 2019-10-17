package slogo.backend;

import java.util.*;
import java.util.regex.Pattern;

public class parser {
    //test
    /**
     * Simple parser based on regular expressions that matches program strings to
     * kinds of language features.
     *
     * @author Amber Johnson and Robert Duvall
     */

    // where to find resources specifically for this class
    private static final String RESOURCES_PACKAGE = parser.class.getPackageName() + "/../../src/resources/languages/";

    // "types" and the regular expression patterns that recognize those types
    private List<Map.Entry<String, Pattern>> mySymbols;

    // list of commands that are
    public List<String> myCommands;


    /**
     * Create an empty parser
     */
    public parser () {
        mySymbols = new ArrayList<>();
        myCommands = new ArrayList<>();
    }

    /**
     * Adds the given resource file to this language's recognized types
     */
    public void addPatterns (String syntax) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    //TODO: add an exception
    public String getSymbol (String text) {
        // final String ERROR = "NO MATCH";
//        try {
        for (Map.Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        return "Commands do not match selected language"; //TODO: move to cmdException.java
    }
        //}
//        catch (cmdException e){
//            // FIXME: perhaps throw an exception instead
//            //return ERROR;
//            new cmdException();
//        }



    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private boolean match (String text, Pattern regex) {
        // THIS IS THE IMPORTANT LINE
        return regex.matcher(text).matches();
    }










}
