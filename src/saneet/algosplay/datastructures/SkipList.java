package saneet.algosplay.datastructures;

import java.math.BigInteger;
import java.util.Random;

/**
 * Sorted Skip List implementation for quick search and add, delete operations.
 */
public class SkipList<T extends Comparable> {
    private int nodeCount = 0;
    private LinkedListNode startNode = null;
    private int levelCount;

    //initilize the first nodes for all levels
    private void initialize() {
        SkipNode node = new SkipNode<T>(this, null, null);
        startNode = node;
        for (int i = 0; i < levelCount - 1; i++) {
            SkipNode temp = new SkipNode<>(this, null, null);
            node.down = temp;
            node = temp;
        }
        LinkedListNode temp = new LinkedListNode(this, null, null);
        node.down = temp;
    }

    //Initialize levels according to expected elements
    public SkipList(int expectedElementCount) {
        levelCount = (int) (Math.log(expectedElementCount) / Math.log(2)) + 1;
        initialize();
    }

    public SkipList() {
        levelCount = 20;
        initialize();
    }

    //Previous and next is managed by the list class so that nodes and their lists always have to go together
    public LinkedListNode getPreviousNode(LinkedListNode node) throws IncorrectListException {
        if (node.list() == this) {
            return node.prev;
        } else {
            throw new IncorrectListException();
        }
    }

    //Previous and next is managed by the list class so that nodes and their lists always have to go together
    public LinkedListNode getNextNode(LinkedListNode node) throws IncorrectListException {
        if (node.list() == this) {
            return node.next;
        } else {
            throw new IncorrectListException();
        }
    }

    //Node search
    public LinkedListNode findNode(T value) {

        LinkedListNode node = findNearestNode(value, null);
        if (node.compareTo(value) != 0) {
            node = null;
        }
        return node;

    }

    //Adds a new node to the sorted list
    public LinkedListNode addNode(T value) {
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

        LinkedListNode[] nodesList = new LinkedListNode[levelCount];
        findNearestNode(value, nodesList);

        //Add the actual node
        LinkedListNode<T> node = new LinkedListNode<>(this, nodesList[levelCount - 1], value);

        //Add all the skip nodes
        for (int i = nodesList.length - 2; i >= (levelCount - levelsToPopulate - 1); i--) {
            node = new LinkedListNode<>(this, nodesList[i], value);
        }

        nodeCount++;

        return node;
    }

    //Goes through the structure and finds the previous node or the correct node on each level
    public LinkedListNode findNearestNode(T value, LinkedListNode[] nodePath) {

        LinkedListNode node = startNode;
        int i = 0;
        while (node instanceof SkipNode) {
            node = ((SkipNode) node).down;

            LinkedListNode prev = node;
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

    /*
      LinkedListNode class
     */
    public class LinkedListNode<T extends Comparable> implements Comparable<LinkedListNode> {
        private LinkedListNode prev;
        private LinkedListNode next;
        private final T value;
        private final SkipList list;

        public T getValue() {
            return value;
        }

        public boolean belongsToList(SkipList list) {
            return list == this.list;
        }

        private LinkedListNode(SkipList list, LinkedListNode prev, T value) {
            this.list = list;

            this.value = value;
            if (prev == null) {
                return;
            }

            this.prev = prev;
            LinkedListNode temp = prev.next;
            prev.next = this;
            this.next = temp;
            if (temp != null) {
                temp.prev = this;
            }
        }

        private SkipList list() {
            return SkipList.this;

        }

        private void remove(){
            if (prev != null) {
                prev.next = next;
            }

            if (next != null) {
                next.prev = prev;
            }
        }


        @Override
        public int compareTo(LinkedListNode o) {
            if (value == null) {
                return -1;
            } else if (o.getValue() == null) {
                return 1;
            } else {
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

    }

    /*
      SkipNode class
     */
    private class SkipNode<T extends Comparable> extends LinkedListNode<T> {
        private LinkedListNode down;
        private SkipNode(SkipList list, LinkedListNode prev, T value) {
            super(list, prev, value);
        }
    }

}

class IncorrectListException extends Exception{};