package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class Repeat implements ControlInterface {
    private List<Map<String, Double>> variables;

    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws BackendException {

        String repeatCondition = parameters.get(0).toString();
        String repeatBlockCommandArgument = parameters.get(1).toString();
        double returnValue = 0;

        CommandBlockManager conditionCommandBlockManager = new CommandBlockManager(repeatCondition, turtleHistory, accessibleVariables, definedFunctions);
        double numTimesRepeated = conditionCommandBlockManager.executeInstructionBlock();
        variables = conditionCommandBlockManager.getVariables();

        for (int i=0; i<(int) numTimesRepeated; i++) {
            CommandBlockManager commandBlockManager = new CommandBlockManager(repeatBlockCommandArgument, turtleHistory, variables, definedFunctions);
            variables = commandBlockManager.getVariables();
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        return returnValue;
    }
}

