package slogo.backend.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * This class executes sequential blocks of instructions (overhead class for entire block of instruction that user enters;
 * also used in control instructions to break them down); should manage stacks of instructions and parameters, etc.;
 * should have a factory object to make instructions, etc.
 * This class should now do a lot of the work that interpreter was doing before in terms of keeping charge of stacks,
 * user functions and variables, etc.
 */

public class CommandBlockManager {
    private String myCommandBlockAsString;
    private double myLatestExecutedInstructionReturnValue;
    private boolean executedInstructions;
    private Map<String, Double> myUserDefinedVariables;
    private Map<String, String> myUserDefinedFunctions;
    private Stack<String> myCommandStack;
    private Stack<Double> myParametersStack;
    private CommandFactory myCommandFactory;

    public CommandBlockManager(String commandBlock) {
        myCommandBlockAsString = commandBlock;
        executedInstructions = false;
        myUserDefinedFunctions = new HashMap<>();
        myUserDefinedVariables = new HashMap<>();
        myCommandStack = new Stack<>();
        myParametersStack = new Stack<>();
        myCommandFactory = new CommandFactory();
    }

    public void executeInstructionBlock() {
        myCommandFactory.makeCommand(myCommandStack.pop());

        System.out.println("Here is where these instructions (e.g. " + myCommandBlockAsString + ") should execute.");
        executedInstructions = true;
    }

    public boolean instructionsWereExecuted() {
        return executedInstructions;
    }

    public double getLatestExecutedInstructionReturnValue() {
        return myLatestExecutedInstructionReturnValue;
    }

    public Map<String, Double> getUserVariables() {
        return Map.copyOf(myUserDefinedVariables);
    }

    public Map<String, String> getUserDefinedFunctions() {
        return Map.copyOf(myUserDefinedFunctions);
    }

}
