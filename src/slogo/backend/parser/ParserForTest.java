package slogo.backend.parser;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class ParserForTest {

    private static final String WHITESPACE = "\\s+"; //used in splitInput(String input)
    private static final String RESOURCES_PACKAGE =  "resources.languages/"; //where the language resource files are located
    private static final String SYNTAX = "Syntax";
    private static final String COMMENT = "#";

    private static List<String> mySplitInput = new ArrayList<>(); //used in splitInput()
    private static List<Map.Entry<String, Pattern>> myLanguageEntries = new ArrayList<>(); // A list of map entries (command : regular expression pattern from Syntax.properties)
    private static List<Map.Entry<String, Pattern>> mySyntaxEntries = new ArrayList<>(); //
    private static ResourceBundle myResource; //used in addPattern()

    private String myCommandsTranslated="";

    //TODO:  what is input is more than one line?
    //TODO:  put actual symbol not properties file key if from Syntax.properties

    //constructor
    public ParserForTest (String language) {
        addPatterns(myLanguageEntries, language);
        addPatterns(mySyntaxEntries, SYNTAX);
    }//end constructor

    //TODO: make a new cmdException
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
    }

    public String translateMyCommands (String full_input) {
        initialize();
        String ERROR = "Not all commands valid given selected language\n";

        splitInput(full_input);

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

    private void addPatterns(List resourceEntries, String language) {
        myResource = ResourceBundle.getBundle(RESOURCES_PACKAGE + language);
        for (String key : Collections.list(myResource.getKeys())) {
            String regex = myResource.getString(key);
            resourceEntries.add(new AbstractMap.SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private void splitInput(String full_input) {
        String[] splitByNewLine = full_input.split("[\\r\\n]");
        for(String split : splitByNewLine) {
            System.out.println("sdf");
            addToMySplitInput(split);
        }
    }

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

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    private void initialize() {
        myCommandsTranslated = "";
        mySplitInput.clear();
    }

}
