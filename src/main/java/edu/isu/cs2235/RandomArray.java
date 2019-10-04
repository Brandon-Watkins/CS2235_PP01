package edu.isu.cs2235;
/**
 *  @author Brandon Watkins
 */

import java.util.Random;
import java.util.Arrays;

public class RandomArray {
    private Integer[] originalArray;
    private Integer[] insertArray;
    private Integer[] selectionArray;
    private Integer[] mergeArray;
    private Integer[] quickArray;
    private Integer[] timArray;
    private Integer[] hybridArray;

    public RandomArray(Integer sizeOfArray, Integer maxValue){
        copyArrays(buildArray(sizeOfArray, maxValue));
    }

    /**
     * Builds an array of random Integers
     * @param sizeOfArray size of the array to make
     * @param maxValue the range of the random values to generate
     * @return an Integer[] full of random values
     */
    private Integer[] buildArray(Integer sizeOfArray, Integer maxValue){
        Random random = new Random();
        originalArray = new Integer[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++){
            originalArray[i] = random.nextInt(maxValue) + 1;
        }
        return originalArray;
    }

    private void copyArrays(Integer[] array){
        insertArray = copyArray(array);
        selectionArray = copyArray(array);
        mergeArray = copyArray(array);
        quickArray = copyArray(array);
        timArray = copyArray(array);
        hybridArray = copyArray(array);
    }

    /**
     * Constructs an exact copy of the input array
     * @param otherArray The array to copy
     * @param E The type parameter of the data in the array
     * @return A copy of the provided array
     */
    public <E>E[] copyArray (E[] otherArray) {
        return Arrays.copyOf(otherArray, otherArray.length);
    }

    public Integer[] getInsertArray() {
        return insertArray;
    }

    public Integer[] getSelectionArray() {
        return selectionArray;
    }

    public Integer[] getMergeArray() {
        return mergeArray;
    }

    public Integer[] getQuickArray() {
        return quickArray;
    }

    public Integer[] getTimArray() {
        return timArray;
    }

    public Integer[] getHybridArray() {
        return hybridArray;
    }
}
