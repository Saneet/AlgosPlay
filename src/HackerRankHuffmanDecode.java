/**
 * HackerRank problem Huffman Decode
 */
public class HackerRankHuffmanDecode {


    void decode(String S ,Node root)
    {
        Node node = root;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '0') {
                node = root.left;
            } else if (S.charAt(i) == '1') {
                node = root.right;
            }
            if (node.data != 0) {
                builder.append(node.data);
                node = root;
            }
        }

        System.out.println(builder.toString());
    }
}

class Node{
    public int freq;
    public char data;
    public Node left;
    public Node right;

}