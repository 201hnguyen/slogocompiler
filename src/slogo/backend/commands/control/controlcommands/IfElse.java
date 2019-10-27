package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IfElse implements ControlInterface {

    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        CommandTree commandTree = new CommandTree(turtleHistory);

        double conditionValue = 0;
        Scanner conditionScanner = new Scanner(parameters.get(0).toString());
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("IfElse condition, current passing to command tree: " + command);
                commandTree.addToCommandTree(command);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if(commandTree.onlyNumberLeft()) {
            conditionValue = commandTree.getLastDouble();
            System.out.println("condition value: " + conditionValue);
        } else {
            /** FIXME: throw new Exception.
             *
             */
        }

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables, definedFunctions);
            return trueBlockManager.executeInstructionBlock();
        } else {
            CommandBlockManager falseBlockManager = new CommandBlockManager(parameters.get(2).toString(), turtleHistory, accessibleVariables, definedFunctions);
            return falseBlockManager.executeInstructionBlock();
        }
    }
}
