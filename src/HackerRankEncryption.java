/**
 * HackerRank problem - Encryption
 */
public class HackerRankEncryption {

    public static String getEncryptedString(String str) {
        int row, column;
        int length;
        length = str.length();
        row = (int) Math.floor(Math.sqrt(length));
        column = (int) Math.ceil(Math.sqrt(length));
        if (row * column < length){
            row = column;
        }

        StringBuilder builder = new StringBuilder();
        int x;
        for (int i = 0; i < column; i++) {
            for (int j = 0; (j < row); j++) {
                x = (j * column) + i;
                builder.append(str.charAt(x));
            }
            builder.append(" ");
        }
        return builder.toString();
    }
}
