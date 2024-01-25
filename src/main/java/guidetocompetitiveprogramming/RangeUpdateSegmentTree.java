package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.List;

public class RangeUpdateSegmentTree {
    private final SegmentTree segmentTree;

    public RangeUpdateSegmentTree(List<Integer> values) {
        this(values.stream().mapToInt(Integer::intValue).toArray());
    }
    public RangeUpdateSegmentTree(int[] values) {
        ArrayUtils.printArray(values);
        for(int i = values.length-1;i>=1;i--){
            values[i] = values[i]-values[i-1];
        }
        this.segmentTree = new SegmentTree(values, Integer::sum, 0);
    }


    public static void main(String[] args) {
        RangeUpdateSegmentTree rangeUpdate = new RangeUpdateSegmentTree(new int[]{3, 3, 1, 1, 1, 5, 2, 2});
        rangeUpdate.rangeUpdate(1,4,2);
        System.out.println(rangeUpdate.getValue(1));
        System.out.println(rangeUpdate.getValue(3));
    }

    public void rangeUpdate(int lower, int higher, int delta) {
        if (lower < 0 || higher < 0 || lower >= segmentTree.getSize() - 1 || higher >= segmentTree.getSize() - 1 || lower > higher) {
            throw new IllegalArgumentException(String.format("invalid lower  %s higher %s", lower, higher));
        }
        segmentTree.increment(lower, delta);
        if (higher < segmentTree.getSize() - 1) {

            segmentTree.increment(higher + 1, -delta);
        }
    }

    public int getValue(int index) {
        if (index < 0 || index >= segmentTree.getSize() - 1) {
            throw new IllegalArgumentException(String.format("invalid index  %s", index));
        }
        return segmentTree.rangeResult(0, index);
    }
}
