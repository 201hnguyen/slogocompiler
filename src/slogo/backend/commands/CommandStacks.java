package slogo.backend.commands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class CommandStacks {

    private final String decimalPattern = "-?[0-9]+\\.?[0-9]*";

    private Stack<String> commandStack = new Stack<>();
    private Stack<Double> numberStack = new Stack<>();

    public void addToStack(String command) {
        if(isThisStringDouble(command)) {
            numberStack.add(Double.parseDouble(command));
        } else {
            commandStack.add(command);
        }
        handleStacks();
    }

    private boolean isThisStringDouble(String command) {
        return Pattern.matches(decimalPattern, command);
    }

    private void handleStacks() {

    }
}
