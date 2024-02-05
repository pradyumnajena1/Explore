package guidetocompetitiveprogramming;

public class LazySegmentTreeClient {
    public static void main(String[] args) {

        LazySegmentTree  segmentTree = new LazySegmentTree(new int[]{ 5, 8, 6, 3, 2, 7, 2, 6, 7, 1, 7, 5, 6, 2, 3, 2} );

         segmentTree.updateRange(5, 13,2,true);
         segmentTree.printTree();

        System.out.println(segmentTree.rangeResult(6, 6).sum);
        segmentTree.printTree();


    }
}
