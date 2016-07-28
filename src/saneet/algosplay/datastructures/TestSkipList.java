package saneet.algosplay.datastructures;

import java.util.Arrays;
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


        //Delete nodes
        startTime = System.nanoTime();
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

    }


}
