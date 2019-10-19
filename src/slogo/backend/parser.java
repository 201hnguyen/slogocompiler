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
    //TODO: fix this path
    //where to find resources specifically for this class
    //private static final String RESOURCES_PACKAGE = "C://Users/amber1/Documents/CS308/slogo_team05/src/resources/languages";

    private static final String RESOURCES_PACKAGE =  "resources.languages/";


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
        //System.out.println("entered addPaterns()");
        //System.out.println(RESOURCES_PACKAGE);

        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax); //makes the path for syntax.properties
        //System.out.println(RESOURCES_PACKAGE); //testing
        for (String key : Collections.list(resources.getKeys())) {
            String regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    /**
     * Returns the type associated with the given text if one exists
     * returned value will be one of the keys in Syntax.properties
     * @param text
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
//        catch (){
//
//            //return ERROR;
//            throw new cmdException();
//        }



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


}
