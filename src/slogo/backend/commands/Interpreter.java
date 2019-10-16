package slogo.backend.commands;

import slogo.backend.utils.Movement;

import java.util.*;

public class Interpreter {
    private CommandStructurer myCommandStructurer;
    private Queue<Movement> movementQueue;
    private Map<String, Double> userVariables;
    private Map<String, CommandStructurer> userDefinedFunctions;
    private Stack<String> commandStack;
    private Stack<Double> numberStack;

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
