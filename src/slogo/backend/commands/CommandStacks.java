package slogo.backend.commands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

//TODO: Let's talk about this; I think now that we aren't doing the movement queue anymore, this is not completely needed;
// I think it's just better to have our two stacks directly in the CommandBlockManager because this seems like just
// unnecessary abstraction that doesn't really serve a purpose
public class CommandStacks {

    private final String decimalPattern = "-?[0-9]+\\.?[0-9]*";

    private Stack<String> commandStack = new Stack<>();
    private Stack<Double> numberStack = new Stack<>();

    public CommandStacks() {

    }

    public void addToStack(String command) {
        if(isThisStringDouble(command)) {
            numberStack.add(Double.parseDouble(command));
        } else {
            commandStack.add(command);
        }
    }

    private boolean isThisStringDouble(String command) {
        return Pattern.matches(decimalPattern, command);
    }


}
