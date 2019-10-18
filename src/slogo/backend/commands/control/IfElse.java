package slogo.backend.commands.control;

import slogo.backend.commands.CommandBlockManager;

import java.util.List;

public class IfElse {
    // parameter 0 is a boolean on whether or not the condition is true
    // parameter 1 is String of all of the true branch's instructions (basically treat it like a block that the user
    // just entered in)
    // parameter 2 is String of all the false branch's instructions

    public double execute(List<Object> parameters) {
        boolean conditionIsTrue = (boolean) parameters.get(0);
        String trueBranchInstructions = parameters.get(1).toString();
        String falseBranchInstructions = parameters.get(2).toString();

        CommandBlockManager commandBlockManager;

        if (conditionIsTrue) {
            commandBlockManager = new CommandBlockManager(trueBranchInstructions);
        } else {
            commandBlockManager = new CommandBlockManager(falseBranchInstructions);
        }

        if (commandBlockManager.instructionsWereExecuted()) {
            return commandBlockManager.getLatestExecutedInstructionReturnValue();
        } else {
            return 0;
        }
    }
}
