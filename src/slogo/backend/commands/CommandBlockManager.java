package slogo.backend.commands;

import slogo.backend.exceptions.NeedValueOfParameterException;
import slogo.backend.exceptions.UnmatchedNumArgumentsException;
import slogo.backend.commands.control.ControlExecutor;
import slogo.backend.commands.control.controlcommands.MakeUserInstruction;
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
    private static final String CONTROLS_RESOURCE_PATH = "resources/DefinedControls";
    private static final String MAKE_USER_DEFINED_RESOURCE_PATH = "resources/setVariableCommand";

    private ResourceBundle myControlsResourceBundle = ResourceBundle.getBundle(CONTROLS_RESOURCE_PATH);
    private ResourceBundle myUserDefinedResourceBundle = ResourceBundle.getBundle(MAKE_USER_DEFINED_RESOURCE_PATH);
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
            if (myControlsResourceBundle.containsKey(command)) {
                List<Object> commandArguments = prepareBlockCommand();
                try {
                    returnValue = myControlExecutor.execute(command, commandArguments, myTurtleHistory);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else if (myUserDefinedResourceBundle.containsKey(command)) {
                //FIXME: Currently this only has make variables, I think making anything else should use control
                prepareVariable();
            } else {
                try {
                    System.out.println("BlockManager, currently passing to command tree: " + command);
                    myCommandTree.addToCommandTree(command);
                } catch (ClassNotFoundException e) {
                    //FIXME
                }
            }
        }
        return returnValue;
    }

    private void prepareVariable() {
        String variable = myScanner.next().replace(":", "");
        try {
            myCommandTree.addToCommandTree(myScanner.next());
//            while (myCommandTree.stillNeedCommand()) { //FIXME: this can be uncommented once boolean for down to last double is made
//                myCommandTree.addToCommandTree(myScanner.next());
//            }
            while (! myScanner.peek().equals("Forward")) {
                myCommandTree.addToCommandTree(myScanner.next());
            }
            System.out.println("adding to map:" + variable + myCommandTree.getLastDouble());
            myUserDefinedVariables.put(variable, myCommandTree.getLastDouble());
            for (String key : myUserDefinedVariables.keySet()) {
                System.out.println("user map: " + key + ":" + myUserDefinedVariables.get(key));
            }
        } catch (UnmatchedNumArgumentsException e) {
            e.printStackTrace(); //FIXME: Not sure why this is causing an exception
        } catch (ClassNotFoundException e) {
            //FIXME !!
        }
    }

    private List<Object> prepareBlockCommand() {
        List<Object> controlCommandArguments = new ArrayList<>();
        buildIndividualControlArgument("[", controlCommandArguments);
        buildIndividualControlArgument("]", controlCommandArguments);
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
