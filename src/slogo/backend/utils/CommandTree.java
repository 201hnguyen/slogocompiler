package slogo.backend.utils;

import slogo.backend.NeedValueOfParameterException;
import slogo.backend.UnmatchedNumArgumentsException;
import slogo.backend.commands.basic.CommandFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommandTree {

    private final String decimalPattern = "-?[0-9]+\\.?[0-9]*";

    CommandTreeNode commandTreeNode = new CommandTreeNode("", null);
    CommandTreeNode rightMostNode = commandTreeNode;

    private CommandFactory myCommandFactory;
    private String turtleID = "";

    public CommandTree(TurtleManager turtleManager) {
        myCommandFactory = new CommandFactory(turtleManager);
    }

    public void addToCommandTree(String command) throws NeedValueOfParameterException {
        if (commandTreeNode.getCommandWord().equals("") && isThisStringDouble(command)) {
            //error
        }
        if (isThisStringDouble(command)) {
            addNumberToTree(command);
        } else {
            addCommandToTree(command);
        }
        interpretTreeFromRight();
    }

    public void putValueInsteadOfParameter(double value) {
        rightMostNode.setCommandWord(""+value);
        if(rightMostNode.getParentNode() != null) {
            moveRightNodeUp();
        }
    }

    public double getLastDouble() throws UnmatchedNumArgumentsException {
        if(commandTreeNode.getCommandWord().equals("")) {
            return 0d;
        }
        if(commandTreeNode.getChildrenNumber() != 0 || (!isThisStringDouble(commandTreeNode.getCommandWord()))) {
            throw new UnmatchedNumArgumentsException("Ummatched Num");
        }
        return Double.parseDouble(commandTreeNode.getCommandWord());
    }

    private void interpretTreeFromRight() throws NeedValueOfParameterException{
        String command = rightMostNode.getCommandWord();
        try {
            int parameter = myCommandFactory.getNumParameter(command);
            while(!(parameter < rightMostNode.getChildrenNumber()) && rightMostNode.getCommandWord() != "") {
                double value = myCommandFactory.execute(command, turtleID, getParameters());
                replaceRightMostCommandWithNumber(value);
            }
        }
        catch (ClassNotFoundException e) {
            throw new NeedValueOfParameterException(command);
        }
    }

    private void addNumberToTree(String command) {
        if(rightMostNode.getLeftNode() == null) {
            rightMostNode.setLeftNode(new CommandTreeNode(command, rightMostNode));
        } else {
            rightMostNode.setRightNode(new CommandTreeNode(command, rightMostNode));
        }
    }

    private void addCommandToTree(String command) {
        rightMostNode.setRightNode(new CommandTreeNode(command, rightMostNode));
        rightMostNode = rightMostNode.getRightNode();
    }


    private boolean isThisStringDouble(String command) {
        return Pattern.matches(decimalPattern, command);
    }

    private void replaceRightMostCommandWithNumber(double num) {
        rightMostNode.setCommandWord("" + num);
        rightMostNode.deleteChildren();

        if(rightMostNode.getParentNode() != null) {
            moveRightNodeUp();
        }
    }

    private void moveRightNodeUp() {
        rightMostNode = rightMostNode.getParentNode();
        if(rightMostNode.getLeftNode() == null) {
            CommandTreeNode treeNode = rightMostNode.getRightNode();
            rightMostNode.deleteChildren();
            rightMostNode.setLeftNode(treeNode);
        }
    }

    private List<Double> getParameters() {
        List<Double> list = new ArrayList<>();
        if(rightMostNode.getLeftNode() != null) {
            list.add(Double.parseDouble(rightMostNode.getLeftNode().getCommandWord()));
        }

        if(rightMostNode.getRightNode() != null) {
            list.add(Double.parseDouble(rightMostNode.getRightNode().getCommandWord()));
        }
        return list;
    }
}