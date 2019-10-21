package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;
import java.util.Map;

public class MakeUserInstruction implements ControlInterface {

    String myFunctionName;
    Map<String, Double> myVariables;

    // parameter 0 is the list of variables
    // parameter 1 is the block of commands as string

    public MakeUserInstruction(List<Object> parameters) {
        String myFunctionName = parameters.get(0).toString();

    }


    @Override
    public double execute(TurtleManager turtleManager, List<Object> parameters) {
        String[] userVariables = parameters.get(0).toString().split(" ");
        CommandBlockManager blockManager = new CommandBlockManager(parameters.get(1).toString(), turtleManager);



        for (int i=0; i<parameters.size(); i++) {
            System.out.println(parameters.get(i).toString());
        }
        return 0;
    }
}
