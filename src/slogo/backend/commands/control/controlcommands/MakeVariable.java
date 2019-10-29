package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.List;
import java.util.Map;

public class MakeVariable implements ControlInterface {

    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        PeekableScanner scanner = (PeekableScanner) parameters.get(0);
        Map<String, Double> localVariables = accessibleVariables.get(accessibleVariables.size() - 1);
        CommandTree commandTree = new CommandTree(turtleHistory);
        double returnValue = 0.0;

        String variable = scanner.next();
        try {
            commandTree.addToCommandTree(scanner.next());
            while (!commandTree.onlyNumberLeft()) {
                String command = scanner.next();
                command = CommandBlockManager.checkAndInputUserVariable(command, accessibleVariables);
                commandTree.addToCommandTree(command);
            }
            returnValue = commandTree.getLastDouble();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //FIXME
        }
        for (Map<String, Double> variableMap : accessibleVariables) {
            if (variableMap.containsKey(variable)) {
                variableMap.put(variable, returnValue);
                return returnValue;
            }
        }
        localVariables.put(variable, returnValue);
        for (String key : localVariables.keySet()) {
            System.out.println("User defined:" + key + " : " + localVariables.get(key));
        }
        return returnValue;
    }
}


