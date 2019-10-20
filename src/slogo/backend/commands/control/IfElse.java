package slogo.backend.commands.control;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.CommandStacks;
import slogo.backend.commands.basic.CommandFactory;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Scanner;

public class IfElse implements ControlInterface {
    // parameter 0 is a boolean on whether or not the condition is true
    // parameter 1 is String of all of the true branch's instructions (basically treat it like a block that the user
    // just entered in)
    // parameter 2 is String of all the false branch's instructions

    public double execute(TurtleManager turtleManager, List<String> parameters) {
        CommandStacks commandStacks = new CommandStacks(turtleManager);
        Scanner conditionScanner = new Scanner(parameters.get(0));
        while (conditionScanner.hasNext()) {
            commandStacks.addToStack(conditionScanner.next());
        }


        for (String parameter : parameters) {
            System.out.println(parameter);
        }
//        boolean conditionIsTrue = (boolean) parameters.get(0);
//        String trueBranchInstructions = parameters.get(1).toString();
//        String falseBranchInstructions = parameters.get(2).toString();
//
//        CommandBlockManager commandBlockManager;
//
//        if (conditionIsTrue) {
//            commandBlockManager = new CommandBlockManager(trueBranchInstructions);
//        } else {
//            commandBlockManager = new CommandBlockManager(falseBranchInstructions);
//        }
//
//        if (commandBlockManager.instructionsWereExecuted()) {
//            return commandBlockManager.getLatestExecutedInstructionReturnValue();
//        } else {
//            return 0;
//        }
        return 0;
    }
}
