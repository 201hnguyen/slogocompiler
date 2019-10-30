package slogo.backend.external_api;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.exceptions.BackendException;
import slogo.backend.parser.Parser;
import slogo.backend.parser.ParserException;
import slogo.backend.parser.ParserForTest;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackendManager {

    private CommandBlockManager commandBlockManager;
    //private ParserForTest myCommandParser;
    private Parser myCommandParser;
    private TurtleHistory turtleHistory;

    public BackendManager(String input, String language, TurtleHistory turtleHistory) {
        setCommands(input, language);
        this.turtleHistory = turtleHistory;
    }

    /**
     * Initializes commands by calling Parser.java on user-input
     *
     * @param input
     * @param language
     */
    public void setCommands(String input, String language) {
        System.out.println(language);
        //myCommandParser = new ParserForTest(language);
        myCommandParser = new Parser(input, language);
    }

    /**
     * Begins executing user-input in Connector.java
     * Assumes all user-input matches SLogo syntax found in resources.languages/
     *
     * @param commands
     */
    public void executeCommands(String commands) throws BackendException {
        //String translatedCommand = myCommandParser.translateMyCommands(commands);
        String translatedCommand = myCommandParser.translateCommands();
        turtleHistory.clearHistory();
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleHistory, new ArrayList<>(), new HashMap<>());
        commandBlockManager.executeInstructionBlock();
    }

    /**
     * Empty method
     *
     * @return
     */
    public Map<String, Double> getMyVariables() {
        return null;
    }

    /**
     * Gets an instance of a TurtleHistory object
     * @return
     */
    public TurtleHistory getHistory() {
        return turtleHistory;
    }
}
