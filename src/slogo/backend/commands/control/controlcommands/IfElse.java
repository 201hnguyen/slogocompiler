package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class IfElse extends If implements ControlInterface {

    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws ClassNotFoundException {
        String ifElseConditionArgument = parameters.get(0).toString();
        String ifBlockCommandArgument = parameters.get(1).toString();
        String elseBlockCommandArgument = parameters.get(2).toString();

        double conditionValue = calculateConditionValue(ifElseConditionArgument, turtleHistory, accessibleVariables);

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(ifBlockCommandArgument, turtleHistory, accessibleVariables, definedFunctions);
            return trueBlockManager.executeInstructionBlock();
        } else {
            CommandBlockManager falseBlockManager = new CommandBlockManager(elseBlockCommandArgument, turtleHistory, accessibleVariables, definedFunctions);
            return falseBlockManager.executeInstructionBlock();
        }
    }
}
