
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Found String: " + findPassword("26953030255659232628"));
    }

    public static String findPassword(String input) {
        BigInteger num = new BigInteger("26953030255659232628");

        for (int i = 1; i < 10; i++) {
            BigInteger multiple = num.multiply(BigInteger.valueOf(i));
            String str = multiple.toString();
            if (isStringValid(str)) {
                System.out.println(i);
                System.out.println(new BigInteger(str).bitLength());
                return str;
            }
        }

        return "";
    }

    private static boolean isStringValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        int num = Integer.parseInt(s.substring(0, 1));

        boolean result = isValidASCIIChar(num) && isStringValid(s.substring(1, s.length()));

        if (!result) {
            num = Integer.parseInt(s.substring(0, 2));
            result = isValidASCIIChar(num) && isStringValid(s.substring(2, s.length()));
        }

        if (!result) {
            num = Integer.parseInt(s.substring(0, 3));
            result = isValidASCIIChar(num) && isStringValid(s.substring(3, s.length()));
        }

        return result;
    }

    private static boolean isValidASCIIChar(int num) {
        return num >= 32 && num <= 126 && (num < 48 || num > 57);
    }
}


