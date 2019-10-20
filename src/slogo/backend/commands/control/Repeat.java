package slogo.backend.commands.control;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class Repeat implements ControlInterface{


    public double execute(TurtleManager turtleManager, List<String> parameters) {
        double returnValue = 0;
        for (int i=0; i<Integer.parseInt(parameters.get(0).trim()); i++) {
            System.out.println("In repeat, executing block: " + parameters.get(1));
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1), turtleManager);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        return returnValue;
    }
}

