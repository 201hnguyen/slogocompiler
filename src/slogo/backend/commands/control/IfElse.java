package slogo.backend.commands.control;

import slogo.backend.NeedValueOfParameterException;
import slogo.backend.UnmatchedNumArgumentsException;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Scanner;

public class IfElse implements ControlInterface {

    public double execute(TurtleManager turtleManager, List<String> parameters) {
        CommandTree commandTree = new CommandTree(turtleManager);

        double conditionValue = 0;
        Scanner conditionScanner = new Scanner(parameters.get(0));
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("IfElse condition, current passing to command tree: " + command);
                commandTree.addToCommandTree(command);
                System.out.println(commandTree.getLastDouble()); //TODO: I'm not getting a double here;
            } catch (NeedValueOfParameterException | UnmatchedNumArgumentsException e) {
                // commandTree.putValueInsteadOfParameter();
            }
        }

        if (conditionValue != 0) {
            CommandBlockManager trueBlockManager = new CommandBlockManager(parameters.get(1), turtleManager);
            return trueBlockManager.executeInstructionBlock();
        } else {
            CommandBlockManager falseBlockManager = new CommandBlockManager(parameters.get(2), turtleManager);
            return falseBlockManager.executeInstructionBlock();
        }
    }
}
