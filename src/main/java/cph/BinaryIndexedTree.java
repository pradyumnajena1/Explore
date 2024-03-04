package cph;

public class BinaryIndexedTree {
    private final int[] indexedTree;

    public BinaryIndexedTree(int[] values) {
        for(int i=1;i< values.length;i++){
            values[i] = values[i]+values[i-1];
        }
        this. indexedTree = new int[values.length+1];
        for(int i=1;i<= values.length;i++){
            int range = Integer.lowestOneBit(i);
           this. indexedTree[i] = getRangeSum(values, i-range,i-1);
        }

    }

    public int sum(int lower, int higher) {
        if (lower < 0 || higher < 0 || lower >= indexedTree.length - 1 || higher >= indexedTree.length - 1 || lower > higher) {
            throw new IllegalArgumentException(String.format("invalid lower  %s higher %s", lower, higher));
        }
        return sum(higher) - sum(lower - 1);
    }

    public void increment(int k, int x) {
        k = k + 1;
        while (k < indexedTree.length) {
            indexedTree[k] += x;
            k = k + Integer.lowestOneBit(k);
        }
    }

    private int sum(int k) {
        int sum = 0;
        k = k + 1;
        while (k >= 1) {
            sum += indexedTree[k];
            k = k - Integer.lowestOneBit(k);
        }
        return sum;
    }

    private int getRangeSum(int[] values, int lower, int higher) {
        return values[higher] - (lower - 1 >= 0 ? values[lower - 1] : 0);
    }
}
