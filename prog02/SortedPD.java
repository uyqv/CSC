package prog02;

import java.io.*;

/**
 * This is an implementation of PhoneDirectory that uses a sorted
 * array to store the entries.
 *
 * @author 
 */
public class SortedPD extends ArrayBasedPD {
    protected int find(String name) {
///*
        int maybe = 0;
        int not = size - 1;
        int middle;

        while (maybe <= not) {
            middle = (maybe + not) / 2;
            if (name.compareTo(theDirectory[middle].getName()) == 0) {
                return middle;
            } else if (name.compareTo(theDirectory[middle].getName()) > 0) {
                maybe = middle + 1;
            } else if (name.compareTo(theDirectory[middle].getName()) < 0) {
                not = middle - 1;
            }
        }
       // */

        /*
        for (int i = 0; i < size; i++)
            if (theDirectory[i].getName().equals(name))
                return i;

         */
        //return -maybe;
        return -maybe -1;
    }

    protected DirectoryEntry add(int index, DirectoryEntry newEntry) {
        if (size == theDirectory.length)
            reallocate();
        for (int i = size - 1; i >= index; i--) {
            theDirectory[i + 1] = theDirectory[i];
        }
        // theDirectory[size] = theDirectory[index];
        theDirectory[index] = newEntry;
        size++;
        return newEntry;
    }

    protected DirectoryEntry remove(int index) {
        index = size - 1;
        DirectoryEntry entry = theDirectory[index];
        for (int i = index; i <= size; i++) {
            theDirectory[i] = theDirectory[i + 1];
        }
        //theDirectory[index] = theDirectory[size - 1];
        size--;
        return entry;
    }

}
