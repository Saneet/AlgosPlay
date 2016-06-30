import java.math.BigInteger;

/**
 * Implementation of Fenwick Tree.
 */
public class FenwickTree {

    /**
     * Parent can be obtained by removing the last set bit from index
     * 1) 2's complement to get minus of index
     * 2) AND this with index
     * 3) Subtract that from index
     */
    private static int getParentIndex(int index){
        return index - (index & -index);
    }

    /**
     * Next node can be obtained by moving ahead on parent
     * 1) 2's complement of get minus of index
     * 2) AND this with index
     * 3) Add it to index
     */
    private static int getNextIndex(int index){
        return index + (index & -index);
    }

    //Create blank tree and keep adding value at every index
    public static int[] createTree(int input[]){
        int binaryIndexedTree[] = new int[input.length+1];
        for(int i=1; i <= input.length; i++){
            updateAddValuetoIndex(binaryIndexedTree, i, input[i-1]);
        }
        return binaryIndexedTree;
    }

    //Start from index+1. Keep adding this value for next node till end of tree.
    public static void updateAddValuetoIndex(int tree[], int index, int value){
        while(index < tree.length){
            tree[index] += value;
            index = getNextIndex(index);
        }
    }

    //Start from index+1 keep adding till root.
    public static int getSumToIndex(int binaryIndexedTree[], int index){
        index++;
        int sum = 0;
        while(index > 0){
            sum += binaryIndexedTree[index];
            index = getParentIndex(index);
        }
        return sum;
    }

    //Start from index+1 keep adding till root. Returns BigInteger for times when int is just not enough.
    public static BigInteger getBigSumToIndex(int binaryIndexedTree[], int index){
        index++;
        BigInteger sum = BigInteger.valueOf(0);
        while(index > 0){
            sum = sum.add(BigInteger.valueOf(binaryIndexedTree[index]));
            index = getParentIndex(index);
        }
        return sum;
    }
}