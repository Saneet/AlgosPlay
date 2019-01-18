package saneet.algosplay.hackerrank;

import saneet.algosplay.utils.FastScanner;

import java.io.IOException;
import java.util.*;

/**
 * Hacker Rank problem solutions
 */
public class OtherRandom {

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


    public static void sherlockAndTheBeast() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            int n = in.nextInt();
            int mod = n % 3;
            int n3 = 0;
            int n5 = 0;
            switch (mod) {
                case 0: {
                    n5 = n;
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    n5 = n - 5;
                    n3 = 5;
                    break;
                }
                default: {
                    System.out.print("-1");
                    System.out.println("");
                    continue;

                }
            }

            for (int i = 0; i < n5; i++) {
                System.out.print("5");
            }
            for (int i = 0; i < n3; i++) {
                System.out.print("3");
            }
            System.out.println("");

        }
    }

    public static int minimumDistances(int[] arr) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Integer index = map.getOrDefault(arr[i], -1);
            if (index > -1) {
                int newVal = (i - index);
                if (newVal < minDist) {
                    minDist = newVal;
                }
            }
            map.put(arr[i], i);
        }

        if (minDist == Integer.MAX_VALUE) {
            minDist = -1;
        }

        return minDist;
    }

    public static int findDigits(int N) {
        int num = N;
        int count = 0;
        while (num != 0) {
            int i = num % 10;
            if (i > 0 && N % i == 0) {
                count++;
            }
            num = num / 10;
        }
        return count;
    }

    public static int fairRations() {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int count = 0;

        if (N <= 0) {
            return 0;
        }

        int prev = in.nextInt();
        for(int B_i=1; B_i < N; B_i++){
            int current = in.nextInt();
            if (prev % 2 != 0) {
                current++;
                count += 2; //increment both numbers
            }
            prev = current;
        }

        if (prev % 2 != 0) {
            return -1;
        } else {
            return count;
        }
    }

    public static int sherlockAndSquares(long start, long end) {
        int startRoot = (int) Math.ceil(Math.sqrt(start));
        long squareVal = startRoot * startRoot;

        int count = 0;
        while (squareVal <= end) {
            count++;
            startRoot++;
            squareVal = startRoot * startRoot;
        }

        return count;
    }

    public static int utopianTree(int cycles) {
        if (cycles % 2 != 0) {
            return (int) Math.pow(2, ((cycles + 1) / 2 + 1)) - 2;
        } else {
            return (int) Math.pow(2, ((cycles) / 2 + 1)) - 1;
        }
    }

    public static String minMaxSum(int[] num) {
        Arrays.sort(num);
        long sum = (long) num[1] + num[2] + num[3];
        return Long.toString((long) num[0] + sum) + " " + Long.toString((long) num[4] + sum);
    }
}
