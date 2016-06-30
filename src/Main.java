import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            int totalCount = FastScanner.nextInt();
            long startTime = System.nanoTime();

            for (int i = 0; i < totalCount; i++) {
                int arrCount = FastScanner.nextInt();
                int[][] locationPopulation = new int[arrCount][2];
                for (int j = 0; j < arrCount; j++) {
                    locationPopulation[j][0] = FastScanner.nextInt();
                }
                for (int j = 0; j < arrCount; j++) {
                    locationPopulation[j][1] = FastScanner.nextInt();
                }
                System.out.println(HackerRankDirectConnections.getTotalCableLength(locationPopulation));
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.

            System.out.println("Duration:" + duration);
        } catch (IOException e) {
            System.out.println("Invalid Input");
        }


    }
}


