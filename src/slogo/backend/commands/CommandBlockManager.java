package slogo.backend.commands;

import slogo.backend.commands.control.ControlExecutor;
import slogo.backend.commands.control.controlcommands.MakeUserInstruction;
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
    private static final String MAKE_VARIABLE_COMMAND = "MakeVariable";
    private static final String NON_BLOCK_ARGUMENT_END_SIGNAL = "[";
    private static final String BLOCK_ARGUMENT_END_SIGNAL = "]";
    private static final String BLOCK_ARGUMENT_BEGIN_SIGNAL = "[";

    private ResourceBundle myBlockControlsResourceBundle = ResourceBundle.getBundle(BLOCK_CONTROLS_RESOURCE_PATH);
    private String myCommandBlockString;
    private ControlExecutor myControlExecutor;
    private CommandTree myCommandTree;
    private TurtleHistory myTurtleHistory;
    private PeekableScanner myScanner;
    private Map<String, Double> myUserDefinedVariables;
    private Set<MakeUserInstruction> myUserDefinedFunctions;

    public CommandBlockManager(String commandBlock, TurtleHistory turtleHistory) {
        myCommandBlockString = commandBlock;
        myTurtleHistory = turtleHistory;
        myCommandTree = new CommandTree(myTurtleHistory);
        myControlExecutor = new ControlExecutor();
        myScanner = new PeekableScanner(myCommandBlockString);
        System.out.println("Full command string of this block: " + myCommandBlockString);
    }

    public double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            if (myBlockControlsResourceBundle.containsKey(command)) {
                List<Object> commandArguments = prepareBlockCommand();
                try {
                    returnValue = myControlExecutor.execute(command, commandArguments, myTurtleHistory);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else if (command.equals(MAKE_VARIABLE_COMMAND)) {
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

    private void addUserDefinedVariable() {
        String variable = myScanner.next().replace(":", "");
        try {
            myCommandTree.addToCommandTree(myScanner.next());
            while (!myCommandTree.onlyNumberLeft()) {
                myCommandTree.addToCommandTree(myScanner.next());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //FIXME
        }

        try {
            double y = myCommandTree.getLastDouble();
            System.out.println("last double: " + y);
//            myUserDefinedVariables.put(variable, y);
        } catch (UnmatchedNumArgumentsException e) {
            e.printStackTrace(); //FIXME
        }
        for (String key : myUserDefinedVariables.keySet()) {
            System.out.println("User defined:" + key + " : " + myUserDefinedVariables.get(key));
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
        while (!nextWord.equals(endSignaler)) {
            builder.append(nextWord + " ");
            try {
                nextWord = myScanner.next();
            } catch (NullPointerException e) {

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
