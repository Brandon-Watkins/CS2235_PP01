package edu.isu.cs2235.algorithms.impl;

/**
 *  @author Brandon Watkins
 *  Once the subarrays created by QuickSort have reached a size of 10 or smaller, it will switch
 *      to InsertionSort to sort the subarrays
 */

import edu.isu.cs2235.algorithms.ArraySort;

public class HybridSort implements edu.isu.cs2235.algorithms.ArraySort {
private InsertionSort insertionS;
private QuickSort quickS;

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        insertionS = new InsertionSort();
        quickS = new QuickSort();
        hybridSort(array, 0, array.length - 1);
    }

    public <E extends Comparable> void hybridSort(E[] array, int leftIndex, int rightIndex){
        int length = rightIndex - leftIndex;
        if (length < 10){
            for (int k = leftIndex; k < length; k++){
                insertionS.insert(array, leftIndex, rightIndex, array[k]);
            }
            return;
        }
        if (leftIndex < rightIndex){
            int pi = quickS.partition(array, leftIndex, rightIndex);
            hybridSort(array, leftIndex, pi - 1);
            hybridSort(array, pi + 1, rightIndex);
        }
    }
}