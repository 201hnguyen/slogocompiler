package slogo.backend.commands.control;

import slogo.backend.commands.CommandBlockManager;

import java.util.List;

public class If {
    public double execute(List<Object> parameters) {
        boolean conditionIsTrue = (boolean) parameters.get(0);
        String instructions = parameters.get(1).toString();

        CommandBlockManager commandBlockManager = new CommandBlockManager(instructions);

        if (conditionIsTrue) {
            commandBlockManager.executeInstructionBlock();
        }

        if (commandBlockManager.instructionsWereExecuted()) {
            return commandBlockManager.getLatestExecutedInstructionReturnValue();
        } else {
            return 0;
        }
    }
}
