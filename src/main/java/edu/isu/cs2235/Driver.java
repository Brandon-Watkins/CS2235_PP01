package edu.isu.cs2235;

/**
 *  @author Brandon Watkins
 *  The modifyable values are at the top of init(), on lines 35-52.)
 */

import edu.isu.cs2235.algorithms.ArraySort;
import edu.isu.cs2235.algorithms.impl.HybridSort;
import edu.isu.cs2235.algorithms.impl.InsertionSort;
import edu.isu.cs2235.algorithms.impl.MergeSort;
import edu.isu.cs2235.algorithms.impl.QuickSort;
import edu.isu.cs2235.algorithms.impl.SelectionSort;
import edu.isu.cs2235.algorithms.impl.TimSort;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Driver {
    RandomArray randomArray;
    ArraySort hybridSort;
    ArraySort timSort;
    ArraySort quickSort;
    ArraySort mergeSort;
    ArraySort selectionSort;
    ArraySort insertionSort;

    public static void main(String[] args) {
        Driver d = new Driver();
        d.init();
    }

    public void init(){
        /** change these 2 values to modify the size of the arrays to be sorted. */
        int startingPoint = 10000;
        int endingPoint = 100000;

        /** change this to adjust the range of values in the random array */
        int maxValue = 100000;

        if (endingPoint % startingPoint != 0){
            System.out.println("Please choose startingPoint and endingPoint s.t. eP % sP == 0.");
            return;
        }
        /** change this to change the number of steps. */
        int numberOfSteps = 9;// this doesnt include the first iteration, will be 1 more "step" in results, to include both the min and max values.

        int stepSize = (endingPoint - startingPoint) / numberOfSteps;

        /** change this to modify the number of times to sort each array size.*/
        Integer numberOfSearchesToAverage = 50;

        // fills first column of results[][] with the Array sizes
        Long[][] results = new Long[(endingPoint - startingPoint)/stepSize + 1][7];// the +1 is due to comment on line 46.
        for (int o = 0; o < numberOfSteps + 1; o++){
            results[o][0] = (long)startingPoint + (o * stepSize);
        }

        createSortingConstructors();

        sortArrays(numberOfSearchesToAverage, numberOfSteps, startingPoint, stepSize, maxValue, results);

        // report results to standard output
        // report results to a CSV file named report.csv
        finalResults(results); // throws IO Exception
    }

    public void createSortingConstructors(){
        hybridSort = new HybridSort();
        timSort = new TimSort();
        quickSort = new QuickSort();
        mergeSort = new MergeSort();
        selectionSort = new SelectionSort();
        insertionSort = new InsertionSort();
    }

    public void resetArray(Long[][] results, int i){
        results[i][1] = 0L;
        results[i][2] = 0L;
        results[i][3] = 0L;
        results[i][4] = 0L;
        results[i][5] = 0L;
        results[i][6] = 0L;
    }

    public void averageTheSearchesForThisArraySize(int j, int numberOfSearchesToAverage, double complete, int i, int numberOfSteps, int startingPoint, int stepSize, int maxValue, Long[][] results, long time){
        for (j = 1; j < numberOfSearchesToAverage + 1; j++){// over 50 iterations, find the average time taken to sort the arrays
            complete = 10 * (10.0 * (double)i / (numberOfSteps + 1) + 1.0 * (double)j / numberOfSearchesToAverage);
            System.out.println((int)complete + " % Complete.     Averaging Iteration " + j + " / " +
                    numberOfSearchesToAverage + " of step " + (i + 1) + " / " + (numberOfSteps + 1) + " ( Array Size " +
                    (startingPoint + (stepSize * i) + " )"));// to see its progress
            // generate the array to sort
            generateRandomArray(stepSize * (i + 1), maxValue);
            // sort insertArray
            results[i][1] = results[i][1] + sortArray(randomArray.getInsertArray(), numberOfSearchesToAverage, insertionSort);
            // sort selectionArray
            results[i][2] = results[i][2] + sortArray(randomArray.getSelectionArray(), numberOfSearchesToAverage, selectionSort);
            // sort mergeArray
            results[i][3] = results[i][3] + sortArray(randomArray.getMergeArray(), numberOfSearchesToAverage, mergeSort);
            // sort quickArray
            results[i][4] = results[i][4] + sortArray(randomArray.getQuickArray(), numberOfSearchesToAverage, quickSort);
            // sort timArray
            results[i][5] = results[i][5] + sortArray(randomArray.getTimArray(), numberOfSearchesToAverage, timSort);
            // sort hybridArray
            results[i][6] = results[i][6] + sortArray(randomArray.getHybridArray(), numberOfSearchesToAverage, hybridSort);
            // add each result to it's respective average-result.
            /**
             * results[*][] = {startingPoint, startingPoint + stepSize, .., .., endingPoint}
             * results[][*] = {insert, selection, merge, quick, tim, hybrid}
             */
        }
        System.out.println("Finished sorting arrays of size " + (startingPoint + (stepSize * i)) + ". It took a total of " +
                ((System.nanoTime() - time) / 1000000000) + " seconds.");
    }

    public void sortArrays(int numberOfSearchesToAverage, int numberOfSteps, int startingPoint, int stepSize, int maxValue, Long[][]results){
        int j = 0;
        long time = 0;
        String msg;
        double complete = 0;// keeps track of completion percentage
        for (int i = 0; i < numberOfSteps + 1; i ++){// for each array-size..
            System.out.println("Currently repeating sorts on arrays of size " + (startingPoint + (stepSize * i)) + ".");// to see its progress
            time = System.nanoTime();
            //initializing values, to be safe.
            resetArray(results, i);
            averageTheSearchesForThisArraySize(j, numberOfSearchesToAverage, complete, i, numberOfSteps, startingPoint, stepSize, maxValue, results, time);
        }
    }

    public <E extends Comparable> long sortArray(E[] array, Integer numberOfSearchesToAverage, ArraySort fixture){
        Integer timeSpent = 0;
        // start timer
        long timeStart = System.nanoTime();
        // sort the array
        fixture.sort(array);
        // stop timer, record results
        return (System.nanoTime() - timeStart) / numberOfSearchesToAverage;
    }

    private void generateRandomArray(int sizeOfArray, int maxValue){
        randomArray = new RandomArray(sizeOfArray, maxValue);
    }

    public void finalResults(Long[][] results)  {
        try {
            System.out.println("Saving results to 'result.csv'.");
            PrintWriter writer = new PrintWriter(new FileWriter("report.csv"));
            writer.println("Array Size, Insertion, Selection, Merge, Quick, Tim, Hybrid");
            System.out.println("Array Size          Insertion          Selection         Merge          Quick          Tim           Hybrid          ");
            saveResults(writer, 0, results);
            writer.close();
        } catch(IOException ioe){
            System.out.println("Exception thrown in finalResults: " + ioe.getMessage());
        }
    }

    public void saveResults(PrintWriter writer, int index, Long[][] results){
        if (index < results.length){
            System.out.println(results[index][0] + "          " + results[index][1]+ "          " + results[index][2] +
                    "          " + results[index][3] + "          " + results[index][4] + "          " + results[index][5] +
                    "          " + results[index][6]);
            writer.println(results[index][0] + ", " + results[index][1] + ", " + results[index][2] +
                    ", " + results[index][3] + ", " + results[index][4] + ", " + results[index][5] + ", " + results[index][6]);
            saveResults(writer, index + 1, results);
        }
    }
}
