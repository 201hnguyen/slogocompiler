package slogo.backend.commands.experiment;

import java.util.ArrayList;
import java.util.List;

public class CommandNode {

    private String myCommands;
    private List<CommandNode> myChildren;

    public CommandNode() {
        myChildren = new ArrayList<>();
    }

    public void setCommand(String commands) {
        myCommands = commands;
    }

    public String getCommands() {
        return myCommands;
    }

    public List<CommandNode> getChildren() {
        return myChildren;
    }

    public void printChildren() {

    }

}
