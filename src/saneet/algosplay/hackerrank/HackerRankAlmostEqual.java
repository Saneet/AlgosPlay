package saneet.algosplay.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import saneet.algosplay.utils.*;

/**
 * HackerRank problem - Almost Equal - Passed 5 test cases.
 * Solution is an example of Dynamic Programming with memoization.
 * For this we need all the query ranges at once and cannot print the results incrementally as we will
 * be processing the queries non-sequentially.
 */
public class HackerRankAlmostEqual {
    private static int MEMO_COUNT = 0;
    private static int MEMO_UPTO = 1;
    public static int RANGE_L = 0;
    public static int RANGE_R = 1;
    public static int RANGE_INDEX = 2;




    /**
     * @param heights = array of heights
     * @param k = value of k or the threshold
     * @param queries = array of all query queries in the form [QUERYCOUNT][3]. The 3 elements for each index should be l, r, index.
     *               The index is just an incremental value starting from 0 to represent the index position of the query.
     *               Will be used to sort the query results.
     * @return Array of results for each query.
     */
    public static long[] getCountsForRanges(int[] heights, int k, int[][] queries) {
        long sortingStart = 0;
        long sortingEnd = 0;
        long sortingTime = 0;
        final int sqrtHeights = ((int) Math.sqrt(heights.length));

        //Sort queries by R value as we will be performing memoization based on right values
        //we need to sort on R because our memoization saves the count upto the last index that was processed.
        //If the last index is less than previous then we have no way of knowing the correct count and need to count again.
        //If the last index increases then we can start counting further from the last known index.
        Arrays.sort(queries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int value = (o1[RANGE_L] / sqrtHeights) - (o2[RANGE_L] / sqrtHeights);
                if (value == 0) {
                    value = o1[RANGE_R] - o2[RANGE_R];
                }
                return value;
            }
        });

        //We will also need to sort the heights as without that we will have to compare every item in the range.
        //But considering that the ranges are on original order of heights we will EITHER have to sort every range separately OR
        //Use this new trick I just came up with
        //Create a dual array of the heights to get the indexes of the heights that would be if they were sorted
        int HEIGHTS_HEIGHT = 0;
        int HEIGHTS_ORIGINAL_INDEX = 1;
        int HEIGHTS_SORTED_INDEX = 2;
        int[][] indexedHeights = new int[heights.length][3];
        for (int i = 0; i < indexedHeights.length; i++) {
            indexedHeights[i][HEIGHTS_HEIGHT] = heights[i];
            indexedHeights[i][HEIGHTS_ORIGINAL_INDEX] = i;
        }



        Arrays.sort(indexedHeights, new Comparator<int[]>(){

            @Override
            public int compare(int[] o1, int[] o2) {
                int result = o1[HEIGHTS_HEIGHT] - o2[HEIGHTS_HEIGHT];
                if (result == 0) {
                    result = o1[HEIGHTS_ORIGINAL_INDEX] - o2[HEIGHTS_ORIGINAL_INDEX];
                }
                return result;
            }
        });

        //Set the sorted index values on this sorted array
        for (int i = 0; i < indexedHeights.length; i++) {
            indexedHeights[i][HEIGHTS_SORTED_INDEX] = i;
        }

        //Sort back to the previous order using the original index
        Arrays.sort(indexedHeights, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[HEIGHTS_ORIGINAL_INDEX] - o2[HEIGHTS_ORIGINAL_INDEX];
            }
        });

        //Initialize Memo
        int[][] memoCountForPos = new int[indexedHeights.length][2];
        //Initialize results
        long[] results = new long[queries.length];

        for (int range = 0; range < queries.length; range++) {
            int l = queries[range][RANGE_L];
            int r = queries[range][RANGE_R];
            int index = queries[range][RANGE_INDEX];
            int rangeLength = r - l + 1;

            sortingStart = System.nanoTime();
            BucketSorter bucketSorter = new BucketSorter(rangeLength);
            for (int i = l; i <= r; i++) {
                double calc = (double) indexedHeights[i][HEIGHTS_SORTED_INDEX] / (double) indexedHeights.length * (double) rangeLength;
                int calcIndex = (int) calc;
                bucketSorter.putAtIndex(calcIndex, heights[i]);
            }
            int[] rangeWindowSorted = bucketSorter.toArray();
            /*int[] rangeWindowSorted = new int[rangeLength];
            for (int i = l, x=0; i <= r; i++, x++) {
                rangeWindowSorted[x] = heights[i];
            }
            Arrays.sort(rangeWindowSorted);*/

            sortingEnd = System.nanoTime();
            sortingTime = sortingTime + (sortingEnd - sortingStart) / 1000000;

            //Count of all matches in the range
            long totalCount = 0;
            //Loop through all the elements in the query range
            for (int i = 0; i < rangeWindowSorted.length; i++) {
                //Calculate the threshold sum
                int thresholdSumMax = rangeWindowSorted[i] + k;
                int thresholdSumMin = rangeWindowSorted[i] - k;
                //Matching count for the current element
                int count = 0;
                int j;
                //Get the last max index processed for the previous run for the same element.
                int memoUpto = memoCountForPos[i + l][MEMO_UPTO];

                //If there was a previous run for the element
                if (memoUpto > 0) {
                    //start from the last index. The last index will always increment through the various runs for the
                    //element because of our sorting of the queries.
                    j = memoUpto - l;
                    //Use the count saved from last run for that element
                    count = memoCountForPos[i + l][MEMO_COUNT];
                } else {
                    //Start from the next element
                    j = i + 1;
                }

                for (; j < rangeWindowSorted.length; j++) {
                    //Check if height fits
                    if (rangeWindowSorted[j] <= thresholdSumMax) {
                        //Increase count
                        count++;
                    } else {
                        break;
                    }
                }

                //Save calculated values
                memoCountForPos[i + l][MEMO_COUNT] = count;
                memoCountForPos[i + l][MEMO_UPTO] = j + l;
                //Add to the count of all matches in the range
                totalCount += count;
            }
            //Set the query result to the correct index
            results[index] = totalCount;
        }

        System.out.println("Total sorting time");
        System.out.println(sortingTime);
        return results;

    }



}


