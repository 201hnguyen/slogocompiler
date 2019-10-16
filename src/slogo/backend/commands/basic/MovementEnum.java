package slogo.backend.commands.basic;

public enum MovementEnum {
    FORWARD("FORWARD", 1),
    BACKWARD("BACKWARD", 1),
    RIGHT("RIGHT", 1),
    LEFT("LEFT", 1),
    SETHEADING("SETHEADING", 1),
    SETTOWARDS("SETTOWARDS", 2),
    SETPOSITION("SETPOSITION", 2),
    PENDOWN("PENDOWN", 0),
    PENUP("PENUP", 0),
    SHOWTURTLE("SHOWTURTLE", 0),
    HIDETURTLE("HIDETURTLE", 0),
    HOME("HOME", 0),
    CLEARSCREEN("CLEARSCREEN", 0);

    private String name;
    private int numParameters;

    MovementEnum(String name, int numParameters) {
        this.name = name;
        this.numParameters = numParameters;
    }

    public String getName() {return name;}

    public int getNumParameters() {return numParameters;}
}
