package slogo.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class TurtleHistory {
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 1);

    private List<TurtleModel> myTurtles = new ArrayList<>();
    private List<List<TurtleMovement>> myTurtleHistory = new ArrayList<>();
    private int curTurtleID = 1;
    private int index = 0;

    public TurtleHistory() {
        myTurtles.add(new TurtleModel(1, INITIAL_PEN_STATUS, INITIAL_DRAW_STATUS));
        myTurtleHistory.add(new ArrayList<>());
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
        System.out.println("sef");
        TurtleModel turtle = getTurtleModel(turtleID);
        turtle.update(movement, drawStatus, penStatus);
        myTurtleHistory.get(myTurtleHistory.size()-1).add(new TurtleMovement(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus()));
    }

    public void toNextTurn() {
        myTurtleHistory.add(new ArrayList<>());
    }

    public void addTurtleModel(int turtleID) {
        if(!hasTurtle(turtleID)) {
            TurtleModel curTurtle = getTurtleModel(curTurtleID);
            curTurtleID = turtleID;
            myTurtles.add(new TurtleModel(turtleID, curTurtle.getPenStatus(), curTurtle.getDrawStatus()));
        }
    }

    public List<List<TurtleMovement>> getMyTurtleHistory() {
        for(TurtleMovement turtleMovement : myTurtleHistory.get(0)) {
            System.out.println("Recorded: " + turtleMovement.getPenStatus().isPenDown() + ", " + turtleMovement.getPenStatus().isPenDownChanged());
        }
        List<List<TurtleMovement>> list = new ArrayList<>();
        list.addAll(myTurtleHistory);
        return list;
    }

    public void clearHistory() {
        myTurtleHistory.clear();
        myTurtleHistory.add(new ArrayList<>());
    }

    public void initialize(){
        return;
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
