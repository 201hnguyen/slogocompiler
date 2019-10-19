package slogo.backend.commands;

import slogo.backend.commands.basic.CommandFactory;
import slogo.backend.utils.TurtleManager;

import java.util.*;
import java.util.regex.Pattern;


public class CommandStacks {

    private final String decimalPattern = "-?[0-9]+\\.?[0-9]*";

    private Stack<String> commandStack = new Stack<>();
    private Stack<Double> numberStack = new Stack<>();
    private Map<String, Double> userDefinedVariables;

    private TurtleManager myTurtleManager;
    private CommandFactory myCommandFactory;
    private String turtleID = "";

    public CommandStacks(TurtleManager turtleManager) {
        myTurtleManager = turtleManager;
        myCommandFactory = new CommandFactory(myTurtleManager);
    }

    public void addToStack(String command) {
        if(isThisStringDouble(command)) {
            numberStack.add(Double.parseDouble(command));
        } else {
            commandStack.add(command);
        }
        checkBasicCommands();
    }

    private void checkBasicCommands() {
        if(commandStack.isEmpty()) {
            if(!numberStack.isEmpty()) {
               //throw error or return something on the screen
                return;
            }
        }
        String command = commandStack.pop();
        try {
            List<Double> parameters = new ArrayList<>();
            int parameterCount = myCommandFactory.getNumParameter(command);
            if(parameterCount > numberStack.size()) {
                return;
            }
            for(int i=0; i<myCommandFactory.getNumParameter(command); i++) {
                parameters.add(numberStack.pop());
            }
            double value = myCommandFactory.execute(command, turtleID, parameters);
            numberStack.push(value);
        } catch (ClassNotFoundException e) {
            commandStack.push(command);
            checkUserDefinedVariables();
        }
    }

    private void checkUserDefinedVariables() {
        String variable = commandStack.pop();
        if(userDefinedVariables.containsKey(variable)) {
            numberStack.push(userDefinedVariables.get(variable));
        } else {
            //
        }
    }

    private boolean isThisStringDouble(String command) {
        return Pattern.matches(decimalPattern, command);
    }


}
