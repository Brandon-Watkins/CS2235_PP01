package edu.isu.cs2235.algorithms.impl;

import javax.management.openmbean.ArrayType;
import java.util.Arrays;

/**
 *  @author Brandon Watkins
 *
 *  this will perform an in-place merge sort, utilizing a single array throughout. memory efficient,
 *  but shifting the entire array every iteration of the merge is less time efficient.
 */

public class MergeSort implements edu.isu.cs2235.algorithms.ArraySort {

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        mergeSort(array);
    }

    public <E extends Comparable> void mergeSort(E[] arrayToSort){
        int length = arrayToSort.length;
        if (length < 2) return; //sorted
        int mid = length / 2;

        E[] leftArray = Arrays.copyOfRange(arrayToSort, 0, mid);
        E[] rightArray = Arrays.copyOfRange(arrayToSort, mid, length);

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, arrayToSort);
    }

    public <E extends Comparable> void merge(E[] leftArrayToMerge, E[] rightArrayToMerge, E[] arrayToSort){
        int leftCount = 0;
        int rightCount = 0;
        int leftLength = leftArrayToMerge.length;
        int rightLength = rightArrayToMerge.length;

        while(leftCount + rightCount < arrayToSort.length){
            if (rightCount == rightLength || (leftCount < leftLength && leftArrayToMerge[leftCount].compareTo(rightArrayToMerge[rightCount]) < 0)){
                arrayToSort[leftCount + rightCount] = leftArrayToMerge[leftCount];
                leftCount++;
            }
            else{
                arrayToSort[leftCount + rightCount] = rightArrayToMerge[rightCount];
                rightCount++;
            }
        }
    }

    public <E extends Comparable> void swap(E[] array, int index1, int index2){
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
