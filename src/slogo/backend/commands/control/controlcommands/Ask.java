package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class Ask extends Tell implements ControlInterface {

    @Override
    public double execute(TurtleHistory turtleHistory,
                          List<Object> parameters,
                          List<Map<String, Double>> accessibleVariables,
                          Map<String, List<Object>> definedFunctions) throws ClassNotFoundException, BackendException {

        List<Integer> previousActiveTurtles = turtleHistory.getActiveTurtles();
        String turtlesToActivateArgument = parameters.get(0).toString();
        String askCommandsArgument = parameters.get(1).toString();

        List<Integer> turtlesToActivate = setActivatedTurtles(turtleHistory, accessibleVariables, turtlesToActivateArgument);

        turtleHistory.setActiveTurtles(turtlesToActivate);

        CommandBlockManager commandBlockManager = new CommandBlockManager(askCommandsArgument, turtleHistory, accessibleVariables, definedFunctions);
        double returnValue = commandBlockManager.executeInstructionBlock();

        turtleHistory.setActiveTurtles(previousActiveTurtles);

        return returnValue;
    }
}
