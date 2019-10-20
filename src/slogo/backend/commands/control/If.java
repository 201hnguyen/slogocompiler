package slogo.backend.commands.control;

import slogo.backend.NeedValueOfParameterException;
import slogo.backend.UnmatchedNumArgumentsException;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Scanner;

public class If implements ControlInterface {
    public double execute(TurtleManager turtleManager, List<String> parameters) {
        CommandTree commandTree = new CommandTree(turtleManager);

        double conditionValue = 5;
        Scanner conditionScanner = new Scanner(parameters.get(0));
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("If condition, current passing to command tree: " + command);
                commandTree.addToCommandTree(command);
//                System.out.println("last double: " + commandTree.getLastDouble()); //TODO: I'm not getting a double here;
            } catch (NeedValueOfParameterException e) {

            }
        }

        try {
            conditionValue = commandTree.getLastDouble();
            System.out.println("condition value: " + conditionValue);
        } catch (UnmatchedNumArgumentsException e) {
            e.printStackTrace();
        }

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(parameters.get(1), turtleManager);
            return trueBlockManager.executeInstructionBlock();
        } else {
            return 0;
        }
    }
}
