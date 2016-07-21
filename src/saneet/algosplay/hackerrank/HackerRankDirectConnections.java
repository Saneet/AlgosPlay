package saneet.algosplay.hackerrank;

import saneet.algosplay.datastructures.BigFenwickTree;
import saneet.algosplay.datastructures.FenwickTree;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

/**
 * HackerRank problem - Direct Connections
 */
public class HackerRankDirectConnections {

    public static final int LOCATION = 0;
    public static final int POPULATION = 1;

    public static BigInteger getTotalCableLength(int[][] locationPopulation) {

        //Ascending sort with Location
        Arrays.sort(locationPopulation, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[LOCATION] - o2[LOCATION];
            }
        });

        //Create array for creating fenwick tree to get quick additions
        BigInteger[] fenwickTree = new BigInteger[locationPopulation.length];
        //Create array for creating fenwick tree to manage indexes
        //On every iteration we delete an element from random index.
        //This tree will help us know how many elements are remaining in the list as we will delete the index from here.
        int[] fenwickTreeIndexes = new int[locationPopulation.length];
        //Get sum of all locations
        BigInteger sumOfLocations = BigInteger.valueOf(0);
        //Create an array of populations and their index in the main array
        int[][] populationIndexArray = new int[locationPopulation.length][2];
        for (int i = 0; i < populationIndexArray.length; i++) {
            //Get population
            populationIndexArray[i][0] = locationPopulation[i][POPULATION];
            //Get index
            populationIndexArray[i][1] = i;
            //add to sum
            sumOfLocations = sumOfLocations.add(BigInteger.valueOf(locationPopulation[i][LOCATION]));
            //populate the array for fenwick tree
            fenwickTree[i] = BigInteger.valueOf(locationPopulation[i][LOCATION]);
            //populate the array for fenwick tree
            fenwickTreeIndexes[i] = 1;
        }

        //Sort the population index array in descending
        Arrays.sort(populationIndexArray, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        //Create Fenwick tree
        fenwickTree = BigFenwickTree.createTree(fenwickTree);
        //Create Fenwick tree for indexes
        fenwickTreeIndexes = FenwickTree.createTree(fenwickTreeIndexes);

        //Final result variable
        BigInteger finalSum = BigInteger.valueOf(0);

        //Iterate from the city with highest population to the lowest population
        for (int i = 0; i < populationIndexArray.length - 1; i++) {
            //Get the current max population value
            BigInteger maxPopulation = BigInteger.valueOf(populationIndexArray[i][0]);
            //Get the index of the max population value for the main array
            int index = populationIndexArray[i][1];

            //Get the current location value to use as subtractor
            BigInteger subtractor = BigInteger.valueOf(locationPopulation[index][LOCATION]);
            //Remove the current value from the total sum
            sumOfLocations = sumOfLocations.subtract(subtractor);
            //Remove the current value from the Fenwick Tree
            BigFenwickTree.updateAddValuetoIndex(fenwickTree, index + 1, subtractor.negate());
            //Remove the current value from the Index Fenwick Tree
            FenwickTree.updateAddValuetoIndex(fenwickTreeIndexes, index + 1, -1);
            //Get the sum of the left side
            BigInteger leftSum = BigFenwickTree.getSumToIndex(fenwickTree, index - 1);
            //Get the sum of the right side
            BigInteger rightSum = sumOfLocations.subtract(leftSum);
            //Subtract distances according to left or right
            //Use the index fenwick tree to get the true index from left by getting the number of remaining elements on left
            int leftMultiplierIndex = FenwickTree.getSumToIndex(fenwickTreeIndexes, index);
            //Calculate the left multiple by multiplying the index with the subtractor
            BigInteger subtractorMultiple = subtractor.multiply(BigInteger.valueOf(leftMultiplierIndex));
            //Subtract the value from left
            leftSum = subtractorMultiple.subtract(leftSum);
            //Total length - number of elements processed - 1 for 0 based indexes - what we already considered on left
            int rightMultiplierIndex = locationPopulation.length - i - 1 - leftMultiplierIndex;
            //Calculate the right multiple by multiplying the index with the subtractor
            subtractorMultiple = subtractor.multiply(BigInteger.valueOf(rightMultiplierIndex));
            //Subtract the value from right
            rightSum = rightSum.subtract(subtractorMultiple);
            //Get total of all distances and multiply by population
            BigInteger totalCableLength = leftSum.add(rightSum).multiply(maxPopulation);

            //Add to total sum
            finalSum = finalSum.add(totalCableLength);
        }

        //Return the mod as given in the statement
        return finalSum.mod(BigInteger.valueOf(1000000007));
    }


}

