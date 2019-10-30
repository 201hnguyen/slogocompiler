package slogo.backend.external_api;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.exceptions.BackendException;
import slogo.backend.parser.Parser;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackendManager {

    private CommandBlockManager commandBlockManager;
    private Parser myCommandParser;
    private TurtleHistory turtleHistory;
    private Map<String, List<Object>> userDefinedFunctions = new HashMap<>();
    private List<String> userDefinedFunctionsAsString = new ArrayList<>();

    public BackendManager(String input, String language, TurtleHistory turtleHistory) {
        setCommands(input, language);
        this.turtleHistory = turtleHistory;
    }


    public void setCommands(String input, String language) {
        myCommandParser = new Parser(input, language);
    }

    public void executeCommands() throws BackendException {
        String translatedCommand = myCommandParser.translateCommands();
        turtleHistory.clearHistory();
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleHistory, new ArrayList<>(), userDefinedFunctions);
        userDefinedFunctionsAsString.clear();
        userDefinedFunctions.clear();
        commandBlockManager.executeInstructionBlock();
        userDefinedFunctions.putAll(commandBlockManager.getUserDefinedFunctions());
        userDefinedFunctionsAsString.addAll(commandBlockManager.getUserDefinedFunctionsAsStrings());
    }

    public List<String> getUserFunctions() {
        List<String> list = new ArrayList<>();
        list.addAll(userDefinedFunctionsAsString);
        return list;
    }

    public TurtleHistory getHistory() {
        return turtleHistory;
    }
}
