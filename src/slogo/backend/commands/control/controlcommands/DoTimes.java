package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class DoTimes implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory,
                          List<Object> parameters,
                          List<Map<String, Double>> accessibleVariables,
                          Map<String, List<Object>> definedFunctions) throws BackendException {
        Map<String, Double> localVariables = accessibleVariables.get(accessibleVariables.size()-1);
        String variableLimitArgument = parameters.get(0).toString();
        String doTimesCommandArgument = parameters.get(1).toString();

        PeekableScanner variableScanner = new PeekableScanner(variableLimitArgument);
        String variable = variableScanner.next();

        double returnValue = 0;

        double variableLimit = calculateVariableLimit(variableScanner, turtleHistory, accessibleVariables);

        for (int i=0; i<variableLimit; i++) {
            localVariables.put(variable, (double) i);
            CommandBlockManager commandBlockManager = new CommandBlockManager(doTimesCommandArgument, turtleHistory, accessibleVariables, definedFunctions);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        localVariables.remove(variable);

        return returnValue;
    }

    private double calculateVariableLimit(PeekableScanner variableScanner, TurtleHistory turtleHistory, List<Map<String, Double>> accessibleVariables) throws BackendException {
        CommandTree commandtree = new CommandTree(turtleHistory);
        while (variableScanner.hasNext()) {
            String valueToCalculate = variableScanner.next();
            valueToCalculate = CommandBlockManager.checkAndInputUserVariable(valueToCalculate, accessibleVariables);
            commandtree.addToCommandTree(valueToCalculate);
        }
        double variableLimit = 0.0;
        if (commandtree.onlyNumberLeft()) {
            variableLimit = commandtree.getLastDouble();
        }
        return variableLimit;
    }
}
