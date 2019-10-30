package slogo.backend.commands;

import java.util.List;
import java.util.ArrayList;

/**
 * Given an input string, scans through it like a scanner but maintains indices to be returned to.
 */
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