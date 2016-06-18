import java.util.ArrayList;
import java.util.List;

/**
 * Print and fetch prime numbers
 */
public class PrimeNumbers {
    private static int n = 0;

    //Checks if the given number is divisible by the numbers passed in the list
    //that are smaller than its square root
    private static boolean checkIfDivisible(int num, List<Integer> list) {
        int sqrt = Math.toIntExact(Math.round(Math.sqrt(num)));
        for (int i:list) {
            n++;
            if (i > sqrt) {
                return false;
            }
            if (num % i == 0) {
                return true;
            }
        }
        return false;

    }

    //Prints the numbers fetched from fetchPrimeNumbers
    public static void printPrimeNumbers(int uptoNum) {
        System.out.println("Prime numbers:");
        for (int i :
                fetchPrimeNumbers(uptoNum)) {
            System.out.println(i);
        }
        System.out.println("Number of divisibility calculations: " + n);

    }

    //Checks if every number is divisible by the prime numbers before it
    public static List<Integer> fetchPrimeNumbers(int uptoNum) {
        List<Integer> primeList = new ArrayList<Integer>();

        for (int i = 2; i < uptoNum; i++) {
            if (!checkIfDivisible(i, primeList)) {
                primeList.add(i);
            }

        }
        return primeList;

    }
}
