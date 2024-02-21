package guidetocompetitiveprogramming;

public class SegmentTreeNode {
    int sum;
    int pendingSumUpdate;
    public SegmentTreeNode() {
        sum = 0;
    }

    public SegmentTreeNode(int value) {
        this.sum = value;
    }
    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "{" +
                "s=" + sum +
                ", ps=" + pendingSumUpdate +
                '}';
    }
}
