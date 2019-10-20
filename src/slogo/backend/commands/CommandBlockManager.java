package slogo.backend.commands;

import slogo.backend.NeedValueOfParameterException;
import slogo.backend.commands.control.ControlFactory;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleManager;

import java.util.*;
import java.util.regex.Pattern;

/**
 * This class executes sequential blocks of instructions (overhead class for entire block of instruction that user enters;
 * also used in control instructions to break them down); should manage stacks of instructions and parameters, etc.;
 * should have a factory object to make instructions, etc.
 * This class should now do a lot of the work that interpreter was doing before in terms of keeping charge of stacks,
 * user functions and variables, etc.
 */

public class CommandBlockManager {
    private static final String COMMANDS_RESOURCE_PATH = "resources/DefinedCommands";
    private static final String CONTROLS_RESOURCE_PATH = "resources/DefinedControls";
    private static final String MAKE_USER_DEFINED_RESOURCE_PATH = "resources/DefinedUserRelatedCommands";

    private ResourceBundle myCommandsResourceBundle = ResourceBundle.getBundle(COMMANDS_RESOURCE_PATH);
    private ResourceBundle myControlsResourceBundle = ResourceBundle.getBundle(CONTROLS_RESOURCE_PATH);
    private ResourceBundle myUserDefinedResourceBundle = ResourceBundle.getBundle(MAKE_USER_DEFINED_RESOURCE_PATH);
    private String myCommandBlockString;
    private ControlFactory myControlFactory;
    private CommandTree myCommandTree;
    private TurtleManager myTurtleManager;
    private PeekableScanner myScanner;
    private Map<String, Double> myUserDefinedVariables;
    private Map<String, String> myUserDefinedFunctions;

    public CommandBlockManager(String commandBlock, TurtleManager turtleManager) {
        myCommandBlockString = commandBlock;
        myTurtleManager = turtleManager;
        myCommandTree = new CommandTree(myTurtleManager);
        myControlFactory = new ControlFactory();
        myScanner = new PeekableScanner(myCommandBlockString);
        System.out.println("Full command string of this block: " + myCommandBlockString);
    }

    public double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            if (myCommandsResourceBundle.containsKey(command) || Pattern.matches("-?[0-9]+\\.?[0-9]*", command)) {
                try {
                    System.out.println("BlockManager, currently passing to command tree: " + command);
                    myCommandTree.addToCommandTree(command);
                } catch (NeedValueOfParameterException e) {
                    //TODO: put parameter
                }
            } else if (myControlsResourceBundle.containsKey(command)) {
                List<String> commandArguments = prepareBlockCommand();
                try {
                    returnValue = myControlFactory.execute(command, commandArguments, myTurtleManager);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else if (myUserDefinedResourceBundle.containsKey(command)) {
                System.out.println("Command: " + command);
            } else {

            }
        }
        return returnValue;
    }

    private List<String> prepareBlockCommand() {
        List<String> controlCommandArguments = new ArrayList<>();
        buildIndividualControlArgument("[", controlCommandArguments);
        buildIndividualControlArgument("]", controlCommandArguments);
        return controlCommandArguments;
    }

    private void buildIndividualControlArgument(String endSignaler, List<String> arguments) {
        StringBuilder builder = new StringBuilder();
        String nextWord = myScanner.next();
        while (!nextWord.equals(endSignaler)) {
            builder.append(nextWord + " ");
            try {
                nextWord = myScanner.next();
            } catch (NullPointerException e) {

            }
        }
        String argument = builder.toString();
        arguments.add(argument);
        try {
            if (endSignaler.equals("]") && myScanner.peek().equals("[")) {
                myScanner.next();
                buildIndividualControlArgument(endSignaler, arguments);
            }
        } catch (NullPointerException e) {

        }

    }
}
