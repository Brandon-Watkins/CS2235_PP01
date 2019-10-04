package edu.isu.cs2235.algorithms.impl;
/**
 *  @author Brandon Watkins
 *
 *  This will perform an in-place merge sort, utilizing a single array throughout. memory efficient,
 *      but shifting the entire array every iteration of the merge is less time efficient.
 *  Once the subarrays created by merge sort have reached a size of 10 or smaller, it will switch
 *      to InsertionSort to sort the subarrays
 */
import edu.isu.cs2235.algorithms.ArraySort;

import java.util.Arrays;

public class TimSort implements edu.isu.cs2235.algorithms.ArraySort {

    private ArraySort fixture;
    private MergeSort mergeS;

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        fixture = new InsertionSort();
        mergeS = new MergeSort();
        timSort(array);
    }

    public <E extends Comparable> void timSort(E[] arrayToSort){
        if (arrayToSort.length < 10){
            fixture.sort(arrayToSort);
            return;// finished sorting
        }

        int length = arrayToSort.length;
        int mid = length / 2;

        E[] leftArray = Arrays.copyOfRange(arrayToSort, 0, mid);
        E[] rightArray = Arrays.copyOfRange(arrayToSort, mid, length);

        timSort(leftArray);
        timSort(rightArray);
        mergeS.merge(leftArray, rightArray, arrayToSort);
    }
}
