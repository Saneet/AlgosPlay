package saneet.algosplay.ctci;

import java.util.*;

public class BstFromSortedArray {

    //Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height

    public static void main(String... args) {
        test2();
    }

    private static void test1() {
        int[] array = new int[]{1, 3, 6, 7, 12};
        printTree(createBST(array, 0, array.length - 1));
    }

    private static void test2() {
        int[] array = new int[]{1, 3, 6, 7};
        printTree(createBST(array, 0, array.length - 1));
    }

    private static Node createBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        //recursive function
        //parameters: rootNode, arr, start and end
        //find center and all node: (start + end) / 2
        //call function for left side and function for right side
        int middle = (start + end) / 2;

        Node node = new Node(arr[middle]);
        node.left = createBST(arr, start, middle - 1);
        node.right = createBST(arr, middle + 1, end);
        return node;
    }

    private static void printTree(Node root) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        queue.add(new Node(-1));
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.data == -1 && queue.size() > 0) {
                System.out.println();
                queue.add(node);
                continue;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            System.out.print(node.data + "\t");
        }
    }

    private static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}

