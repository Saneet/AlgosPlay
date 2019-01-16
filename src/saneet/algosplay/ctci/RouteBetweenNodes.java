package saneet.algosplay.ctci;

import java.util.*;

public class RouteBetweenNodes {

    //Given a directed graph, design an algorithm to find out whether there is a route between two nodes

    public static void main(String... args) {
        new RouteBetweenNodes().test1();
    }

    private void test1() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.nodes.add(node2);
        node1.nodes.add(node3);
        node2.nodes.add(node4);
        node3.nodes.add(node5);
        node3.nodes.add(node6);
        node6.nodes.add(node2);
        node6.nodes.add(node3);
        node6.nodes.add(node7);
        node6.nodes.add(node8);
        node8.nodes.add(node3);

        System.out.println("Result: " + findRoute(node7, 4));
    }

    public boolean findRoute(Node root, int target) {
        if (root == null) {
            return false;
        }

        if (root.data == target) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        HashSet<Integer> seenNodes = new HashSet<>();
        queue.add(root);
        seenNodes.add(root.data);

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            for (Node child: node.nodes) {
                if (child.data == target) {
                    return true;
                } else if (!seenNodes.contains(child.data)) {
                    seenNodes.add(child.data);
                    queue.add(child);
                }
            }
        }

        return false;
    }

    private class Node {
        List<Node> nodes = new ArrayList<>();

        int data;

        Node(int data) {
            this.data = data;
        }
    }
}
