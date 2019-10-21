package slogo.backend.external_api;


import slogo.backend.Parser;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Map;

public class BackendManager {
    private CommandBlockManager commandBlockManager;
    private Parser myCommandParser;
    private TurtleManager turtleManager;

    public BackendManager(String language, TurtleManager turtleManager) {
        setLanguage(language);
        this.turtleManager = turtleManager;
    }

    public void setCommand(String commands) {
        String translatedCommand = myCommandParser.translateMyCommands(commands, "English");
        commandBlockManager = new CommandBlockManager(translatedCommand, turtleManager);
        commandBlockManager.executeInstructionBlock();
    }

    public void setLanguage(String language) {
        myCommandParser = new Parser(language);
    }

    public Map<String, Double> getMyVariables() {
        return null;
    }
}
