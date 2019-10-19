package slogo;

import slogo.backend.commands.control.Repeat;

import java.util.ArrayList;
import java.util.List;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        Repeat repeat = new Repeat();
        List<Object> parameters = new ArrayList<>();
        parameters.add(3);
        parameters.add("fd 50 fd 10"); // FIXME: currently split by comma just for sake of demo
        repeat.execute(parameters);
    }
}
