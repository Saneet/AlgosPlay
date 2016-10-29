package saneet.algosplay.misc;


import java.util.*;

public class ClosestCommonAncestor {

    private Map<Integer, Node> parents = new HashMap<>();

    private Node commonAncestorNode;

    public int findClosestCommonAncestor(Node root, Node node1, Node node2) {
        //find path to node1
        boolean result = find(root, node1);
        if (!result) {
            return 0;
            //Invalid input
        }

        //find path to node2
        result = find(root, node2);

        if (commonAncestorNode == null) {
            return 0;
            //none found
        }

        return commonAncestorNode.data;
    }

    public boolean find(Node root, Node node){
        if (root.data == node.data) {
            if (parents.get(root.data) != null && commonAncestorNode == null) {
                commonAncestorNode = root;
            } else
                parents.put(root.data, root);
            return true;
        }

        for (int i = 0; i < root.children.size(); i++) {
            if (find(root.children.get(i), node)) {
                if (parents.get(root.data) != null && commonAncestorNode == null) {
                    commonAncestorNode = root;
                } else
                    parents.put(root.data, root);
                return true;
            }
        }

        return false;
    }

    class Node {
        int data;
        List<Node> children;
    }



}
