import java.math.BigInteger;

/**
 * Hacker Rank problem - Extra long factorials
 */
public class HackerRankExLongFactorial {
    //Uses BigInteger to loop through and calculate factorial of large numbers
    public static String getFactorialString(int num) {
        BigInteger bInt = BigInteger.valueOf(1);
        for (int i = num; i > 0; i--) {
            bInt = bInt.multiply(BigInteger.valueOf(i));
        }
        return bInt.toString();

    }
}
