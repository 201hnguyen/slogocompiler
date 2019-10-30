package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class If implements ControlInterface {
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws BackendException {
        String ifConditionArgument = parameters.get(0).toString();
        String trueBlockCommandArgument = parameters.get(1).toString();

        double conditionValue = calculateConditionValue(ifConditionArgument, turtleHistory, accessibleVariables);

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(trueBlockCommandArgument, turtleHistory, accessibleVariables, definedFunctions);
            return trueBlockManager.executeInstructionBlock();
        } else {
            return 0;
        }
    }

    protected double calculateConditionValue(String ifConditionArgument, TurtleHistory turtleHistory, List<Map<String, Double>> accessibleVariables) throws BackendException {
        CommandTree commandTree = new CommandTree(turtleHistory);
        double conditionValue = 0;
        PeekableScanner conditionScanner = new PeekableScanner(ifConditionArgument);
        while (conditionScanner.hasNext()) {
            String command = conditionScanner.next();
            System.out.println("If condition, current passing to command tree: " + command);
            command = CommandBlockManager.checkAndInputUserVariable(command, accessibleVariables);
            commandTree.addToCommandTree(command);
        }
        if(commandTree.onlyNumberLeft()) {
            conditionValue = commandTree.getLastDouble();
        }
        return conditionValue;
    }
}
