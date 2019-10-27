package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.*;

public class MakeUserInstruction implements ControlInterface {

    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        String methodName = parameters.get(0).toString();
        List<Object> blockArguments = (List<Object>) parameters.get(1);
        Map<String,List<Object>> userFunctionsMap = (Map<String, List<Object>>) parameters.get(2);

        String variablesAsString = blockArguments.get(0).toString();
        String commandsAsString = blockArguments.get(1).toString();

        List<Object> commandArgumentsToStore = new ArrayList<>();

        List<String> variables = Arrays.asList(variablesAsString.split(" "));
        Map<String, Double> variablesMap = new LinkedHashMap<>();
        for (String variable : variables) {
            variablesMap.put(variable, null);
        }

        commandArgumentsToStore.add(variablesMap);
        commandArgumentsToStore.add(commandsAsString);

        userFunctionsMap.put(methodName, commandArgumentsToStore);

        return 1;
    }
}
