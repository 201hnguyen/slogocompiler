package slogo.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class TurtleHistory {
    private List<TurtleModel> myTurtles = new ArrayList<>();
    private List<List<TurtleMovement>> myTurtleHistory = new ArrayList<>();
    private int index = 0;

    public TurtleHistory() {
        myTurtles.add(new TurtleModel(1));
        myTurtleHistory.add(new ArrayList<>());
    }

    public TurtleModel getTurtleModel(int turtleID){
        for(TurtleModel turtle : myTurtles) {
            if((turtle.getMyID()==turtleID)) {
                return turtle;
            }
        }

        addTurtleModel(turtleID);
        return getTurtleModel(turtleID);
    }

    public void updateTurtle(int turtleID, Movement movement, DrawStatus drawStatus) {
        TurtleModel turtle = getTurtleModel(turtleID);
        myTurtleHistory.get(myTurtleHistory.size()-1).add(new TurtleMovement(turtleID, movement, drawStatus));
        turtle.update(movement, drawStatus);
        System.out.println(turtle.getXPos() + ", " + turtle.getYPos() + ", " + turtle.getOrientation());
        System.out.println(myTurtleHistory.get(0).size());
    }

    public void toNextTurn() {
        myTurtleHistory.add(new ArrayList<>());
    }

    public void addTurtleModel(int turtleID) {
        if(!hasTurtle(turtleID)) {
            TurtleModel turtle = new TurtleModel(turtleID);
            myTurtles.add(turtle);
        }
    }

    public List<List<TurtleMovement>> getMyTurtleHistory() {
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
