package slogo.backend.commands.control.controlcommands;

import slogo.backend.exceptions.UnmatchedNumArgumentsException;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class If implements ControlInterface {
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, Map<String, Double> variables) {
        CommandTree commandTree = new CommandTree(turtleHistory);

        double conditionValue = 0;
        Scanner conditionScanner = new Scanner(parameters.get(0).toString());
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("If condition, current passing to command tree: " + command);
                commandTree.addToCommandTree(command);
            } catch (ClassNotFoundException e) {
                //FIXME
            }
        }

        try {
            conditionValue = commandTree.getLastDouble();
            System.out.println("condition value: " + conditionValue);
        } catch (UnmatchedNumArgumentsException e) {
            e.printStackTrace();
        }

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, variables);
            return trueBlockManager.executeInstructionBlock();
        } else {
            return 0;
        }
    }
}
