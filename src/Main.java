import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count1 = sc.nextInt();
        int count2 = sc.nextInt();

        int[][] shotRanges = new int[count1][2];
        int[][] fielderRanges = new int[count2][2];

        for (int i = 0; i < count1; i++){
            shotRanges[i][0] = sc.nextInt();
            shotRanges[i][1] = sc.nextInt();
        }
        for (int i = 0; i < count2; i++){
            fielderRanges[i][0] = sc.nextInt();
            fielderRanges[i][1] = sc.nextInt();
        }

        long startTime = System.nanoTime();

        System.out.println("Solution");
        System.out.println(HackerRankMrXAndHisShots.solve(shotRanges, fielderRanges));

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.

        System.out.println("Duration:" + duration);


    }
}


