package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class Repeat implements ControlInterface {


    public double execute(TurtleHistory turtleHistory, List<Object> parameters) {
        double returnValue = 0;
        for (int i=0; i<Integer.parseInt(parameters.get(0).toString().trim()); i++) {
            System.out.println("In repeat, executing block: " + parameters.get(1));
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        return returnValue;
    }
}

