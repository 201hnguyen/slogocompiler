package slogo.backend.utils;

import slogo.util.DrawStatus;
import slogo.util.Movement;
import slogo.util.PenStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TurtleHistory {
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 2, false);

    private List<TurtleModel> myTurtles = new ArrayList<>();
    private List<List<TurtleMovement>> myTurtleHistory = new ArrayList<>();
    private List<Integer> activeTurtles = new ArrayList<>();
    private List<Map<String, Double>> listOfActiveVariables = new ArrayList<>();
    private int curTurtleID = 1;
    private int index = 0;

    public TurtleHistory() {
        myTurtles.add(new TurtleModel(1, INITIAL_PEN_STATUS, INITIAL_DRAW_STATUS));
        activeTurtles.add(1);
    }

    public TurtleModel getTurtleModel(int turtleID){
        for(TurtleModel turtle : myTurtles) {
            if((turtle.getMyID()==turtleID)) {
                curTurtleID = turtleID;
                return turtle;
            }
        }

        addTurtleModel(turtleID);
        return getTurtleModel(turtleID);
    }

    public void updateTurtle(int turtleID, Movement movement, DrawStatus drawStatus, PenStatus penStatus) {
        TurtleModel turtle = getTurtleModel(turtleID);
        turtle.update(movement, drawStatus, penStatus);
        myTurtleHistory.get(index).add(new TurtleMovement(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus()));
    }

    public void addTurtleModel(int turtleID) {
        if(!hasTurtle(turtleID)) {
            TurtleModel curTurtle = getTurtleModel(curTurtleID);
            curTurtleID = turtleID;
            myTurtles.add(new TurtleModel(turtleID, curTurtle.getPenStatus(), curTurtle.getDrawStatus()));
        }
    }

    public List<Map<String, Double>> getMyVariables() {
        ArrayList<Map<String, Double>> list = new ArrayList<>();
        list.addAll(listOfActiveVariables);
        return list;
    }

    public void toNextTurn(Map<String, Double> myGlobalVariables) {
        Map<String, Double> map = new TreeMap<>();
        map.putAll(myGlobalVariables);
        listOfActiveVariables.add(map);
        myTurtleHistory.add(new ArrayList<>());
        index++;
    }

    public List<List<TurtleMovement>> getMyTurtleHistory() {
        List<List<TurtleMovement>> list = new ArrayList<>();
        list.addAll(myTurtleHistory);
        return list;
    }

    public void clearHistory() {
        myTurtleHistory.clear();
        myTurtleHistory.add(new ArrayList<>());
        listOfActiveVariables = new ArrayList<>();
        index = 0;
    }

    public List<Integer> getActiveTurtles() {
        List<Integer> list = new ArrayList<>();
        list.addAll(activeTurtles);
        return list;
    }

    public void setActiveTurtles(List<Integer> activeTurtles) {
        this.activeTurtles.clear();
        this.activeTurtles.addAll(activeTurtles);
    }

    private boolean hasTurtle(int turtleID) {
        for(TurtleModel turtle : myTurtles) {
            if(turtle.getMyID()==turtleID) {
                return true;
            }
        }
        return false;
    }
}
