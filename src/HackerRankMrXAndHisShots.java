import java.util.Arrays;
import java.util.Comparator;

/**
 * Hacker Rank problem - Mr. X and his shots - Time out
 */
public class HackerRankMrXAndHisShots {

    public static int solve(int[][] shotRanges, int[][] fielderRanges){
        int total = 0;
        int startPos = 0;
        boolean found;
        int fieldLength = fielderRanges.length;
        int shotLength = shotRanges.length;

        Arrays.sort(shotRanges, new MyComparator());
        Arrays.sort(fielderRanges, new MyComparator());

        for (int i = 0; i < fieldLength; i++) {
            int start1 = fielderRanges[i][0];
            int end1 = fielderRanges[i][1];
            int end3 = Integer.MAX_VALUE;
            if (i < fieldLength - 1) {
                end3 = fielderRanges[i + 1][1];
            }
            found = false;
            for (int j = startPos; j < shotLength; j++) {
                int start2 = shotRanges[j][0];
                int end2 = shotRanges[j][1];

                if (start1 <= end2) {
                    if (end1 >= start2) {
                        if (!found && (end3 >= start2)) {
                            //start comparing the next fielder from the first found position of the last fielder
                            startPos = j;
                            found = true;
                        }
                        total++;
                    } else {
                        //Finish when the shots are larger than the fielders
                        //Exploits the fact that fielders don't overlap. Otherwise this won't work.
                        break;
                    }
                }

            }
        }
        return total;
    }

}

//Sorts using start position
class MyComparator implements Comparator<int[]>{

    @Override
    public int compare(int[] o1, int[] o2) {
        int result = o1[0] - o2[0];
        if (result == 0) {
            result = o1[1] - o2[1];
        }
        return result;
    }
}
