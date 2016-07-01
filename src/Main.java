import java.io.IOException;

public class Main {

    public static void main(String[] args) {

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


