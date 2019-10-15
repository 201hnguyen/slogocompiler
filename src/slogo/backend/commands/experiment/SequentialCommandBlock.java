package slogo.backend.commands.experiment;

public class SequentialCommandBlock implements CommandBlock {
    @Override
    public CommandNode getEntryNode() {
        return null;
    }

    @Override
    public CommandNode setExitNodeChild() {
        return null;
    }
}
