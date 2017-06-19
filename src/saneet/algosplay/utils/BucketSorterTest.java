package saneet.algosplay.utils;

import java.util.Random;

public class BucketSorterTest {
    public static void testBucketSorter(){
        //Bucket Sorter Test
        Random r = new Random((long) 1.04);
        int size = 12312034;
        int largeSize = 12312034;

        long startTime = System.nanoTime();
        BucketSorter sorter = new BucketSorter(size);
        for (int i = 0; i < size; i++) {
            int num = r.nextInt(largeSize);
            double index = ((double) num) / largeSize * size;

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
        System.out.println("Empty Buckets: " + sorter.emptyBuckets);

    }
}
