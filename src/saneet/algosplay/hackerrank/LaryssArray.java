package saneet.algosplay.hackerrank;

/**
 * Hacker Rank problem - Larry's Array
 */
public class LaryssArray {
    public static boolean canArrayBySorted(int[] a) {
        int sum = 0;
        //Count the number of inversions or places where the number is before the numbers that are smaller than it
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j])
                    sum++;
            }
        }
        //3 numbers switch makes it impossible to move number by 1 place
        //So odd even status of the array cannot be changed by the switching process
        //Hence arrays whose inversion count is already even are the only ones that can be sorted
        if (sum % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
