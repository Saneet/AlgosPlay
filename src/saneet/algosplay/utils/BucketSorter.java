package saneet.algosplay.utils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Bucket Sort implementation
 */
public class BucketSorter {
    private LinkedList<Integer>[] array;
    private int itemCount = 0;
    public int emptyBuckets = 0;
    public int insertComparisons = 0;

    public BucketSorter(int size) {
        array = new LinkedList[size];
    }

    public void putAtIndex(int index, int value){
        if (array[index] == null) {
            array[index] = new LinkedList<Integer>();
        }
        LinkedList<Integer> list = array[index];
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next() > value) {
                iterator.previous();
                break;
            }
            insertComparisons++;
        }

        iterator.add(value);
        itemCount++;
    }

    public int[] toArray() {
        emptyBuckets = 0;
        int[] results = new int[itemCount];
        int resultIndex = 0;
        int arrayIndex = 0;
        ListIterator<Integer> iterator = null;
        LinkedList<Integer> list;
        if (itemCount > 0) {
            while (resultIndex < itemCount) {
                if (iterator == null) {
                    list = array[arrayIndex];
                    if (list != null) {
                        iterator = list.listIterator();
                    } else {
                        emptyBuckets++;
                    }
                    arrayIndex++;
                } else if (iterator.hasNext()) {
                    results[resultIndex] = iterator.next();
                    resultIndex++;
                } else {
                    iterator = null;
                }
            }

            if (arrayIndex < array.length) {
                emptyBuckets += array.length - arrayIndex;
            }

        }
        return results;
    }

    public LinkedList<Integer> toLinkedList() {
        emptyBuckets = 0;
        LinkedList<Integer> results = new LinkedList<Integer>();

        int resultCount = 0;
        int arrayIndex = 0;
        ListIterator<Integer> iterator = null;
        LinkedList<Integer> list;
        if (itemCount > 0) {
            while (resultCount < itemCount) {
                if (iterator == null) {
                    list = array[arrayIndex];
                    if (list != null) {
                        iterator = list.listIterator();
                    } else {
                        emptyBuckets++;
                    }
                    arrayIndex++;
                } else if (iterator.hasNext()) {
                    results.addLast(iterator.next());
                    resultCount++;
                } else {
                    iterator = null;
                }
            }

            if (arrayIndex < array.length) {
                emptyBuckets += array.length - arrayIndex;
            }

        }
        return results;

    }


}
