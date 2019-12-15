package slogo.backend.utils;

import slogo.util.DrawStatus;
import slogo.util.Movement;
import slogo.util.PenStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Manages the history of the movements of the turtles, and the history of all variables.
 *
 * Ex:  TurtleHistory TH = new TurtleHistory();
 * TH.updateTurtle(1, Movement object, DrawStatus object, PenStatus object);
 * return getMyTurtleHistory()
 *
 * Additional Usage: none
 * @author Eric Han
 */
public class TurtleHistory {
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 2, false);
    private static final String CLEAR = "Clear";

    private List<TurtleModel> myTurtles = new ArrayList<>();
    private List<List<TurtleMovement>> myTurtleHistory = new ArrayList<>();
    private List<Integer> activeTurtles = new ArrayList<>();
    private List<Map<String, Double>> listOfActiveVariables = new ArrayList<>();
    private int curTurtleID = 1;
    private int index = 0;
    private boolean stampsCleared = false;
    /**
     * if string value is "Clear", means clearStamp.
     * else, string value will be the in the form "ID,xPos,yPos,orientation". Frontend is assumed to know
     * how to decipher this, as the frontend can know the image from the ID, and the location of the image and
     * the orientation from the other parts of the string.
     */
    private List<List<String>> myStamps = new ArrayList<>();

    public TurtleHistory() {
        myTurtles.add(new TurtleModel(1, INITIAL_PEN_STATUS, INITIAL_DRAW_STATUS));
        activeTurtles.add(1);
    }

    /**
     * Returns the TurtleModel object corresponding to the ID
     *
     * @param turtleID : ID of the TurtleModel
     */
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

    /**
     * Update the TurtleModel object corresponding to the ID, and saves the path.
     *
     * @param turtleID : ID of the TurtleModel
     *  @param movement : Movement object to be stored
     * @param drawStatus : DrawStatus to be stored
     * @param penStatus : PenStatus to be stored
     */
    public void updateTurtle(int turtleID, Movement movement, DrawStatus drawStatus, PenStatus penStatus) {
        TurtleModel turtle = getTurtleModel(turtleID);
        turtle.update(movement, drawStatus, penStatus);
        myTurtleHistory.get(index).add(new TurtleMovement(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus()));
    }

    private void addTurtleModel(int turtleID) {
        if(!hasTurtle(turtleID)) {
            TurtleModel curTurtle = getTurtleModel(curTurtleID);
            curTurtleID = turtleID;
            myTurtles.add(new TurtleModel(turtleID, curTurtle.getPenStatus(), curTurtle.getDrawStatus()));
        }
    }

    /**
     * Returns the history of variables
     */
    public List<Map<String, Double>> getMyVariables() {
        ArrayList<Map<String, Double>> list = new ArrayList<>();
        list.addAll(listOfActiveVariables);
        return list;
    }

    /**
     * Assumption : this is called every time that a movement command ends (at the end of rerunMovement method in CommandBlockManager)
     *
     * Purpose: Called to save and match the map of variables to the corresponding movements of the turtles.
     * @param myGlobalVariables : current collection of all variables
     */
    public void toNextTurn(Map<String, Double> myGlobalVariables) {
        Map<String, Double> map = new TreeMap<>();
        map.putAll(myGlobalVariables);
        listOfActiveVariables.add(map);
        myTurtleHistory.add(new ArrayList<>());
        myStamps.add(new ArrayList<>());
        index++;
    }

    /**
     * Returns the history of turtleMovements
     */
    public List<List<TurtleMovement>> getMyTurtleHistory() {
        List<List<TurtleMovement>> list = new ArrayList<>();
        list.addAll(myTurtleHistory);
        return list;
    }

    /**
     * Clear the history of turtle movements and variables
     */
    public void clearHistory() {
        myTurtleHistory.clear();
        myTurtleHistory.add(new ArrayList<>());
        listOfActiveVariables = new ArrayList<>();
        myStamps = new ArrayList<>();
        index = 0;
    }

    /**
     * Returns the list of all active turtles
     */
    public List<Integer> getActiveTurtles() {
        List<Integer> list = new ArrayList<>();
        list.addAll(activeTurtles);
        return list;
    }

    /**
     * Sets the list of all active turtles
     */
    public void setActiveTurtles(List<Integer> activeTurtles) {
        this.activeTurtles.clear();
        this.activeTurtles.addAll(activeTurtles);
    }

    public boolean addOrClearStamps(boolean clearStamp, int turtleID) {
        List<String> list = myStamps.get(myStamps.size()-1);
        TurtleModel turtleModel = myTurtles.get(turtleID);
        list.add(clearStamp? CLEAR :
                turtleID + "," + turtleModel.getXPos() + "," + turtleModel.getYPos() + "," + turtleModel.getOrientation());
        boolean alreadyCleared = stampsCleared;
        stampsCleared = clearStamp;
        return !alreadyCleared;
    }

    public List<List<String>> getStamps() {
        List<List<String>> list = new ArrayList<>();
        list.addAll(myStamps);
        return list;
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
