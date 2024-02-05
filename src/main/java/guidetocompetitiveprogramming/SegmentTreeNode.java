package guidetocompetitiveprogramming;

public class SegmentTreeNode {
    int sum;
    int min;
    int pendingSumUpdate;
    int pendingDivUpdate;

    public SegmentTreeNode() {
        sum = 0;
        min = Integer.MAX_VALUE;
    }

    public SegmentTreeNode(int value) {
        this.sum = value;
        this.min = value;
    }

    public SegmentTreeNode(int sum, int min) {
        this.sum = sum;
        this.min = min;
    }

    public int getSum() {
        return sum;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "{" +
                "s:" + sum +
                ",m:" + min +
                ",ps:" + pendingSumUpdate +
                ",pd:" + pendingDivUpdate +
                '}';
    }
}
