package slogo.backend.commands;

import slogo.backend.commands.control.ControlExecutor;
//import slogo.backend.commands.control.controlcommands.MakeUserInstruction;
import slogo.backend.exceptions.UnmatchedNumArgumentsException;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.*;

/**
 * This class executes sequential blocks of instructions (overhead class for entire block of instruction that user enters;
 * also used in control instructions to break them down); should manage stacks of instructions and parameters, etc.;
 * should have a factory object to make instructions, etc.
 * This class should now do a lot of the work that interpreter was doing before in terms of keeping charge of stacks,
 * user functions and variables, etc.
 */

public class CommandBlockManager {
    private static final String BLOCK_CONTROLS_RESOURCE_PATH = "resources/DefinedControls";
    private static final String USER_DEFINED_RESOURCE_PATH = "resources/UserDefinedVariables";
    private static final ResourceBundle CONTROLS_RESOURCE_BUNDLE = ResourceBundle.getBundle(BLOCK_CONTROLS_RESOURCE_PATH);
    private static final ResourceBundle USER_DEFINED_RESOURCE_BUNDLE = ResourceBundle.getBundle(USER_DEFINED_RESOURCE_PATH);
    private static final String NON_BLOCK_ARGUMENT_END_SIGNAL = "[";
    private static final String BLOCK_ARGUMENT_END_SIGNAL = "]";
    private static final String BLOCK_ARGUMENT_BEGIN_SIGNAL = "[";
    private static final char USER_DEFINED_SIGNAL = ':';

    private String myCommandBlockString;
    private ControlExecutor myControlExecutor;
    private CommandTree myCommandTree;
    private TurtleHistory myTurtleHistory;
    private PeekableScanner myScanner;
    private Map<String, Double> myLocalUserDefinedVariables;
    private List<Map<String, Double>> myAccessibleVariables;

    public CommandBlockManager(String commandBlock, TurtleHistory turtleHistory, List<Map<String,Double>> higherScopeVariables) {
        myCommandBlockString = commandBlock;
        myTurtleHistory = turtleHistory;
        myCommandTree = new CommandTree(myTurtleHistory);
        myControlExecutor = new ControlExecutor();
        myScanner = new PeekableScanner(myCommandBlockString);
        myLocalUserDefinedVariables = new HashMap<>();
        myAccessibleVariables = new ArrayList<>();
        myAccessibleVariables.addAll(higherScopeVariables);
        myAccessibleVariables.add(myLocalUserDefinedVariables);
        System.out.println("Full command string of this block: " + myCommandBlockString);
    }

    public double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            command = checkAndInputUserVariable(command);
            if (CONTROLS_RESOURCE_BUNDLE.containsKey(command)) {
                List<Object> commandArguments = prepareBlockCommand();
                try {
                    returnValue = myControlExecutor.execute(command, commandArguments, myTurtleHistory, myAccessibleVariables);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else if (USER_DEFINED_RESOURCE_BUNDLE.containsKey(command)) {
                addUserDefinedVariable();
            } else {
                try {
                    myCommandTree.addToCommandTree(command);
                } catch (ClassNotFoundException e) {
                    //FIXME
                }
            }
        }
        return returnValue;
    }

    private String checkAndInputUserVariable(String command) {
        if (command.charAt(0) == USER_DEFINED_SIGNAL) {
            for (Map<String, Double> variableMap : myAccessibleVariables) {
                if (variableMap.containsKey(command)) {
                    return variableMap.get(command).toString();
                }
            }
        }
        return command;
    }

    private void addUserDefinedVariable() {
        String variable = myScanner.next();
        try {
            myCommandTree.addToCommandTree(myScanner.next());
            while (!myCommandTree.onlyNumberLeft()) {
                myCommandTree.addToCommandTree(myScanner.next());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //FIXME
        }
        try {
            for (Map<String, Double> variableMap : myAccessibleVariables) {
                if (variableMap.containsKey(variable)) {
                    variableMap.put(variable, myCommandTree.getLastDouble());
                    return;
                }
            }
            myLocalUserDefinedVariables.put(variable, myCommandTree.getLastDouble());
        } catch (UnmatchedNumArgumentsException e) {
            e.printStackTrace(); //FIXME
        }
        for (String key : myLocalUserDefinedVariables.keySet()) {
            System.out.println("User defined:" + key + " : " + myLocalUserDefinedVariables.get(key));
        }
    }

    private List<Object> prepareBlockCommand() {
        List<Object> controlCommandArguments = new ArrayList<>();
        buildIndividualControlArgument(NON_BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        buildIndividualControlArgument(BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        return controlCommandArguments;
    }

    private void buildIndividualControlArgument(String endSignaler, List<Object> arguments) {
        StringBuilder builder = new StringBuilder();
        String nextWord = myScanner.next();
        nextWord = checkAndInputUserVariable(nextWord);
        while (!nextWord.equals(endSignaler)) {
            builder.append(nextWord + " ");
            try {
                nextWord = myScanner.next();
            } catch (NullPointerException e) {
                //FIXME
            }
        }
        if (nextWord.equals(BLOCK_ARGUMENT_END_SIGNAL)) {
            builder.append(nextWord);
        }
        String argument = builder.toString();
        arguments.add(argument);
        try {
            if (endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
                myScanner.next();
                buildIndividualControlArgument(endSignaler, arguments);
            }
        } catch (NullPointerException e) {

        }

    }
}
