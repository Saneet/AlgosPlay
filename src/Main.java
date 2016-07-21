import saneet.algosplay.hackerrank.HackerRankAlmostEqual;
import saneet.algosplay.utils.FastScanner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        //Bucket Sorter Test
        /*Random r = new Random((long) 1.04);
        int size = 12312;
        int largeSize = 921231134;

        long startTime = System.nanoTime();
        saneet.algosplay.utils.BucketSorter sorter = new saneet.algosplay.utils.BucketSorter(size);
        for (int i = 0; i < size; i++) {
            int num = r.nextInt(largeSize);
            double index = ((double) num) / (largeSize) * size;

            //int num = largeSize - size + i - 10;
            sorter.putAtIndex((int) index, num);
        }
        long middleTime = System.nanoTime();
        int[] sortedArray = sorter.toArray();
        long endTime = System.nanoTime();
        long duration1 = (middleTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        long duration2 = (endTime - middleTime) / 1000000;  //divide by 1000000 to get milliseconds.

        System.out.println("Duration1:" + duration1);
        System.out.println("Duration2:" + duration2);

        int last = sortedArray[0];
        for (int i = 1; i < sortedArray.length; i++) {
            if (sortedArray[i] < last) {
                System.out.println("Incorrect");
                break;
            }
        }
        System.out.println("Done");
        System.out.println("Insert Comparisons: " + sorter.insertComparisons);
        System.out.println("Empty Buckets: " + sorter.emptyBuckets);*/

        //System.out.println(Arrays.toString(sortedArray));
        try {

            int totalCount = FastScanner.nextInt();
            int k = FastScanner.nextInt();

            long startTime = System.nanoTime();

            int[] heights = new int[totalCount];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = FastScanner.nextInt();
            }

            int queryCount = FastScanner.nextInt();

            int[][] queries = new int[queryCount][3];

            for (int i = 0; i < queryCount; i++) {
                queries[i][HackerRankAlmostEqual.RANGE_L] = FastScanner.nextInt();
                queries[i][HackerRankAlmostEqual.RANGE_R] = FastScanner.nextInt();
                queries[i][HackerRankAlmostEqual.RANGE_INDEX] = i;
            }

            long[] results = HackerRankAlmostEqual.getCountsForRanges(heights, k, queries);

            for (int i = 0; i < results.length; i++) {
                System.out.println(results[i]);
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.

            System.out.println("Duration:" + duration);
        } catch (IOException e) {
            System.out.println("Invalid Input");
        }



    }
}


