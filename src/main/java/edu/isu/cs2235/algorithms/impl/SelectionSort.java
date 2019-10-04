package edu.isu.cs2235.algorithms.impl;
/**
 *  @author Brandon Watkins
 */
public class SelectionSort implements edu.isu.cs2235.algorithms.ArraySort {

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        for (int firstUnsortedIndex = 0; firstUnsortedIndex < array.length; firstUnsortedIndex ++){
            int minIndex = firstUnsortedIndex;
            for (int i = minIndex; i < array.length; i++){
                if (array[i].compareTo(array[minIndex]) < 0) minIndex = i;
            }
            swap(firstUnsortedIndex, minIndex, array);
        }
    }

    public <E extends Comparable> void swap(int index1, int index2, E[] array){
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
