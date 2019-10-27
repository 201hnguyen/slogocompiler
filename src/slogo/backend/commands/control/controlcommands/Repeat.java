package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class Repeat implements ControlInterface {

    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        double returnValue = 0;
        CommandBlockManager conditionCommandBlockManager = new CommandBlockManager(parameters.get(0).toString(), turtleHistory, accessibleVariables, definedFunctions);
        double conditionValue = conditionCommandBlockManager.executeInstructionBlock();

        for (int i=0; i<(int) conditionValue; i++) {
            System.out.println("In repeat, executing block: " + parameters.get(1));
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables, definedFunctions);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        return returnValue;
    }
}

