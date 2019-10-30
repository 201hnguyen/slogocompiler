package slogo.backend.external_api;

import slogo.backend.commands.CommandBlockManager;
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
        setLanguage(input, language);
        this.turtleHistory = turtleHistory;
    }

    //TODO: return error message from ParserException
    // should it return a ParserException object?
    //public void setLanguage(String input, String language) {
    public void setLanguage(String input, String language) {
        System.out.println(language);
        //myCommandParser = new ParserForTest(language);
        myCommandParser = new Parser(input, language);
    }

    //TODO: return error message from ParserException
    // should it return a ParserException object?
    public void setCommand(String commands) {
        //String translatedCommand = myCommandParser.translateMyCommands(commands);
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
