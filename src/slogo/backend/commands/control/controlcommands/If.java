package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class If implements ControlInterface {
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        String ifConditionArgument = parameters.get(0).toString();
        String trueBlockCommandArgument = parameters.get(1).toString();


        CommandTree commandTree = new CommandTree(turtleHistory);

        double conditionValue = 0;
        Scanner conditionScanner = new Scanner(ifConditionArgument);
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("If condition, current passing to command tree: " + command);
                command = CommandBlockManager.checkAndInputUserVariable(command, accessibleVariables);
                commandTree.addToCommandTree(command);
            } catch (ClassNotFoundException e) {
                //FIXME
            }
        }

        if(commandTree.onlyNumberLeft()) {
            conditionValue = commandTree.getLastDouble();
            System.out.println("condition value: " + conditionValue);
        }

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(trueBlockCommandArgument, turtleHistory, accessibleVariables, definedFunctions);
            return trueBlockManager.executeInstructionBlock();
        } else {
            return 0;
        }
    }
}
