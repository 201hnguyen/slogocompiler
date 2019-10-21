package slogo.backend.commands.control.controlcommands;

import slogo.backend.NeedValueOfParameterException;
import slogo.backend.UnmatchedNumArgumentsException;
import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Scanner;

public class If implements ControlInterface {
    public double execute(TurtleManager turtleManager, List<Object> parameters) {
        CommandTree commandTree = new CommandTree(turtleManager);

        double conditionValue = 0;
        Scanner conditionScanner = new Scanner(parameters.get(0).toString());
        while (conditionScanner.hasNext()) {
            try {
                String command = conditionScanner.next();
                System.out.println("If condition, current passing to command tree: " + command);
                commandTree.addToCommandTree(command);
            } catch (NeedValueOfParameterException e) {
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
            CommandBlockManager trueBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleManager);
            return trueBlockManager.executeInstructionBlock();
        } else {
            return 0;
        }
    }
}