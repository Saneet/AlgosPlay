package saneet.algosplay.datastructures;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Sorted Skip List implementation for quick search and add, delete operations.
 */
public class SkipList<T extends Comparable<T>> {
    private int nodeCount = 0;
    private SkipNode<T> startNode = null;
    private int levelCount;
    int[] nodesAtLevels;

    //initialize the first nodes for all levels
    private void initialize() {
        SkipNode<T> node = new SkipNode<T>(this, null, null);
        startNode = node;
        for (int i = 0; i < levelCount - 1; i++) {
            SkipNode<T> temp = new SkipNode<T>(this, null, null);
            node.down = temp;
            node = temp;
        }
        LinkedListNode<T> temp = new LinkedListNode<T>(this, null, null);
        node.down = temp;

        nodesAtLevels = new int[levelCount];
    }

    //Initialize levels according to expected elements
    public SkipList(int expectedElementCount) {
        //Initialize levels to what the height of an equivalent binary tree would be
        levelCount = (int) (Math.log(expectedElementCount) / Math.log(2)) + 1;
        initialize();
    }

    public SkipList() {
        levelCount = 20;
        initialize();
    }

    //Previous and next is managed by the list class so that nodes and their lists always have to go together
    public LinkedListNode<T> getPreviousNode(LinkedListNode<T> node) {
        if (node == null || node.list() != this) {
            return null;
        } else
            return node.prev;
    }

    //Previous and next is managed by the list class so that nodes and their lists always have to go together
    public LinkedListNode<T> getNextNode(LinkedListNode<T> node) {
        if (node == null || node.list() != this) {
            return null;
        } else
            return node.next;
    }

    //Node search
    public LinkedListNode<T> findNode(T value) {

        LinkedListNode<T> node = findNearestNode(value, null);
        if (node.compareTo(value) != 0) {
            node = null;
        }
        return node;

    }

    //Adds a new node to the sorted list
    public LinkedListNode<T> addNode(T value) {
        //While adding node we need to decide the number of levels.
        //The probability of next level existing over previous level can 1/2. Ideal is 1/3. Theoretically ideal is 1/e.
        //We can achieve this with the bits trick. With 1 bit you can store 1 number and with the 2nd bit you can store
        // 3 - 1 = 2 numbers. With the 3rd bit you can store 7 - 3 = 4 number. With the 4th bit you have 15 - 7 = 8 numbers.
        // Similarly 5th bit gets 16, 6th gets 32 numbers. So if you get a random number upto 10 bits then each left bit will have a
        // twice the probability of being 1 than the right side bit as it denotes twice as many numbers that the random number can generate.
        // We can use the leftmost bit position for knowing how many levels to create.

        //We'll use BigInteger as we have no limit to the number of levels.
        //We will calculate a random big integer with number of bits as number of levels and the more zeros the random number has from
        // the left the more levels we'll have for the value added to the skip list
        BigInteger bits = new BigInteger(levelCount, new Random());

        int levelsToPopulate = levelCount - bits.bitLength();

        LinkedListNode<T>[] nodesList = new LinkedListNode[levelCount];
        findNearestNode(value, nodesList);

        //Add the actual node
        LinkedListNode<T> prevNode = nodesList[levelCount - 1];
        LinkedListNode<T> node = new LinkedListNode<T>(this, prevNode, value);

        //If nodes with the same value already exist in the list then do not add skipnodes
        if (prevNode.compareTo(node) != 0) {
            //Add all the skip nodes
            LinkedListNode<T> prev = node;
            SkipNode<T> skipNode;
            for (int i = nodesList.length - 2; i >= (levelCount - levelsToPopulate - 1) && i >= 0; i--) {
                skipNode = new SkipNode<T>(this, nodesList[i], value);
                skipNode.down = prev;
                prev = skipNode;
                nodesAtLevels[i]++;
            }
        }

        nodesAtLevels[levelCount - 1]++;
        nodeCount++;

        return node;
    }

    private void deleteNode(T value, LinkedListNode<T> node) {
        if (value == null) {
            return;
        }

        //Our list can have duplicate elements. If an element that occurs multiple times has skip nodes then they will
        //point to the first element. We have to make sure that while deleting we point the skip nodes to the next element.

        //Get node path
        LinkedListNode<T>[] nodePath = new LinkedListNode[levelCount];
        findNearestNode(value, nodePath);

        LinkedListNode<T> foundNode = nodePath[levelCount - 1];
        //found node could be the nearest node and not the actual node
        if (foundNode == null || foundNode.compareTo(value) != 0) {
            return;
        }

        LinkedListNode<T> nextNode = foundNode.next;
        SkipNode<T> lastSkipNode = (SkipNode<T>) nodePath[levelCount - 2];
        boolean hasSkipNode = lastSkipNode != null && lastSkipNode.compareTo(value) == 0;

        //If the node being deleted is a skip node and if the next node also has the same value then point the skip list
        //to the next node otherwise delete the skip nodes.
        if (node != null) {
            if (foundNode != node) {
                foundNode = node;
                hasSkipNode = false;
            } else if (hasSkipNode && nextNode != null && nextNode.compareTo(foundNode) == 0) {
                lastSkipNode.down = nextNode;
                hasSkipNode = false;
            }
        } else if (hasSkipNode && nextNode != null && nextNode.compareTo(foundNode) == 0) {
            foundNode = nextNode;
            hasSkipNode = false;
        }

        foundNode.remove();
        if (hasSkipNode) {
            for (int i = nodePath.length - 1; i >= 0; i--) {
                if (nodePath[i] != null && nodePath[i].value != null) {
                    nodePath[i].remove();
                } else {
                    break;
                }
            }
        }

        nodeCount--;


    }

    public void deleteNode(LinkedListNode<T> node) {
        if (node.list() != this) {
            return;
        }

        deleteNode(node.value, node);
    }

    public void deleteNode(T value) {
        deleteNode(value, null);
    }

    public LinkedListNode<T> getFirstNode() {
        LinkedListNode<T> node = startNode;
        while (node instanceof SkipNode)
            node = ((SkipNode) node).down;
        return node;
    }

    //Goes through the structure and finds the previous node or the correct node on each level
    private LinkedListNode<T> findNearestNode(T value, LinkedListNode<T>[] nodePath) {

        LinkedListNode<T> node = startNode;
        int i = 0;
        while (node instanceof SkipNode) {
            node = ((SkipNode) node).down;

            LinkedListNode<T> prev = node;
            while (true) {
                int result = node.compareTo(value);

                if (result == 0) {
                    break;
                } else if (result > 0) {
                    node = prev;
                    break;
                } else if (node.next == null) {
                    break;
                } else {
                    prev = node;
                    node = node.next;
                }
            }

            if (nodePath != null) {
                nodePath[i++] = node;
            }

        }

        return node;
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[nodeCount];
        LinkedListNode<T> node = getFirstNode();

        int index = 0;
        node = getNextNode(node);
        while (node != null) {
            result[index++] = node.value;
            node = getNextNode(node);
        }

        return result;
    }

    public void printAll(char delimiter) {

        LinkedListNode<T> node = getFirstNode();
        node = getNextNode(node);
        while (node != null) {
            System.out.print(node);
            System.out.print(delimiter);
            node = getNextNode(node);
        }

    }

    /*
      LinkedListNode class
     */
    public class LinkedListNode<T extends Comparable<T>> implements Comparable<LinkedListNode<T>> {
        private LinkedListNode<T> prev;
        private LinkedListNode<T> next;
        private final T value;

        private final SkipList list;

        public T getValue() {
            return value;
        }

        public boolean belongsToList(SkipList list) {
            return list == this.list;
        }

        private LinkedListNode(SkipList list, LinkedListNode<T> prev, T value) {
            this.list = list;

            this.value = value;

            this.prev = prev;
            if (prev == null) {
                return;
            }

            LinkedListNode<T> temp = prev.next;
            prev.next = this;
            this.next = temp;
            if (temp != null) {
                temp.prev = this;
            }
        }

        private SkipList list() {
            return SkipList.this;

        }

        private void remove() {
            if (prev != null) {
                prev.next = next;
            }

            if (next != null) {
                next.prev = prev;
            }
        }


        @Override
        public int compareTo(LinkedListNode<T> o) {
            if (value == null) {
                return -1;
            } else if (o == null || o.getValue() == null) {
                return 1;
            } else {
                o.getValue();
                return value.compareTo(o.getValue());
            }
        }

        public int compareTo(T value) {
            if (this.value == null) {
                return -1;
            } else if (value == null) {
                return 1;
            } else {
                return this.value.compareTo(value);
            }
        }

        @Override
        public String toString() {
            if (value != null) {
                return value.toString();
            }
            else return super.toString();
        }

    }

    /*
      SkipNode class
     */
    private class SkipNode<T extends Comparable<T>> extends LinkedListNode<T> {
        private LinkedListNode<T> down;

        private SkipNode(SkipList list, LinkedListNode<T> prev, T value) {
            super(list, prev, value);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }


}
