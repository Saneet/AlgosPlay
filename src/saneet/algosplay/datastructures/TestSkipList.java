package saneet.algosplay.datastructures;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Test code for testing skip list. Shows statistics
 */
public class TestSkipList{
    public static void testSkipList(){
        //Testing skip list

        //Range of numbers to use.
        int end = 239423445;
        int start = end / 2;
        //Create array of the size needed.
        int[] array = new int[100000000];
        Random r  = new Random(124173423);

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(end - start) + start;
        }

        long startTime = System.nanoTime();
        SkipList<Integer> skipList = new SkipList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            skipList.addNode(array[i]);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Add Time: " + duration);

        System.out.println("Nodes At Levels: ");
        System.out.println(Arrays.toString(skipList.nodesAtLevels));


        startTime = System.nanoTime();
        int incorrectFetches = 0;
        for (int i = 0; i < array.length; i++) {
            SkipList<Integer>.LinkedListNode<Integer> node = skipList.findNode(array[i]);
            if (!(node != null && node.getValue() == array[i])) {
                incorrectFetches++;
            }
        }

        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        System.out.println("Fetches Total Time: " + duration);
        System.out.println("Incorrect Fetches: " + incorrectFetches);

        try {
            startTime = System.nanoTime();
            int incorrectSorting = 0;
            int nodeCount = -1;
            SkipList<Integer>.LinkedListNode<Integer> node = skipList.getFirstNode();
            SkipList<Integer>.LinkedListNode<Integer> node2 = skipList.getNextNode(node);
            while(node2 == null){
                if (node.compareTo(node2) > 0) {
                    incorrectSorting++;
                }
                node = node2;
                node2 = skipList.getNextNode(node);
                nodeCount++;
            }
            endTime = System.nanoTime();
            duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
            System.out.println("Iteration Total Time: " + duration);
            System.out.println("Incorrect Sorting: " + incorrectSorting);
            System.out.println("Node count: " + nodeCount);
        } catch (IncorrectListException e) {
            e.printStackTrace();
        }

        deleteTest();

    }

    public static void deleteTest() {

        class DeleteTestNodeData implements Comparable<DeleteTestNodeData> {
            private String value;
            private int id;

            public DeleteTestNodeData(String value, int id) {
                this.value = value;
                this.id = id;
            }

            @Override
            public int compareTo(DeleteTestNodeData o) {
                return value.compareTo(o.value);
            }

            @Override
            public String toString() {
                return value + '/' + id;
            }
        }


        int chars = 26;
        int nodeNum = 4;
        SkipList<DeleteTestNodeData> skipList = new SkipList<DeleteTestNodeData>(chars*nodeNum);
        DeleteTestNodeData[] items = new DeleteTestNodeData[chars * nodeNum];

        int id = 0;
        for (int i = 'a'; i <= 'a' + chars - 1; i++) {
            for (int j = 0; j < nodeNum; j++) {
                DeleteTestNodeData item = new DeleteTestNodeData("" + (char) i, id);
                skipList.addNode(item);
                items[id++] = item;
            }
        }

        skipList.printAll(' ');
        System.out.println("");

        for (int i = 0; i < items.length; i++) {
            System.out.println("Deleting: " + items[i]);
            skipList.deleteNode(items[i]);
            skipList.printAll(' ');
            System.out.println("");

        }

    }

}
