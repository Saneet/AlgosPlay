package saneet.algosplay.hackerrank;

import java.util.ArrayList;
import java.util.List;

/**
 * Hacker Rank problem - Kaprekar numbers
 */
public class KaprekarNumbers {

    public static List<Integer> fetchKaprekarList(int start, int end) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {
            int sqr = i * i;
            StringBuilder strbr1 = new StringBuilder(Integer.toString(sqr));
            StringBuilder strbr2 = new StringBuilder();

            while (strbr1.length() > 0) {
                strbr2.append(strbr1.charAt(strbr1.length() - 1));
                strbr1.deleteCharAt(strbr1.length() - 1);
                int num1, num2;
                if (strbr1.length() > 0) {
                    num1 = Integer.parseInt(strbr1.toString());
                }else{
                    num1 = 0;
                }
                if (strbr2.length() > 0) {
                    num2 = Integer.parseInt(strbr2.toString());
                }else{
                    num2 = 0;
                }

                if (num1 + num2 == i) {
                    result.add(i);
                }
            }


        }
        return result;
    }

    public static List<Integer> fetchModKaprekarList(int start, int end) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = start; i <= end; i++) {

            long sqr = Long.valueOf((long) Math.pow(i, 2));
            String sqrStr = Long.toString(sqr);
            long num1 = 0;
            try {
                num1 = Long.parseLong(sqrStr.substring(0, sqrStr.length() / 2));
            } catch (NumberFormatException e) {

            }
            long num2 = 0;
            try {
                num2 = Long.parseLong(sqrStr.substring(sqrStr.length() / 2));
            } catch (NumberFormatException e) {

            }
            if (num1 + num2 == i) {
                result.add(i);
            }

        }
        return result;
    }


}
