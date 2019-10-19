//TODO: Let's deprecate this class; I'm still keeping it here so that we can
// talk before making final decision, but basically all functionality is just
// transferred to CommandBlockManager, which does everything this class does
// and a few more things to actually manage commands.

//
//
//package slogo.backend.commands;
//
//import slogo.backend.utils.Movement;
//
//import java.util.*;
//
//public class Interpreter {
//    private Queue<Movement> movementQueue;
//    private Map<String, Double> userVariables;
//    private Stack<String> commandStack;
//    private Stack<Double> numberStack;
//
//    public Interpreter() {
//        commandStack = new Stack<>();
//        numberStack = new Stack<>();
//    }
//
//    public void setCommands(List<String> commands) {
//    }
//
//    public Movement getMovement() {
//        return movementQueue.poll();
//    }
//
//    public boolean hasNextMovement() {
//        return !movementQueue.isEmpty();
//    }
//
//    public Map<String, Double> getUserVariables() {
//        Map<String, Double> map = new HashMap<>();
//        map.putAll(userVariables);
//        return map;
//    }
//}
