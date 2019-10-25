//package slogo.backend.parser;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.*;
//import java.util.List;
//import java.util.regex.Pattern;
//
//import static java.util.ResourceBundle.getBundle;
//
//public class Parser {
//
//    private static final String WHITESPACE = "\\s+"; //used in splitInput(String input)
//    private static final String SYNTAX = "Syntax";
//
//    private static String myFullInput = "";
//
//    private static String[] mySplitInput; //used in splitInput()
//    private static String myLanguagePath = ""; //where language resource files are located
//    private static String myExampleFilePath = ""; //where
//    private static List<Map.Entry<String, Pattern>> myLanguageEntries = new ArrayList<>(); // A list of map entries (command : regular expression pattern from Syntax.properties)
//    private static List<Map.Entry<String, Pattern>> mySyntaxEntries = new ArrayList<>(); //
//    private static ResourceBundle myLanguageResource; //used in addPattern()
//    private static ResourceBundle myExampleFileResource; //used in constructor 2
//    private static String filename = "";
//
//    private String myCommandsTranslated = "";
//
//    //TODO:  what is input is more than one line?
//    //TODO:  put actual symbol not properties file key if from Syntax.properties
//
//    //constructor 1
//    public Parser(String language) {
//        myLanguagePath = "resources.languages/" + language;
//        System.out.println(myLanguagePath);
//        myLanguageResource = ResourceBundle.getBundle(myLanguagePath);
//        addPatterns(myLanguageEntries, language);
//        addPatterns(mySyntaxEntries, SYNTAX);
//
//        myFullInput = "";
//    }//end constructor
//
//    //constructor 2
//    //TODO:  check filename is valid
//    //TODO:  check file contents are valid
//    public Parser(String language, String filename){
//
//        Parser p = new Parser(language);
//
//        myExampleFilePath = "resources.examples/" + filename;
//        myExampleFileResource = getBundle(myExampleFilePath);
//        try {
//            //read file to string
//            FileReader f = new FileReader(myExampleFilePath);
//        }
//        catch (FileNotFoundException e) {
//
//        }
//
//    }//end method
//
//
//
//
//    /**
//     *
//     * logic to make the string that will be sent to the back-end IFF all commands are valid
//     *
//     *
//     * @return
//     */
//    public String translateMyCommands () {
//        String ERROR = "Not all commands valid given selected language\n";
//
//        //split input
//        //TODO: check splitInput is comprehensive
//        String[] inputSplit = splitInput(myFullInput);
//
//        //get resource key
//        int badStr = 0;
//        for (String s : inputSplit) {
//            if (s.trim().length() > 0) {
//                //System.out.println(String.format("%s : %s", s, getResourceKey(s)));
//                if (s.compareTo("NO MATCH") == 0) { //
//                    badStr++;
//                }
//            }
//        }
//        //todo: add cmdException instead of ERROR
//        //if all commands have an identified type (badStr = 0), put all commands in string (for back-end)
//        if (badStr == 0) {
//            for (String s : inputSplit) {
//                if (isStringInt(s)) { //working
//                    //if (getResourceKey(s).compareTo("Constant") == 0) {
//                    //System.out.println("entered first if statement"); //testing
//                    myCommandsTranslated = myCommandsTranslated + s + " ";
//                }
//                else {
//                    myCommandsTranslated = myCommandsTranslated + getResourceKey(s) + " ";
//                }
//            }
//            return myCommandsTranslated;
//        }
//        else {
//            return ERROR;
//        }
//    }//end method
//
//
//    //addPatterns for given by getting languages resource bundle
//    private void addPatterns(List resourceEntries, String language) {
//        for (String key : Collections.list(myLanguageResource.getKeys())) {
//            String regex = myLanguageResource.getString(key);
//            resourceEntries.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
//        }
//    }//end method
//
//    //split the string from user input into an array list of strings
//    //called in translateMyCommands()
//    //TODO: add rules for multi-line code?
//    private String[] splitInput(String full_input) {
//        String[] initSplit = full_input.split(WHITESPACE);
//        mySplitInput = initSplit;
//        return mySplitInput;
//    }//end method
//
//
//    //get the key of a given pattern (value from Syntax.properties)
//    //called in translateMyCommands
//    //TODO: put in exception class
//    public String getResourceKey(String single_cmd) {
//        final String ERROR = "NO MATCH";
//        //for instructions
//        for (Map.Entry<String, Pattern> e : myLanguageEntries) {
//            if (match(single_cmd, e.getValue())) {
//                return e.getKey();
//            }
//        }
//        //for symbols
//        for (Map.Entry<String, Pattern> e : mySyntaxEntries) {
//            if (match(single_cmd, e.getValue())) {
//                return single_cmd;
//            }
//        }
//        // FIXME: perhaps throw an exception instead
//        return ERROR;
//    }//end method
//
//    //method used in getResourceEntry
//    /**
//     * Returns true if the given text matches the given regular expression pattern
//     * called in getSymbol() method
//     * @author Robert C. Duvall
//     * @param text
//     * @param regex
//     */
//    private boolean match(String text, Pattern regex) {
//        return regex.matcher(text).matches();
//    }//end method
//
//    //helper method from StackOverflow thread: https://stackoverflow.com/questions/12558206/how-can-i-check-if-a-value-is-of-type-integer
//    //TODO: put in exception class
//    private boolean isStringInt(String s) {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException ex) {
//            return false;
//        }
//    }//end method
//
//}//end class
//
