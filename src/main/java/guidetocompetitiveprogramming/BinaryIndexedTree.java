package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class BinaryIndexedTree {


    int[] binaryIndexedTree;

    public BinaryIndexedTree(int[] values) {

        ArrayUtils.printArray(values);
        for (int i = 1; i < values.length; i++) {
            values[i] = values[i - 1] + values[i];
        }

        this.binaryIndexedTree = new int[values.length + 1];
        for (int i = 1; i < binaryIndexedTree.length; i++) {
            int range = Integer.lowestOneBit(i);
            binaryIndexedTree[i] = getRangeSum(values, i - range, i - 1);
        }
        printTree();
    }

    private static int getRangeSum(int[] values, int lower, int higher) {
        return values[higher] - (lower - 1 >= 0 ? values[lower - 1] : 0);
    }

    private void printTree() {
        ArrayUtils.printArray(binaryIndexedTree);
    }

    public int sum(int lower, int higher) {
        if (lower < 0 || higher < 0 || lower >= binaryIndexedTree.length - 1 || higher >= binaryIndexedTree.length - 1 || lower > higher) {
            throw new IllegalArgumentException(String.format("invalid lower  %s higher %s", lower, higher));
        }
        return sum(higher) - sum(lower - 1);
    }

    /**
     * return sum of elements 0 to k
     *
     * @param k
     * @return sum of elements 0 to k
     */
    public int sum(int k) {
        int sum = 0;
        k = k + 1;
        while (k >= 1) {
            sum += binaryIndexedTree[k];
            k = k - Integer.lowestOneBit(k);
        }
        return sum;
    }

    public void increment(int k, int x) {
        k = k + 1;
        while (k < binaryIndexedTree.length) {
            binaryIndexedTree[k] += x;
            k = k + Integer.lowestOneBit(k);
        }
    }

    public static void main(String[] args) {
        BinaryIndexedTree binaryIndexedTree = new BinaryIndexedTree(new int[]{1, 3, 4, 8, 6, 1, 4, 2});
        System.out.println(binaryIndexedTree.sum(1, 4));
        System.out.println(binaryIndexedTree.sum(0, 4));
        binaryIndexedTree.increment(2, 2);
        binaryIndexedTree.printTree();
        System.out.println(binaryIndexedTree.sum(0, 4));
    }
}
