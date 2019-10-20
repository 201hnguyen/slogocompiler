package slogo.backend.commands;

import slogo.backend.commands.control.ControlFactory;
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

    private ResourceBundle myCommandsResourceBundle = ResourceBundle.getBundle(COMMANDS_RESOURCE_PATH);
    private ResourceBundle myControlsResourceBundle = ResourceBundle.getBundle(CONTROLS_RESOURCE_PATH);
    private String myCommandBlockString;
    private ControlFactory myControlFactory;
    private CommandStacks myCommandStacks;
    private TurtleManager myTurtleManager;
    private Scanner myScanner;

    private double myLatestExecutedInstructionReturnValue;
    private boolean executedInstructions;
    private Map<String, Double> myUserDefinedVariables;
    private Map<String, String> myUserDefinedFunctions;
//    private CommandFactory myCommandFactory;

    public CommandBlockManager(String commandBlock, TurtleManager turtleManager) {
        myCommandBlockString = commandBlock;
        myTurtleManager = turtleManager;
        myCommandStacks = new CommandStacks(myTurtleManager);
        myControlFactory = new ControlFactory();
        myScanner = new Scanner(myCommandBlockString);
        System.out.println("full command string: " + myCommandBlockString);
        executeInstructionBlock();
    }

    private double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            if (myCommandsResourceBundle.containsKey(command) || Pattern.matches("-?[0-9]+\\.?[0-9]*", command)) {
                myCommandStacks.addToStack(command);
            } else if (myControlsResourceBundle.containsKey(command)) {
                List<String> commandArguments = prepareBlockCommand();
                try {
                    returnValue = myControlFactory.execute(command, commandArguments, myTurtleManager);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
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
            nextWord = myScanner.next();
        }
        String argument = builder.toString();
        arguments.add(argument);
        if (endSignaler.equals("]") && myScanner.next().equals("[")) {
            buildIndividualControlArgument(endSignaler, arguments);
        }
    }
    public boolean instructionsWereExecuted() {
        return executedInstructions;
    }

}
