package slogo.backend.commands.experiment;

public interface CommandBlock {
    public CommandNode getEntryNode();

    public CommandNode setExitNodeChild();
}
