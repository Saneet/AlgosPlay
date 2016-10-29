package saneet.algosplay.hackerrank;


import com.sun.org.apache.xpath.internal.SourceTree;

public class AlmostSorted {

    public static void almostSorted(int[] arr) {

        int start, stop = -1;
        //check if increasing
        int min = 0;
        int count = 0;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (min <= arr[i]) {
                min = arr[i];
                if (stop > -1) {
                    start = i;
                }
            } else {
                if (max > arr[i]) {
                    max = arr[i];
                    count++;
                } else {
                    System.out.println("NO");
                    return;
                }
                stop = i;
            }
        }
        //check if fits
        if (max > min) {
        } else {
            System.out.println("NO");
            return;
        }

        String value;
        if (count > 1) {
            value = "Rev";
        } else {
            value = "Swap";
        }

        System.out.println();


    }
}
