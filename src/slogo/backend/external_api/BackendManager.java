package slogo.backend.external_api;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.exceptions.BackendException;
import slogo.backend.parser.Parser;
import slogo.backend.utils.TurtleHistory;

import java.util.*;

public class BackendManager {

    private CommandBlockManager commandBlockManager;
    private Parser myCommandParser;
    private TurtleHistory turtleHistory;
    private Map<String, List<Object>> userDefinedFunctions = new HashMap<>();
    private List<String> userDefinedFunctionsAsString = new ArrayList<>();
    private List<Map<String, Double>> variables = new ArrayList<>();

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
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleHistory, variables, userDefinedFunctions);
        userDefinedFunctionsAsString.clear();
        userDefinedFunctions.clear();
        commandBlockManager.executeInstructionBlock();
        turtleHistory.toNextTurn(mergeAllAccessibleVariables(commandBlockManager.getVariables()));
        userDefinedFunctions.putAll(commandBlockManager.getUserDefinedFunctions());
        userDefinedFunctionsAsString.addAll(commandBlockManager.getUserDefinedFunctionsAsStrings());
    }

    public void setVariables(Map<String, Double> updatedVariables) {
        variables.clear();
        for(String key : updatedVariables.keySet()) {
            System.out.println(key + ", " +updatedVariables.get(key) + "sdfsdf");
        }
        variables.add(updatedVariables);
    }

    public List<String> getUserFunctions() {
        List<String> list = new ArrayList<>();
        list.addAll(userDefinedFunctionsAsString);
        return list;
    }

    public TurtleHistory getHistory() {
        return turtleHistory;
    }

    private Map<String, Double> mergeAllAccessibleVariables(List<Map<String, Double>> list) {
        Map<String, Double> mergedMap = new LinkedHashMap<>();
        for (Map<String, Double> variableMap : list) {
            mergedMap.putAll(variableMap);
        }
        return mergedMap;
    }
}
