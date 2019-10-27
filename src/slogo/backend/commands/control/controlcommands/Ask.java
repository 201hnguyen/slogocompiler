package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Ask implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        List<Integer> savedActiveTurtles = turtleHistory.getActiveTurtles();

        List<String> turtlesToActivateStringList = Arrays.asList(parameters.get(0).toString().split(" "));
        List<Integer> turtlesToActivate = new ArrayList<>();
        for (String turtleID : turtlesToActivateStringList) {
            turtlesToActivate.add(Integer.parseInt(turtleID));
        }
        turtleHistory.setActiveTurtles(turtlesToActivate);

        CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables, definedFunctions);
        double returnValue = commandBlockManager.executeInstructionBlock();

        turtleHistory.setActiveTurtles(savedActiveTurtles);

        return returnValue;
    }
}
