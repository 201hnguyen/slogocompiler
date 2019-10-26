package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class Repeat implements ControlInterface {

    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables) {
        double returnValue = 0;
        for (int i=0; i<(int) Double.parseDouble(parameters.get(0).toString().trim()); i++) {
            System.out.println("In repeat, executing block: " + parameters.get(1));
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        return returnValue;
    }
}

