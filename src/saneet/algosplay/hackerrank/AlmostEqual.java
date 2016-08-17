package saneet.algosplay.hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import saneet.algosplay.datastructures.SkipList;
import saneet.algosplay.utils.*;

/**
 * HackerRank problem - Almost Equal - Passed 5 test cases.
 * Implemented using Two pointer algorithm with MO's sorting. The queries will be divided into sections depending upon the start point and
 * the queries in the same section will be sorted according to their end points. Then we'll run a two pointer algorithm
 * and grow and shrink our current range according to the next query, adding and deleting elements as needed. To make
 * sure we don't have to compare each element to every other element in the current range we will keep the current range sorted so we can break the loops.
 * It will be sorted, searchable, editable and iterable, hence skip list. If an existing number gets added to the range again then we will use the
 * previously calculated value and double it and add one. For this all the repeating numbers which are same should be processed together.
 * We will use Hashmap with number counts for that.
 */
public class AlmostEqual {
    private static int MEMO_COUNT = 0;
    private static int MEMO_UPTO = 1;
    public static int RANGE_L = 0;
    public static int RANGE_R = 1;
    public static int RANGE_INDEX = 2;


    /**
     * @param heights = array of heights
     * @param k       = value of k or the threshold
     * @param queries = array of all query queries in the form [QUERYCOUNT][3]. The 3 elements for each index should be l, r, index.
     *                The index is just an incremental value starting from 0 to represent the index position of the query.
     *                Will be used to sort the query results.
     * @return Array of results for each query.
     */
    public static long[] getCountsForRanges(int[] heights, int k, int[][] queries) {
        final int sqrtHeights = ((int) Math.sqrt(heights.length));

        //Divide the queries into sections and sort them on sections first and then on end points.
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

        //Run MO's algorithm
        long[] results = new long[queries.length];

        int l = queries[0][RANGE_L];
        int r = queries[0][RANGE_R];

        SkipList<Integer> currentRange = new SkipList<Integer>(r - l + 1);
        HashMap<Integer, Integer> currentRangeNumCounts = new HashMap<Integer, Integer>();

        int currentL = -1;
        int currentR = -1;

        int result = 0;
        for (int range = 0; range < queries.length; range++) {
            l = queries[range][RANGE_L];
            r = queries[range][RANGE_R];
            int index = queries[range][RANGE_INDEX];



            while (l < currentL) {
                currentL--;
                result += addRemoveHeightToRange(heights[currentL], currentRange, k, false);
            }

            while (r > currentR) {
                currentR++;
                result += addRemoveHeightToRange(heights[currentR], currentRange, k, false);
            }

            while (l > currentL) {
                if (currentL >= 0) {
                    result += addRemoveHeightToRange(heights[currentL], currentRange, k, true);
                }
                currentL++;
            }


            while (r < currentR) {
                result += addRemoveHeightToRange(heights[currentR], currentRange, k, true);
                currentR--;
            }

            //Set the query result to the correct index
            results[index] = result;

        }

        return results;

    }


    private static int addRemoveHeightToRange(int height, SkipList<Integer> list, int threshold, boolean delete){
        int result = 0;

        SkipList<Integer>.LinkedListNode<Integer> node;
        int operation;
        if (delete) {
            operation = -1;
            node = list.findNode(height);
        } else {
            operation = 1;
            node = list.addNode(height);
        }

        int thresholdMax = height + threshold;
        int thresholdMin = height - threshold;


        SkipList<Integer>.LinkedListNode<Integer> nextNode = list.getNextNode(node);
        while (nextNode != null && nextNode.getValue() != null && nextNode.getValue() <= thresholdMax) {
            result += operation;
            nextNode = list. getNextNode(nextNode);
        }

        SkipList<Integer>.LinkedListNode<Integer> prevNode = list.getPreviousNode(node);
        while (prevNode != null && prevNode.getValue() != null && prevNode.getValue() >= thresholdMin) {
            result += operation;
            prevNode = list.getPreviousNode(prevNode);
        }

        if (delete) {
            list.deleteNode(node);
        }
        return result;

    }


}


