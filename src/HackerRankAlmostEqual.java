import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
        //Sort queries by R value as we will be performing memoization based on right values
        //we need to sort on R because our memoization saves the count upto the last index that was processed.
        //If the last index is less than previous then we have no way of knowing the correct count and need to count again.
        //If the last index increases then we can start counting further from the last known index.
        Arrays.sort(queries, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int value = o1[RANGE_R] - o2[RANGE_R];
                if (value == 0) {
                    //Doesn't make a different what we chose on the left side
                    value = o2[RANGE_L] - o1[RANGE_L];
                }
                return value;
            }
        });

        //Initialize Memo
        int[][] memoCountForPos = new int[heights.length][2];;
        //Initialize results
        long[] results = new long[queries.length];

        for (int range = 0; range < queries.length; range++) {
            int l = queries[range][RANGE_L];
            int r = queries[range][RANGE_R];
            int index = queries[range][RANGE_INDEX];

            //Count of all matches in the range
            long totalCount = 0;
            //Loop through all the elements in the query range
            for (int i = l; i <= r; i++) {
                //Calculate the threshold sum
                int thresholdSumMax = heights[i] + k;
                int thresholdSumMin = heights[i] - k;
                //Matching count for the current element
                int count = 0;
                int j;
                //Get the last max index processed for the previous run for the same element.
                int memoUpto = memoCountForPos[i][MEMO_UPTO];

                //If there was a previous run for the element
                if (memoUpto > 0) {
                    //start from the last index. The last index will always increment through the various runs for the
                    //element because of our sorting of the queries.
                    j = memoUpto;
                    //Use the count saved from last run for that element
                    count = memoCountForPos[i][MEMO_COUNT];
                } else {
                    //Start from the next element
                    j = i + 1;
                }

                for (; j <= r; j++) {
                    //Check if height fits
                    if (heights[j] <= thresholdSumMax && heights[j] >= thresholdSumMin) {
                        //Increase count
                        count++;
                    }
                }

                //Save calculated values
                memoCountForPos[i][MEMO_COUNT] = count;
                memoCountForPos[i][MEMO_UPTO] = j;
                //Add to the count of all matches in the range
                totalCount += count;
            }
            //Set the query result to the correct index
            results[index] = totalCount;
        }

        return results;


    }
}
