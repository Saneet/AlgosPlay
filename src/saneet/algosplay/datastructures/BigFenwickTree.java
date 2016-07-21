package saneet.algosplay.datastructures;

import java.math.BigInteger;

/**
 * Implementation of Fenwick Tree with BigIntegers.
 */
public class BigFenwickTree {

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
    public static BigInteger[] createTree(BigInteger input[]){
        BigInteger binaryIndexedTree[] = new BigInteger[input.length+1];
        for (int i = 0; i < binaryIndexedTree.length; i++) {
            binaryIndexedTree[i] = BigInteger.ZERO;
        }
        for(int i=1; i <= input.length; i++){
            updateAddValuetoIndex(binaryIndexedTree, i, input[i-1]);
        }
        return binaryIndexedTree;
    }

    //Start from index+1. Keep adding this value for next node till end of tree.
    public static void updateAddValuetoIndex(BigInteger tree[], int index, BigInteger value){
        while(index < tree.length){
            tree[index] = tree[index].add(value);
            index = getNextIndex(index);
        }
    }

    //Start from index+1 keep adding till root. Returns BigInteger for times when int is just not enough.
    public static BigInteger getSumToIndex(BigInteger binaryIndexedTree[], int index){
        index++;
        BigInteger sum = BigInteger.valueOf(0);
        while(index > 0){
            sum = sum.add(binaryIndexedTree[index]);
            index = getParentIndex(index);
        }
        return sum;
    }
}