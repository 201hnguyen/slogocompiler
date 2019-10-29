package slogo.backend.commands;

import java.util.*;

//public class PeekableScanner {
//    private List<String> myCommands;
//    private int myIndex;
//
//
//    public PeekableScanner(String source) {
//        myCommands = Arrays.asList(source.split("\\s+"));
//        myIndex = 0;
//    }
//
//    public boolean hasNext() {
//        return myIndex != myCommands.size() - 1;
//    }
//
//    public String next() {
//        if (hasNext())
//        return myCommands.get(myIndex++);
//    }
//
//    public String peek() {
//        return myCommands.get(myIndex+1);
//    }
//}


//public class PeekableScanner {
//    private Scanner myFirstScanner;
//    private Scanner mySecondScanner;
//    private String myNextString;
//
//    public PeekableScanner(String source) {
//        myFirstScanner = new Scanner(source);
//        mySecondScanner = new Scanner(source);
//        myNextString = mySecondScanner.next();
//    }
//
//    public boolean hasNext() {
//        return myFirstScanner.hasNext();
//    }
//
//    public String next() {
//        if (mySecondScanner.hasNext()) {
//            myNextString = mySecondScanner.next();
//        } else {
//            myNextString = null;
//        }
//
//        return myFirstScanner.next();
//    }
//
//    public String peek() {
//        return myNextString;
//    }
//}

public class PeekableScanner {
    private int index;
    private List<String> myWords = new ArrayList<>();

    public PeekableScanner(String source) {
        String[] arr = source.split(" ");
        for(int i=0; i<arr.length; i++) {
            myWords.add(arr[i]);
        }
        index = 0;
    }

    public boolean hasNext() {
        return index < myWords.size();
    }

    public String next() {
        String str = myWords.get(index);
        index++;
        return str;
    }

    public int getIndex() {
        return index;
    }

    public void goToIndex(int index) {
        this.index = index;
    }

    public String peek() {
        return myWords.get(index);
    }
}