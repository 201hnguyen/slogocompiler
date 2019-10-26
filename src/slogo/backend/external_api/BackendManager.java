package slogo.backend.external_api;


//import slogo.backend.parser.Parser;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.parser.ParserForTest;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackendManager {
    private CommandBlockManager commandBlockManager;
    private ParserForTest myCommandParser;
    private TurtleHistory turtleHistory;

    public BackendManager(String language, TurtleHistory turtleHistory) {
        setLanguage(language);
        this.turtleHistory = turtleHistory;
    }

    public void setCommand(String commands) {
        String translatedCommand = myCommandParser.translateMyCommands(commands);
        turtleHistory.clearHistory();
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleHistory, new ArrayList<>());
        commandBlockManager.executeInstructionBlock();
    }

    public void setLanguage(String language) {
        myCommandParser = new ParserForTest(language);
    }

    public Map<String, Double> getMyVariables() {
        return null;
    }

    public TurtleHistory getHistory() {
        return turtleHistory;
    }
}
