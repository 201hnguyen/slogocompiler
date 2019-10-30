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

    /**
     * Checks if the source String has a word/String following it
     * @return
     */
    public boolean hasNext() {
        return index < myWords.size();
    }

    /**
     * Gets the next individual word/String in the source String
     * @return
     */
    public String next() {
        String str = myWords.get(index);
        index++;
        return str;
    }

    /**
     * Gets the index of a certain String in the source
     * @return
     */
    public int getIndex() {
        return index;
    }

    /**
     * Goes back to the index of a certain string in the source
     * @param index
     */
    public void goToIndex(int index) {
        this.index = index;
    }

    /**
     * peeks the next element in the source
     * @return
     */
    public String peek() {
        return myWords.get(index);
    }
}