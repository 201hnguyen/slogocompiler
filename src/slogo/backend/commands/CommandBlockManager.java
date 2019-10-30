package slogo.backend.commands;

import slogo.backend.commands.control.ControlExecutor;
import slogo.backend.exceptions.BackendException;
import slogo.backend.utils.CommandTree;
import slogo.backend.utils.TurtleHistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

    public double executeInstructionBlock() throws BackendException {
        double returnValue = 0;
        while (myScanner.hasNext()) {
            String command = myScanner.next();
            command = checkAndInputUserVariable(command, myAccessibleVariables);
            System.out.println(command + " will now be put somewhere");
            if (CONTROLS_RESOURCE_BUNDLE.containsKey(command)) {
                returnValue = buildAndExecuteControlCommand(command);
            } else if (myAccessibleUserDefinedFunctions.containsKey(command)) {
                returnValue = buildAndExecuteUserDefinedCommand(command);
            } else {
                returnValue = buildAndExecuteBasicCommand(command);
            }
        }
        if (myCommandTree.onlyNumberLeft()) {
            returnValue = myCommandTree.getLastDouble();
        }
        return returnValue;
    }

    public Map<String, List<Object>> getUserDefinedFunctions() {
        return Map.copyOf(myAccessibleUserDefinedFunctions);
    }

    public List<String> getUserDefinedFunctionsAsStrings() {
        List<String> userDefinedFunctionsAsString = new ArrayList<>();
        for (Map.Entry<String, List<Object>> entry : myAccessibleUserDefinedFunctions.entrySet()) {
            StringBuilder functionAsString = new StringBuilder();
            functionAsString.append(entry.getKey() + " ");
            Map<String, Double> methodParametersMap = (Map<String, Double>) entry.getValue().get(0);
            for (String key : methodParametersMap.keySet()) {
                functionAsString.append(key + " ");
            }
            userDefinedFunctionsAsString.add(functionAsString.toString());
        }
        return userDefinedFunctionsAsString;
    }

    private double buildAndExecuteControlCommand(String command) throws BackendException {
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
        returnValue = myControlExecutor.execute(command, commandArguments, myTurtleHistory, myAccessibleVariables, myAccessibleUserDefinedFunctions);
        myCommandTree.addToCommandTree(returnValue+"");
        return returnValue;
    }

    private double buildAndExecuteUserDefinedCommand(String command) throws BackendException {
        double returnValue = 0;
        List<Object> commandArguments = prepareUserDefinedFunction(command);
        returnValue = myControlExecutor.execute(EXECUTE_USER_DEFINED_COMMAND, commandArguments, myTurtleHistory, myAccessibleVariables, myAccessibleUserDefinedFunctions);
        myCommandTree.addToCommandTree(returnValue+"");
        return returnValue;
    }

    private double buildAndExecuteBasicCommand(String command) throws BackendException {
        double returnValue = 0;
        System.out.println(command + " is inside the tree now");
        if (MOVEMENT_COMMANDS_RESOURCE_BUNDLE.containsKey(command)) {
            command = rerunMovementCommands(command) + "";
        }
        myCommandTree.addToCommandTree(command);
        return returnValue;
    }

    private double rerunMovementCommands(String command) throws BackendException {
        int index = myScanner.getIndex() - 1;
        double returnVal = 0;
        myActiveTurtles.clear();
        myActiveTurtles.addAll(myTurtleHistory.getActiveTurtles());
        for(int i=0; i<myActiveTurtles.size(); i++) {
            System.out.println("This is what the turtle executes: " + "for turtle # " + myActiveTurtles.get(i) + " executing ");
            myScanner.goToIndex(index);

            CommandTree repeatCommandTree = new CommandTree(myTurtleHistory);
            repeatCommandTree.setTurtleID(myActiveTurtles.get(i));

            while (!repeatCommandTree.onlyNumberLeft() && myScanner.hasNext()) {
                command = myScanner.next();
                command = checkAndInputUserVariable(command, myAccessibleVariables);
                repeatCommandTree.addToCommandTree(command);
            }
            returnVal = repeatCommandTree.getLastDouble();
        }
        myTurtleHistory.toNextTurn(mergeAllAccessibleVariables());
        return returnVal;
    }

    private List<Object> prepareUserDefinedFunction(String command) throws BackendException {
        List<Object> commandArguments = new ArrayList<>();
        List<Double> numericalArgumentForMethod = new ArrayList<>();
        Map<String, Double> parameters = (Map<String, Double>) myAccessibleUserDefinedFunctions.get(command).get(0);
        if (parameters.size() == 1 && parameters.keySet().contains("")) {
            parameters.remove("");
        }
        int parametersNeeded = parameters.size();

        for (int i=0; i<parametersNeeded; i++) {
            String argument = myScanner.next();
            argument = checkAndInputUserVariable(argument, myAccessibleVariables);
            myCommandTree.addToCommandTree(argument);
            while (!myCommandTree.onlyNumberLeft()) {
                argument = myScanner.next();
                argument = checkAndInputUserVariable(argument, myAccessibleVariables);
                myCommandTree.addToCommandTree(argument);
            }
            if (myCommandTree.onlyNumberLeft()) {
                numericalArgumentForMethod.add(myCommandTree.getLastDouble());
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
            Map<String, Double> mostLocalMap = accessibleVariables.get(accessibleVariables.size()-1);
            mostLocalMap.put(command, 0.0);
            return mostLocalMap.get(command).toString();
        }
        return command;
    }

    public Map<String, Double> getVariables() {
        Map<String, Double> variables = new LinkedHashMap<>();
        variables.putAll(myLocalUserDefinedVariables);
        return variables;
    }

    private List<Object> prepareBlockCommand() throws BackendException {
        List<Object> controlCommandArguments = new ArrayList<>();
        if (! myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
            buildIndividualControlArgument(NON_BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        } else {
            myScanner.next();
        }
        buildIndividualControlArgument(BLOCK_ARGUMENT_END_SIGNAL, controlCommandArguments);
        return controlCommandArguments;
    }

    private void buildIndividualControlArgument(String endSignaler, List<Object> arguments) throws BackendException {
        addFirstArgument(endSignaler, arguments);
        checkAndAddAdditionalArguments(endSignaler, arguments);
    }

    private void addFirstArgument(String endSignaler, List<Object> arguments) throws BackendException {
        StringBuilder builder = new StringBuilder();
        String nextWord = myScanner.next();
        int endSignalersNeeded = 1;
        if (! nextWord.equals(BLOCK_ARGUMENT_END_SIGNAL)) {
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
                } else {
                    throw new BackendException("Unmatched number of brackets");
                }
            }
        }
        String argument = builder.toString();
        arguments.add(argument);
    }

    private Map<String, Double> mergeAllAccessibleVariables() {
        Map<String, Double> mergedMap = new LinkedHashMap<>();
        for (Map<String, Double> variableMap : myAccessibleVariables) {
            mergedMap.putAll(variableMap);
        }
        return mergedMap;
    }

    private void checkAndAddAdditionalArguments(String endSignaler, List<Object> arguments) throws BackendException {
        if (myScanner.hasNext() && endSignaler.equals(BLOCK_ARGUMENT_END_SIGNAL) && myScanner.peek().equals(BLOCK_ARGUMENT_BEGIN_SIGNAL)) {
                myScanner.next();
                buildIndividualControlArgument(endSignaler, arguments);
        }
    }
}
