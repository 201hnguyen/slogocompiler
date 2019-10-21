package slogo.backend.utils;

public class CommandTreeNode {
    private CommandTreeNode parentNode;
    private CommandTreeNode leftNode;
    private CommandTreeNode rightNode;
    private String commandWord;

    public CommandTreeNode(String commandWord, CommandTreeNode parentNode) {
        this.parentNode = parentNode;
        this.commandWord = commandWord;
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

    public CommandTreeNode getParentNode() {
        return parentNode;
    }

    public String getCommandWord() {
        return commandWord;
    }

    public void setCommandWord(String commandWord) {
        this.commandWord = commandWord;
    }

    public void deleteChildren() {
        leftNode = null;
        rightNode = null;
    }

    public int getChildrenNumber() {
        int leftNodeNum = leftNode == null ? 0 : 1;
        int rightNodeNum = rightNode == null ? 0 : 1;
        return leftNodeNum + rightNodeNum;
    }
}
