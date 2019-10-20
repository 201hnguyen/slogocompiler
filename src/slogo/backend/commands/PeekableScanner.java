package slogo.backend.commands;

import java.util.Scanner;

public class PeekableScanner {
    private Scanner myFirstScanner;
    private Scanner mySecondScanner;
    private String myNextString;

    public PeekableScanner(String source) {
        myFirstScanner = new Scanner(source);
        mySecondScanner = new Scanner(source);
        myNextString = mySecondScanner.next();
    }

    public boolean hasNext() {
        return myFirstScanner.hasNext();
    }

    public String next() {
        if (mySecondScanner.hasNext()) {
            myNextString = mySecondScanner.next();
        } else {
            myNextString = null;
        }

        return myFirstScanner.next();
    }

    public String peek() {
        return myNextString;
    }
}