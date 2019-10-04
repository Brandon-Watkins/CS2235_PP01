package edu.isu.cs2235.algorithms.impl;
/**
 *  @author Brandon Watkins
 */
public class QuickSort implements edu.isu.cs2235.algorithms.ArraySort {

    @Override
    public <E extends Comparable> void sort(E[] array){
        if (array == null || array.length == 0) throw new IllegalArgumentException("Null array entered.");
        quickSort(array, 0, array.length - 1);
    }

    public <E extends Comparable> void quickSort(E[] array, int leftIndex, int rightIndex){
        if (leftIndex < rightIndex){
            int pi = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pi - 1);
            quickSort(array, pi + 1, rightIndex);
        }
    }

    public <E extends Comparable> int partition(E[] array, int leftIndex, int rightIndex){
        int pivotIndex = findPivot(array, leftIndex, rightIndex);
        E pivot = array[pivotIndex];
        swap(array, leftIndex, pivotIndex);
        int i = leftIndex + 1;
        int j = rightIndex;

        while(i <= j){
            if (array[i].compareTo(pivot) > 0 && array[j].compareTo(pivot) <= 0){
                swap(array, i, j);
                i++;
                j--;
            }
            else{
                if (array[i].compareTo(pivot) <= 0)
                    i++;
                if (array[j].compareTo(pivot) > 0)
                    j--;
            }
        }
        swap(array, leftIndex, j);
        return j;
    }

    public <E extends Comparable> int findPivot(E[] array, int leftIndex, int rightIndex){
        int pivotIndex;
        if(array[leftIndex].compareTo(array[(rightIndex - leftIndex) / 2]) > 0) pivotIndex = leftIndex;
        else pivotIndex = (rightIndex - leftIndex) / 2;

        if (pivotIndex == leftIndex) {
            if (array[pivotIndex].compareTo(array[rightIndex]) <= 0) pivotIndex = leftIndex;
            else{
                if (array[(rightIndex - leftIndex) / 2].compareTo(array[rightIndex]) < 0) pivotIndex = rightIndex;
                else pivotIndex = (rightIndex - leftIndex) / 2;
            }
        }

        else{
            if (array[(rightIndex - leftIndex) / 2].compareTo(array[rightIndex]) <= 0) pivotIndex = (rightIndex - leftIndex) / 2;
            else if (array[leftIndex].compareTo(array[rightIndex]) <= 0) pivotIndex = rightIndex;
            else pivotIndex = leftIndex;
        }
        return pivotIndex;
    }

    public <E extends Comparable> void swap(E[] array, int index1, int index2){
        E temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
