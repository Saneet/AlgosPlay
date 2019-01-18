package saneet.algosplay.ctci;

public class CheckBalanced {
    public static void main(String... args) {
        test1();
    }

    private static void test1() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.left = node5;
        node3.right = node6;
        node6.left = node7;
        node6.right = node8;

        System.out.println("Result: " + checkBalancedAndReturnHeight(node1));
    }

    public static int checkBalancedAndReturnHeight(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkBalancedAndReturnHeight(root.left);
        int rightHeight = checkBalancedAndReturnHeight(root.right);

        if (leftHeight == Integer.MIN_VALUE || rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (leftHeight > rightHeight) {
            if (leftHeight - rightHeight > 1) {
                return Integer.MIN_VALUE;
            } else {
                return leftHeight + 1;
            }
        } else {
            if (rightHeight - leftHeight > 1) {
                return Integer.MIN_VALUE;
            } else {
                return rightHeight + 1;
            }
        }
    }

    private static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }
}
