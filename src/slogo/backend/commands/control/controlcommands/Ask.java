package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Ask implements ControlInterface {

    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {

        List<Integer> savedActiveTurtles = turtleHistory.getActiveTurtles();
        String turtlesToActivateArgument = parameters.get(0).toString();
        String askCommandsArgument = parameters.get(1).toString();

        List<Integer> turtlesToActivate = new ArrayList<>();
        CommandTree commandTree = new CommandTree(turtleHistory);
        PeekableScanner turtlesScanner = new PeekableScanner(turtlesToActivateArgument);
        while (turtlesScanner.hasNext()) {
            while (!commandTree.onlyNumberLeft()) {
                String turtleArgument = turtlesScanner.next();
                turtleArgument = CommandBlockManager.checkAndInputUserVariable(turtleArgument, accessibleVariables);
                try {
                    commandTree.addToCommandTree(turtleArgument);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            }
            turtlesToActivate.add((int) commandTree.getLastDouble());
        }

        turtleHistory.setActiveTurtles(turtlesToActivate);

        CommandBlockManager commandBlockManager = new CommandBlockManager(askCommandsArgument, turtleHistory, accessibleVariables, definedFunctions);
        double returnValue = commandBlockManager.executeInstructionBlock();

        turtleHistory.setActiveTurtles(savedActiveTurtles);

        return returnValue;
    }
}
