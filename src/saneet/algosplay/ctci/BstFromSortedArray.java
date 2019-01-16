package saneet.algosplay.ctci;

import java.util.*;

public class BstFromSortedArray {

    //Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search tree with minimal height

    public static void main(String... args) {

    }

    private static void test1() {
        int[] array = new int[]{1, 3, 6, 7, 12};

    }

    private static void test2() {
        int[] array = new int[]{1, 3, 6, 7};
    }

    private Node createBST(int[] arr, int start, int end) {
        //recursive function
        //parameters: rootNode, arr, start and end
        //find center and all node: (start + end) / 2
        //call function for left side and function for right side
        int middle = (start + end) / 2;

        Node node = new Node(middle);
        node.left = createBST(arr, start, middle - 1);
        node.right = createBST(arr, middle + 1, end);
        return node;
    }

    private void printTree(Node root) {
        Queue<List<Node>> queue = new LinkedList<>();

        queue.add(Collections.singletonList(root));
        while (!queue.isEmpty()) {
            List<Node> list = queue.remove();

            for (Node node :
                    list) {
                
            }
            System.out.println(node.data);
            
        }
    }

    private class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}

