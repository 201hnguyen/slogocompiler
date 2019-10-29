package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.PeekableScanner;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tell implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        String turtlesToActivateArgument = parameters.get(0).toString();

        List<Integer> turtlesToActivate = setActivatedTurtles(turtleHistory, accessibleVariables, turtlesToActivateArgument);
        turtleHistory.setActiveTurtles(turtlesToActivate);
        return turtleHistory.getActiveTurtles().get(turtleHistory.getActiveTurtles().size()-1);
    }

    protected List<Integer> setActivatedTurtles(TurtleHistory turtleHistory, List<Map<String,Double>> accessibleVariables, String turtlesToActivateArgument) {
        List<Integer> turtlesToActivate = new ArrayList<>();
        CommandTree commandTree = new CommandTree(turtleHistory);
        PeekableScanner turtlesScanner = new PeekableScanner(turtlesToActivateArgument);
        while (turtlesScanner.hasNext()) {
            while (!commandTree.onlyNumberLeft()) {
                String turtleArgument = turtlesScanner.next();
                turtleArgument = CommandBlockManager.checkAndInputUserVariable(turtleArgument, accessibleVariables);
                try {
                    commandTree.addToCommandTree(turtleArgument);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            }
            turtlesToActivate.add((int) commandTree.getLastDouble());
        }
        return turtlesToActivate;
    }
}
