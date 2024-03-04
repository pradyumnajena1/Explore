package cph;

public class SegmentTreeRangeUpdate {
    private final SegmentTree segmentTree;

    public SegmentTreeRangeUpdate(int[] values) {
        int[] diff = new int[values.length];
        diff[0] = values[0];
        for(int i=1;i< values.length;i++){
            diff[i] = values[i]-values[i-1];
        }
        this. segmentTree = new SegmentTree(diff,Integer::sum,0);
    }

    public void rangeUpdate(int i,int j,int delta){
        segmentTree.increment(i,delta);
        segmentTree.increment(j+1,-delta);
    }
    public int getValue(int i){
        return segmentTree.rangeResult(0,i);
    }

    public static void main(String[] args) {
        SegmentTreeRangeUpdate segmentTreeRangeUpdate = new SegmentTreeRangeUpdate(new int[]{3,3,1,1,1,5,2,2});
        segmentTreeRangeUpdate.rangeUpdate(1,5,5);
        System.out.println(segmentTreeRangeUpdate.getValue(4));
    }
}
