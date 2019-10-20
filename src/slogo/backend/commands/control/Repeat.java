package slogo.backend.commands.control;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class Repeat implements ControlInterface{
    // parameter 0 is int representing the number of times to execute
    // parameter 1 is String representing the block of instruction within the repeat (basically treat it like a block
    // that the user just entered in)

    // CommandBlockManager executes sequential blocks of instructions (overhead class for entire block of
    // instruction that user enters; also used in control instructions when breaking down inner blocks);
    // should manage stacks of instructions and parameters; should have a factory object to make instructions; etc.

    public double execute(TurtleManager turtleManager, List<String> parameters) {

//        int timesToExecute = (int) parameters.get(0);
//        String commandsToExecute = parameters.get(1).toString();
//
//        CommandBlockManager commandBlockManager = new CommandBlockManager(commandsToExecute);
//
//        for (int i=0 ; i < timesToExecute; i++) {
//            commandBlockManager.executeInstructionBlock();
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

