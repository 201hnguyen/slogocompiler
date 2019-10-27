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
    private static final String MOVEMENT_COMMANDS_RESOURCE_PATH = "resources/DefinedMovementCommands";
    private static final ResourceBundle CONTROLS_RESOURCE_BUNDLE = ResourceBundle.getBundle(BLOCK_CONTROLS_RESOURCE_PATH);
    private static final ResourceBundle USER_DEFINED_RESOURCE_BUNDLE = ResourceBundle.getBundle(USER_DEFINED_RESOURCE_PATH);
    private static final ResourceBundle MOVEMENT_COMMANDS_RESOURCE_BUNDLE = ResourceBundle.getBundle(MOVEMENT_COMMANDS_RESOURCE_PATH);
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
    private Map<String,List<Object>> myAccessibleUserDefinedFunctions;
    private List<Integer> myActiveTurtles;
    private List<String> myCommandsToReRun;

    public CommandBlockManager(String commandBlock, TurtleHistory turtleHistory, List<Map<String,Double>> higherScopeVariables, Map<String, List<Object>> definedFunctions) {
        myCommandBlockString = commandBlock;
        myTurtleHistory = turtleHistory;
        myCommandTree = new CommandTree(myTurtleHistory);
        myControlExecutor = new ControlExecutor();
        myScanner = new PeekableScanner(myCommandBlockString);
        myLocalUserDefinedVariables = new HashMap<>();
        myAccessibleVariables = new ArrayList<>();
        myAccessibleVariables.addAll(higherScopeVariables);
        myAccessibleVariables.add(myLocalUserDefinedVariables);
        myAccessibleUserDefinedFunctions = new HashMap<>();
        myAccessibleUserDefinedFunctions.putAll(definedFunctions);
        myActiveTurtles = new ArrayList<>() {{ add(1); }};
        myCommandsToReRun = new ArrayList<>();
        System.out.println("Full command string of this block: " + myCommandBlockString);
    }

    public double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            command = checkAndInputUserVariable(command, myAccessibleVariables);
            if (CONTROLS_RESOURCE_BUNDLE.containsKey(command)) {
                List<Object> commandArguments;
                if (USER_DEFINED_RESOURCE_BUNDLE.containsKey(command)) {
                    commandArguments = new ArrayList<>() {{
                        add(myScanner);
                    }};
                } else if (command.equals("MakeUserInstruction")) {
                    commandArguments = new ArrayList<>();
                    String methodName = myScanner.next();
                    commandArguments.add(methodName);
                    commandArguments.add(prepareBlockCommand());
                    commandArguments.add(myAccessibleUserDefinedFunctions);
                } else {
                    commandArguments = prepareBlockCommand();
                }
                try {
                    returnValue = myControlExecutor.execute(command, commandArguments, myTurtleHistory, myAccessibleVariables, myAccessibleUserDefinedFunctions);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else if (myAccessibleUserDefinedFunctions.containsKey(command)) {
                List<Object> commandArguments = prepareUserDefinedFunction(command);
                try {
                    myControlExecutor.execute("UserDefined", commandArguments, myTurtleHistory, myAccessibleVariables, myAccessibleUserDefinedFunctions);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); //FIXME
                }
            } else {
                if (MOVEMENT_COMMANDS_RESOURCE_BUNDLE.containsKey(command)) {

                }



                try {
                    myCommandTree.addToCommandTree(command);

                } catch (ClassNotFoundException e) {
                    //FIXME
                }
            }

            if (myCommandTree.onlyNumberLeft()) {
                try {
                    returnValue = myCommandTree.getLastDouble();
                } catch (UnmatchedNumArgumentsException e) {
                    e.printStackTrace(); //FIXME
                }
            }
        }
        return returnValue;
    }

    private List<Object> prepareUserDefinedFunction(String command) {
        List<Object> commandArguments = new ArrayList<>();
        List<Double> numericalArgumentForMethod = new ArrayList<>();
        int parametersNeeded = ((Map<String, Double>) myAccessibleUserDefinedFunctions.get(command).get(0)).size();
        System.out.println("test parameters needed: " + parametersNeeded);

        for (int i=0; i<parametersNeeded; i++) {
            String argument = myScanner.next();
            argument = checkAndInputUserVariable(argument, myAccessibleVariables);
            try {
                myCommandTree.addToCommandTree(argument);
                while (!myCommandTree.onlyNumberLeft()) {
                    argument = myScanner.next();
                    argument = checkAndInputUserVariable(argument, myAccessibleVariables);
                    myCommandTree.addToCommandTree(argument);
                }
                numericalArgumentForMethod.add(myCommandTree.getLastDouble());
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); //FIXME
            } catch (UnmatchedNumArgumentsException e) {
                e.printStackTrace(); //FIXME
            }
        }
        commandArguments.add(numericalArgumentForMethod);
        commandArguments.addAll(myAccessibleUserDefinedFunctions.get(command));
        return commandArguments;
    }

    public static String checkAndInputUserVariable(String command, List<Map<String, Double>> accessibleVariables) {
        if (command.charAt(0) == USER_DEFINED_SIGNAL) {
            for (Map<String, Double> variableMap : accessibleVariables) {
                if (variableMap.containsKey(command)) {
                    return variableMap.get(command).toString();
                }
            }
        }
        return command;
    }

    private List<Object> prepareBlockCommand() {
        List<Object> controlCommandArguments = new ArrayList<>();
        if (! myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
            buildIndividualControlArgument(NON_BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        } else {
            myScanner.next();
        }
        buildIndividualControlArgument(BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        return controlCommandArguments;
    }

    private void buildIndividualControlArgument(String endSignaler, List<Object> arguments) {
        StringBuilder builder = new StringBuilder();
        String nextWord = myScanner.next();
        int endSignalersNeeded = 1;
        nextWord = checkAndInputUserVariable(nextWord, myAccessibleVariables);
        while (endSignalersNeeded != 0) {
            builder.append(nextWord + " ");
            if (myScanner.hasNext()) {
                nextWord = myScanner.next();
                if (endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && nextWord.equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
                    endSignalersNeeded++;
                } else if (endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && nextWord.equals(BLOCK_ARGUMENT_END_SIGNAL) ||
                        endSignaler.equals(NON_BLOCK_ARGUMENT_END_SIGNAL) && nextWord.equals(NON_BLOCK_ARGUMENT_END_SIGNAL)) {
                    endSignalersNeeded--;
                }
            }
        }
        String argument = builder.toString();
        arguments.add(argument);
        try {
            if (endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
                myScanner.next();
                buildIndividualControlArgument(endSignaler, arguments);
            }
        } catch (NullPointerException e) {
                //fixme
        }

    }
}
