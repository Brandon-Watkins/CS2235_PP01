package edu.isu.cs2235.algorithms.impl;
/**
 *  @author Brandon Watkins
 */
public class InsertionSort implements edu.isu.cs2235.algorithms.ArraySort {

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        for (int i = 1; i < array.length; i++){
            insert(array,0, i, array[i]);
        }
    }

    public <E extends Comparable> void insert(E[] array, int indexStart, int indexEnd, E valueToInsert){
        int i = indexEnd - 1;
        while (i >= indexStart && array[i].compareTo(valueToInsert) > 0){
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = valueToInsert;
    }
}
