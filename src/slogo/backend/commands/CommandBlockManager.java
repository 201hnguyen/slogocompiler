package slogo.backend.commands;

import slogo.backend.commands.control.ControlExecutor;
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
    private static final String MOVEMENT_COMMANDS_RESOURCE_PATH = "resources/DefinedMovementCommands";
    private static final String DEFINE_USER_VARIABLE_COMMAND = "MakeVariable";
    private static final String DEFINE_USER_INSTRUCTION_COMMAND = "MakeUserInstruction";
    private static final String EXECUTE_USER_DEFINED_COMMAND = "UserDefined";
    private static final ResourceBundle CONTROLS_RESOURCE_BUNDLE = ResourceBundle.getBundle(BLOCK_CONTROLS_RESOURCE_PATH);
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
        myActiveTurtles = myTurtleHistory.getActiveTurtles();
        System.out.println("Full command string of this block: " + myCommandBlockString);
    }

    public double executeInstructionBlock() {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            command = checkAndInputUserVariable(command, myAccessibleVariables);
            if (CONTROLS_RESOURCE_BUNDLE.containsKey(command)) {
                returnValue = buildAndExecuteControlCommand(command);
            } else if (myAccessibleUserDefinedFunctions.containsKey(command)) {
                returnValue = buildAndExecuteUserDefinedCommand(command);
            } else {
                returnValue = buildAndExecuteBasicCommand(command);
                if (myCommandTree.onlyNumberLeft()) {
                    returnValue = myCommandTree.getLastDouble();
                }
            }
        }
        return returnValue;
    }

    private double buildAndExecuteControlCommand(String command) {
        double returnValue = 0;
        List<Object> commandArguments;
        if (command.equals(DEFINE_USER_VARIABLE_COMMAND)) {
            commandArguments = new ArrayList<>() {{
                add(myScanner);
            }};
        } else if (command.equals(DEFINE_USER_INSTRUCTION_COMMAND)) {
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
        return returnValue;
    }

    private double buildAndExecuteUserDefinedCommand(String command) {
        double returnValue = 0;
        List<Object> commandArguments = prepareUserDefinedFunction(command);
        try {
            returnValue = myControlExecutor.execute(EXECUTE_USER_DEFINED_COMMAND, commandArguments, myTurtleHistory, myAccessibleVariables, myAccessibleUserDefinedFunctions);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //FIXME
        }
        return returnValue;
    }

    private double buildAndExecuteBasicCommand(String command) {
        double returnValue = 0;
        try {
            if (MOVEMENT_COMMANDS_RESOURCE_BUNDLE.containsKey(command)) {
                returnValue = rerunMovementCommands(command);
            } else {
                myCommandTree.addToCommandTree(command);
            }
        } catch (ClassNotFoundException e) {
            //FIXME
        }
        return returnValue;
    }

    private double rerunMovementCommands(String command) throws ClassNotFoundException {
        List<String> commandsToRerun = new ArrayList<>();
        runCommandForFirstTurtle(command, commandsToRerun);
        System.out.println(myActiveTurtles.size());
        double returnVal = runCommandForOtherTurtles(commandsToRerun);
        return returnVal;
    }

    private void runCommandForFirstTurtle(String command, List<String> commandsToRerun) throws ClassNotFoundException {
        int firstTurtleID;
        if (myActiveTurtles.size()>0) {
            firstTurtleID = myActiveTurtles.get(0);
        } else {
            firstTurtleID = 1;
        }
        CommandTree testCommandtree = new CommandTree(myTurtleHistory);
        testCommandtree.setTurtleID(firstTurtleID);
        commandsToRerun.add(command);
        while (!testCommandtree.onlyNumberLeft()) {
            command = myScanner.next();
            command = checkAndInputUserVariable(command, myAccessibleVariables);
            commandsToRerun.add(command);
            testCommandtree.addToCommandTree(command);
        }
    }

    private double runCommandForOtherTurtles(List<String> commandsToRerun) throws ClassNotFoundException {
        double returnVal = 0;
        for (int i=0; i<myActiveTurtles.size(); i++) {
            StringBuilder builder = new StringBuilder();
            for (String c : commandsToRerun) {
                builder.append(c);
            }
            String s = builder.toString();
            System.out.println("This is what the turtle executes: " + "for turtle # " + myActiveTurtles.get(i) + " executing " + s);

            CommandTree repeatCommandTree = new CommandTree(myTurtleHistory);
            repeatCommandTree.setTurtleID(myActiveTurtles.get(i));
            for (String commandToRerun : commandsToRerun) {
                repeatCommandTree.addToCommandTree(commandToRerun);
            }

            if (i == myActiveTurtles.size() - 1 && repeatCommandTree.onlyNumberLeft()) {
                returnVal = repeatCommandTree.getLastDouble();
            }
        }
        return returnVal;
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
                if (myCommandTree.onlyNumberLeft()) {
                    numericalArgumentForMethod.add(myCommandTree.getLastDouble());
                }
            } catch (ClassNotFoundException e) {
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
        addFirstArgument(endSignaler, arguments);
        checkAndAddAdditionalArguments(endSignaler, arguments);
    }

    private void addFirstArgument(String endSignaler, List<Object> arguments) {
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
    }

    private void checkAndAddAdditionalArguments(String endSignaler, List<Object> arguments) {
        if (myScanner.hasNext()) {
            if (endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
                myScanner.next();
                buildIndividualControlArgument(endSignaler, arguments);
            }
        }
    }
}
