package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class For implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {

        PeekableScanner scanner = new PeekableScanner(parameters.get(0).toString());
        Map<String, Double> localVariables = accessibleVariables.get(accessibleVariables.size()-1);

        String variable = scanner.next();
        List<Double> parameterValues = new ArrayList<>();

        for (int i=0; i<3; i++) {
            CommandTree commandTree = new CommandTree(turtleHistory);
            while (!commandTree.onlyNumberLeft()) {
                String nextVal = scanner.next();
                nextVal = CommandBlockManager.checkAndInputUserVariable(nextVal, accessibleVariables);
                try {
                    commandTree.addToCommandTree(nextVal);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            }
            parameterValues.add(commandTree.getLastDouble());
        }

        double start = parameterValues.get(0);
        double end = parameterValues.get(1);
        double increment = parameterValues.get(2);

        double returnValue = 0;
        for (int i = (int) start; i <= end; i+=increment) {
            localVariables.put(variable, (double) i);
            CommandBlockManager commandBlockManager = new CommandBlockManager(parameters.get(1).toString(), turtleHistory, accessibleVariables, definedFunctions);
            returnValue = commandBlockManager.executeInstructionBlock();
        }
        localVariables.remove(variable);

        return returnValue;
    }
}
