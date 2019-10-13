package slogo.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class CommandTreeNode {
    private CommandTreeNode leftNode;
    private CommandTreeNode rightNode;
    private List<String> commands = new ArrayList<>();

    public CommandTreeNode(List<String> commands) {
        this.commands.addAll(commands);
    }

    public CommandTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(CommandTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public CommandTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(CommandTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public void addToTreeNode(String str) {
        commands.add(str);
    }
}
