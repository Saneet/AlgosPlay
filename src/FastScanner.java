import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Fast Scanner using Buffered Reader
 */
public class FastScanner {

    private static BufferedReader reader;
    private static StringTokenizer tokenizer;

    //Get next string token
    public static String next() throws IOException {
        if (reader == null) {
            reader = new BufferedReader(
                    new InputStreamReader(System.in) );
            tokenizer = new StringTokenizer("");
        }
        if (!tokenizer.hasMoreTokens()) {
            //Get the next line of input
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    //Get next integer
    public static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    //Get next double
    public static double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }
}
