package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Tell implements ControlInterface {
    @Override
    public double execute(TurtleHistory turtleHistory, List<Object> parameters, List<Map<String, Double>> accessibleVariables, Map<String, List<Object>> definedFunctions) {
        String turtlesToActivateArgument = parameters.get(0).toString();

        List<String> turtlesToActivateStringList = Arrays.asList(turtlesToActivateArgument.split(" "));
        List<Integer> turtlesToActivate = new ArrayList<>();
        for (String turtleID : turtlesToActivateStringList) {
            turtlesToActivate.add(Integer.parseInt(turtleID));
        }
        turtleHistory.setActiveTurtles(turtlesToActivate);
        for (Integer id : turtleHistory.getActiveTurtles()) {
            System.out.println("TELL ACTIVATED TURTLE" + id);
        }
        return turtleHistory.getActiveTurtles().get(turtleHistory.getActiveTurtles().size()-1);
    }
}
