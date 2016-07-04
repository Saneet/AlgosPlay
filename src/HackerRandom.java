import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Hacker Rank problem solutions
 */
public class HackerRandom {

    //Hacker Rank Sparse Arrays - Solved using Hashmap of subtractions
    public static int[] sparseArrays(String[] strings, String[] queries){
        int[] results = new int[queries.length];
        HashMap<String, Integer> map = new HashMap<>(strings.length, 1);

        for (String string : strings) {
            int count = map.getOrDefault(string, 0);
            map.put(string, ++count);
        }

        for (int i = 0; i < queries.length; i++) {
            results[i] = map.getOrDefault(queries[i], 0);
        }


        return results;

    }

    //Hacker Rank Sherlock and Array solution
    public static void sherlockAndArray() throws IOException {

        int testCount = FastScanner.nextInt();
        for (int i = 0; i < testCount; i++) {
            int left = 0;
            int right = 0;
            int prevNum = 0;

            int arrCount = FastScanner.nextInt();
            int[] arr = new int[arrCount];

            for (int j = 0; j < arr.length; j++) {
                arr[j] = FastScanner.nextInt();
                right += arr[j];
            }

            int j;
            for (j = 0; j < arr.length; j++) {
                left += prevNum;
                right -= arr[j];

                if (left == right) {
                    System.out.println("YES");
                    break;
                }

                prevNum = arr[j];
            }

            if (j >= arr.length) {
                System.out.println("NO");
            }
        }


    }

    //Hacker Rank Ice Cream Parlour - Solution using hashmap
    public static void iceCreamParlour() throws IOException {
        int testCount = FastScanner.nextInt();
        for (int i = 0; i < testCount; i++) {

            int M = FastScanner.nextInt();
            int arrCount = FastScanner.nextInt();
            HashMap<Integer, Integer> map = new HashMap<>(arrCount, 1);

            int[] arr = new int[arrCount];

            boolean found = false;
            for (int j = 0; j < arr.length; j++) {
                int val = FastScanner.nextInt();

                if (!found) {
                    int a = M - val;
                    if (a > 0) {
                        if (map.containsKey(val)) {
                            System.out.println((map.get(val) + 1) + " " + (j + 1));
                            found = true;
                        }
                        map.put(a, j);
                    }
                }
            }

        }

    }

    //Hacker Rank Maximum SubArray - Solution using modified Kandane's algorithm
    public static void maximumSubArray() throws Exception {
        int testCaseCount = FastScanner.nextInt();

        for (int i = 0; i < testCaseCount; i++) {
            int arrCount = FastScanner.nextInt();

            int totalMax = FastScanner.nextInt();
            int currentMax = totalMax;
            int justMax = currentMax;

            for (int j = 0; j < arrCount - 1; j++) {
                int val = FastScanner.nextInt();
                currentMax = Math.max(val, currentMax + val);
                totalMax = Math.max(currentMax, totalMax);
                justMax = Math.max(justMax, justMax + val);
                justMax = Math.max(justMax, val);
            }

            System.out.println(totalMax + " " + justMax);
        }
    }

    //Hacker Rank Binary Search Tree : Lowest Common Ancestor - solution
    static Node lca(Node root,int v1,int v2) {
        List<Node> v1Nodes = new ArrayList<>();
        List<Node> v2Nodes = new ArrayList<>();
        class Finder {
            public boolean find(Node root, int value, List<Node> nodesList) {
                if (root.data == value) {
                    nodesList.add(root);
                    return true;
                }
                boolean result = false;
                if (root.left != null) {
                    result = find(root.left, value, nodesList);
                }
                if (result == false && root.right != null) {
                    result = find(root.right, value, nodesList);
                }

                if (result == true) {
                    nodesList.add(root);
                }
                return result;
            }
        }

        new Finder().find(root, v1, v1Nodes);
        new Finder().find(root, v2, v2Nodes);


        int j = v2Nodes.size() - 1;
        Node lastCommonNode = null;
        for (int i = v1Nodes.size() - 1; i >= 0 && j >= 0; i--, j--) {
            if (v1Nodes.get(i).data == v2Nodes.get(j).data) {
                lastCommonNode = v1Nodes.get(i);
            }
        }

        return lastCommonNode;
    }


    public static void palindromeIndex() throws IOException {
        int testCount = FastScanner.nextInt();

        for (int i = 0; i < testCount; i++) {
            StringBuilder str1 = new StringBuilder(FastScanner.next());
            int j, k;
            for (j = 0, k = str1.length() - 1; j < k; j++, k--) {
                if (str1.charAt(j) != str1.charAt(k)) {
                    if (str1.charAt(j + 1) == str1.charAt(k)) {
                        System.out.println(j);
                    } else if (str1.charAt(j) == str1.charAt(k - 1)) {
                        System.out.println(str1.length() - k);
                    } else {
                        System.out.println("what???");
                    }

                }
            }
            if (j==k) {
                System.out.println("-1");
            }


        }
    }


}
