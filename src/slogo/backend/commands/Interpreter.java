package slogo.backend.commands;

import slogo.backend.utils.CommandTreeNode;
import slogo.backend.utils.Movement;

import java.util.*;

public class Interpreter {
    private CommandStructurer myCommandStructurer;
    private Queue<Movement> movementQueue;
    Map<String, Double> userVariables;
    Map<String, CommandStructurer> userDefinedFunctions;
    Stack<String> commandStack;
    Stack<Double> numberStack;

    public Interpreter() {
        commandStack = new Stack<>();
        numberStack = new Stack<>();
    }

    public void setCommands(List<String> commands) {
        myCommandStructurer = new CommandStructurer(commands);
    }

    public Movement getMovement() {
        return movementQueue.poll();
    }

    public boolean hasNextMovement() {
        return !movementQueue.isEmpty();
    }

    public Map<String, Double> getUserVariables() {
        Map<String, Double> map = new HashMap<>();
        map.putAll(userVariables);
        return map;
    }
}
