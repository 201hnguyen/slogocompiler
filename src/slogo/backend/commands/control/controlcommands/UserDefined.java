package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.*;

public class UserDefined implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables) {
        List<Double> numericalArguments = (List<Double>) parameters.get(0);
        Map<String, Double> methodParameters = (LinkedHashMap<String, Double>) parameters.get(1);
        String commandBlockAsString = parameters.get(2).toString();
        List<Map<String, Double>> currentAccessibleVariables = accessibleVariables;
        currentAccessibleVariables.add(methodParameters);

        for (Double number : numericalArguments) {
            System.out.println("In user defined, testing numerical arguments: " + number);
        }

        System.out.println("In user defined, testing commandS string arguments: " + commandBlockAsString);

        int numericalArgumentsIndex = 0;
        for (String key : methodParameters.keySet()) {
            methodParameters.put(key, numericalArguments.get(numericalArgumentsIndex));
            numericalArgumentsIndex++;
        }

        for (String key : methodParameters.keySet()) {
            System.out.println("In user defined, testing parameters arguments: " + key + ":" + methodParameters.get(key));
        }

        CommandBlockManager commandBlockManager = new CommandBlockManager(commandBlockAsString, turtleHistory, currentAccessibleVariables);
        commandBlockManager.executeInstructionBlock();
        return 1;
    }
}
