package slogo.backend.commands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

public class CommandStacks {

    private static final String decimalPattern = "-?[0-9]+\\.?[0-9]*";

    private Stack<String> commandStack = new Stack<>();
    private Stack<Double> numberStack = new Stack<>();
    Queue<Double> movementQueue = new LinkedList<>();

    public CommandStacks() {

    }

    public void addCommand(String command) {
        if(isThisStringDouble(command)) {
            numberStack.push(Double.parseDouble(command));
        } else {
            commandStack.push(command);
        }
    }

    private boolean isThisStringDouble(String command) {
        return Pattern.matches(decimalPattern, command);
    }


}
