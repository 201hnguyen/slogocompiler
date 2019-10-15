package slogo;

import slogo.backend.commands.experiment.RepeatableCommandBlock;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {

        RepeatableCommandBlock commandStructure = new RepeatableCommandBlock("repeat 180 [ fd 5 rt 2 ]");
        System.out.println("Hello world");
    }
}
