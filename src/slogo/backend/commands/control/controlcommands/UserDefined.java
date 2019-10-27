package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDefined implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables) {
        Map<String, Double> arguments = (Map<String, Double>) parameters.get(0);
        for (String key : arguments.keySet()) {
            System.out.println("Testing in user defined: " + key + ":" + arguments.get(key));
        }
        List<Map<String, Double>> variablesInScope = new ArrayList<>();
        variablesInScope.addAll(accessibleVariables);
        variablesInScope.add(arguments);
        CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, variablesInScope);
        commandBlockManager.executeInstructionBlock();
        return 0;
    }
}
