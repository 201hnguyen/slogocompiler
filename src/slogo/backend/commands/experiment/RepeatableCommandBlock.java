package slogo.backend.commands.experiment;

public class RepeatableCommandBlock implements CommandBlock {
    public RepeatableCommandBlock(String commands) {
        System.out.println(commands);
    }

    @Override
    public CommandNode getEntryNode() {
        return null;
    }

    @Override
    public CommandNode setExitNodeChild() {
        return null;
    }
}
