package slogo.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class TurtleHistory {
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 1, false);

    private List<TurtleModel> myTurtles = new ArrayList<>();
    private List<TurtleMovement> myTurtleHistory = new ArrayList<>();
    private List<Integer> activeTurtles = new ArrayList<>();
    private int curTurtleID = 1;

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
        myTurtleHistory.add(new TurtleMovement(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus()));
    }

    public void addTurtleModel(int turtleID) {
        if(!hasTurtle(turtleID)) {
            TurtleModel curTurtle = getTurtleModel(curTurtleID);
            curTurtleID = turtleID;
            myTurtles.add(new TurtleModel(turtleID, curTurtle.getPenStatus(), curTurtle.getDrawStatus()));
        }
    }

    public List<TurtleMovement> getMyTurtleHistory() {
        List<TurtleMovement> list = new ArrayList<>();
        list.addAll(myTurtleHistory);
        return list;
    }

    public void clearHistory() {
        myTurtleHistory.clear();
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
