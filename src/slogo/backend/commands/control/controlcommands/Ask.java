package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

/**
 *
 * @author: Ha Nguyen
 */
public class Ask extends Tell implements ControlInterface {

    /**
     * Executes Ask command by telling the turtles given in the first element of parameters to follow the the commands
     * given in the second element of parameters.
     * @param turtleHistory the turtle history class that manages all turtles, of which the command will be executed on
     * @param parameters arguments for the control; the 0th element is the String of ids of the turtles that will follow
     *                   the commands and the 1st element is the String of the commands themselves.
     * @param accessibleVariables all the variables that this command has access to
     * @param definedFunctions all the functions that this command has access to
     * @return
     * @throws ClassNotFoundException
     * @throws BackendException
     */
    @Override
    public double execute(TurtleHistory turtleHistory,
                          List<Object> parameters,
                          List<Map<String, Double>> accessibleVariables,
                          Map<String, List<Object>> definedFunctions) throws BackendException {

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
