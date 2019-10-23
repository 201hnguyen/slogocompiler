package slogo.backend.commands.control.controlcommands;

import slogo.backend.commands.CommandBlockManager;
import slogo.backend.commands.control.ControlInterface;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MakeUserInstruction implements ControlInterface {

    private static final String DECIMAL_PATTERN = "-?[0-9]+\\.?[0-9]*";

    private String myFunctionName;
    private Map<String, Double> myLocalVariables = new HashMap<>();
    private List<String> variableNames = new ArrayList<>();
    private String myCommand;

    private CommandBlockManager myCommandBlockManager;

    // parameter 0 is the list of variables
    // parameter 1 is the block of commands as string

    //will change to List<Object> later. coded this way to manage flow.
    public MakeUserInstruction(String functionName, List<String> variables, String command) {
        String myFunctionName = functionName;
        variableNames.addAll(variables);
        myCommand = command;
    }

    /**
     *  When the user defined function is called, only the parameters will be given. Thus, the commandblock
     *  should be formed when the object is called, and will be called indirectly with only the values of the parameters.
     */
    public double execute(TurtleHistory turtleHistory, List<Object> parameters) {
//      String[] userVariables = parameters.get(0).toString().split(" ");
//      CommandBlockManager blockManager = new CommandBlockManager(parameters.get(1).toString(), turtleManager);

        if(parameters.size() != variableNames.size()) {
            //throw exception
        }

        for (int i=0; i<parameters.size(); i++) {
            String parameter = parameters.get(i).toString();
            System.out.println(parameter);
            if(!isThisStringDouble(parameter)) {
                //throw error.
            }
            myLocalVariables.put(variableNames.get(i), Double.parseDouble(parameter));
        }
        myCommandBlockManager = new CommandBlockManager(myCommand, turtleHistory);
        return myCommandBlockManager.executeInstructionBlock();
    }

    public String getFunctionName() {
        return myFunctionName;
    }

    private boolean isThisStringDouble(String command) {
        return Pattern.matches(DECIMAL_PATTERN, command);
    }
}
