package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tell implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) throws BackendException {
        String turtlesToActivateArgument = parameters.get(0).toString();

        List<Integer> turtlesToActivate = setActivatedTurtles(turtleHistory, accessibleVariables, turtlesToActivateArgument);
        turtleHistory.setActiveTurtles(turtlesToActivate);
        return turtleHistory.getActiveTurtles().get(turtleHistory.getActiveTurtles().size()-1);
    }

    protected List<Integer> setActivatedTurtles(TurtleHistory turtleHistory, List<Map<String,Double>> accessibleVariables, String turtlesToActivateArgument) throws BackendException {
        List<Integer> turtlesToActivate = new ArrayList<>();
        PeekableScanner turtlesScanner = new PeekableScanner(turtlesToActivateArgument);
        while (turtlesScanner.hasNext()) {
            CommandTree commandTree = new CommandTree(turtleHistory);
            while (!commandTree.onlyNumberLeft()) {
                String turtleArgument = turtlesScanner.next();
                if(turtleArgument.charAt(0) == ':') {
                    for (Map<String, Double> variableMap : accessibleVariables) {
                        if (variableMap.containsKey(turtleArgument)) {
                            turtleArgument = variableMap.get(turtleArgument).toString();
                        }
                    }
                }
                turtleArgument = CommandBlockManager.checkAndInputUserVariable(turtleArgument, accessibleVariables);
                commandTree.addToCommandTree(turtleArgument);
            }
            turtlesToActivate.add((int) commandTree.getLastDouble());
        }
        return turtlesToActivate;
    }
}
