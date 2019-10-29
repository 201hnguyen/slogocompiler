package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class DoTimes implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        Map<String, Double> localVariables = accessibleVariables.get(accessibleVariables.size()-1);
        PeekableScanner variableScanner = new PeekableScanner(parameters.get(0).toString());
        String variable = variableScanner.next();
        CommandTree commandtree = new CommandTree(turtleHistory);
        while (variableScanner.hasNext()) {
            try {
                String valueToCalculate = variableScanner.next();
                valueToCalculate = CommandBlockManager.checkAndInputUserVariable(valueToCalculate, accessibleVariables);
                commandtree.addToCommandTree(valueToCalculate);
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); //FIXME
            }
        }
        double value = 0.0;
        if (commandtree.onlyNumberLeft()) {
            value = commandtree.getLastDouble();
        }

        double returnValue = 0;
        for (int i=0; i<value; i++) {
            localVariables.put(variable, (double) i);
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables, definedFunctions);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        localVariables.remove(variable);

        return returnValue;
    }
}
