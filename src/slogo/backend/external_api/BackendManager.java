package slogo.backend.external_api;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.exceptions.BackendException;
import slogo.backend.parser.Parser;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackendManager {

    private CommandBlockManager commandBlockManager;
    private Parser myCommandParser;
    private TurtleHistory turtleHistory;

    public BackendManager(String input, String language, TurtleHistory turtleHistory) {
        setCommands(input, language);
        this.turtleHistory = turtleHistory;
    }


    public void setCommands(String input, String language) {
        myCommandParser = new Parser(input, language);
    }

    public void executeCommands(String commands) throws BackendException {
        String translatedCommand = myCommandParser.translateCommands();
        turtleHistory.clearHistory();
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleHistory, new ArrayList<>(), new HashMap<>());
        commandBlockManager.executeInstructionBlock();
    }

    public Map<String, Double> getMyVariables() {
        return null;
    }

    public TurtleHistory getHistory() {
        return turtleHistory;
    }
}
