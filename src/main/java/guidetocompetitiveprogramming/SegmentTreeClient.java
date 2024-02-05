package guidetocompetitiveprogramming;

import commons.IntegerUtils;
import epp.array.ArrayUtils;

import java.util.List;
import java.util.function.BiFunction;

public class SegmentTreeClient {


    public static void main(String[] args) {
        SegmentTree  segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Integer::sum, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 5);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Math::min, Integer.MAX_VALUE);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, -5);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{5, 8, 6, 3, 2, 7, 2, 6}, Math::max, Integer.MIN_VALUE);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, IntegerUtils::gcd, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x & y, -1);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));


        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x | y, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));

        segmentTree = new SegmentTree(new int[]{15, 25, 9, 3, 12, 27, 21, 16}, (x, y) -> x ^ y, 0);
        System.out.println(segmentTree.rangeResult(1, 5));
        segmentTree.increment(3, 6);
        System.out.println(segmentTree.rangeResult(1, 5));
    }


}
