package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserDefined implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws BackendException {
        List<Double> numericalArguments = (List<Double>) parameters.get(0);
        Map<String, Double> methodParameters = (LinkedHashMap<String, Double>) parameters.get(1);
        String userDefinedCommandBlockArgument = parameters.get(2).toString();
        List<Map<String, Double>> currentAccessibleVariables = accessibleVariables;
        currentAccessibleVariables.add(methodParameters);

        int numericalArgumentsIndex = 0;
        for (String key : methodParameters.keySet()) {
            methodParameters.put(key, numericalArguments.get(numericalArgumentsIndex));
            numericalArgumentsIndex++;
        }

        CommandBlockManager commandBlockManager = new CommandBlockManager(userDefinedCommandBlockArgument, turtleHistory, currentAccessibleVariables, definedFunctions);
        commandBlockManager.executeInstructionBlock();
        return 1;
    }
}
