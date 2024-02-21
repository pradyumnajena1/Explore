package guidetocompetitiveprogramming;

import java.util.List;

public class LazySegmentTree {
    private final SegmentTreeNode[] segmentTree;
    private final int size;

    public LazySegmentTree(List<Integer> values) {
        this(values.stream().mapToInt(Integer::intValue).toArray());
    }

    public LazySegmentTree(int[] values) {
        this.size = nextPowerOf2(values.length);
        this.segmentTree = new SegmentTreeNode[2 * size];
        construct(values);
        printTree();

    }

    private void construct(int[] values) {
        construct(values, 1, 0, size - 1);
    }

    private void construct(int[] values, int si, int ss, int se) {
        if (ss > se) {
            return;
        }
        segmentTree[si] = new SegmentTreeNode();
        if (ss == se) {
            segmentTree[si].sum = ss < values.length ? values[ss] : 0;


        } else {
            int mid = (ss + se) / 2;
            construct(values, 2 * si, ss, mid);
            construct(values, 2 * si + 1, mid + 1, se);
            segmentTree[si].sum = segmentTree[2 * si].sum + segmentTree[2 * si + 1].sum;

        }

    }

    public void printTree() {

        StringBuilder sb = new StringBuilder();
        int a = size;
        int b = 2 * size - 1;
        while (a >= 1 && a <= b) {
            sb.append("[");
            for (int i = a; i <= b; i += 2) {
                sb.append(segmentTree[i]).append(",");
                if (a != b) {
                    sb.append(segmentTree[i + 1]).append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            a = a / 2;
            b = b / 2;
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    public void updateRange(int qs, int qe, int delta) {
        updateRange(qs, qe, delta, 1, 0, size - 1);
    }

    private void updateRange(int qs, int qe, int delta, int sIndex, int sts, int ste) {
        // System.out.println("updateRange qs = " + qs + ", qe = " + qe + ", delta = " + delta + ", increment = " + increment + ", sIndex = " + sIndex + ", sts = " + sts + ", ste = " + ste);
        //if already pending updates, fix current node and push pending value down to child nodes
        if (segmentTree[sIndex].pendingSumUpdate != 0) {
            pushPendingSum(sIndex, sts, ste);
        }
        if (sts > ste || qs > ste || qe < sts) {
            return;
        }
        if (qs <= sts && ste <= qe) {

            segmentTree[sIndex].sum += (ste - sts + 1) * delta;
            if (sts < ste) {
                segmentTree[sIndex * 2].pendingSumUpdate += delta;
                segmentTree[sIndex * 2 + 1].pendingSumUpdate += delta;
            }

            return;

        }
        int mid = (sts + ste) / 2;
        updateRange(qs, qe, delta, 2 * sIndex, sts, mid);
        updateRange(qs, qe, delta, 2 * sIndex + 1, mid + 1, ste);
        segmentTree[sIndex].sum = segmentTree[sIndex * 2].sum + segmentTree[sIndex * 2 + 1].sum;

    }


    public SegmentTreeNode rangeResult(int queryStart, int queryEnd) {
        return rangeResult(queryStart, queryEnd, 1, 0, size - 1);
    }

    private SegmentTreeNode rangeResult(int qs, int qe, int sIndex, int sts, int ste) {
        // System.out.println("rangeResult qs = " + qs + ", qe = " + qe + ", sIndex = " + sIndex + ", sts = " + sts + ", ste = " + ste);
        // if pending updates, fix current node and push pending value down to child nodes
        if (segmentTree[sIndex].pendingSumUpdate != 0) {
            pushPendingSum(sIndex, sts, ste);
        }
        if (sts > ste || qs > ste || qe < sts) {
            return new SegmentTreeNode();
        }
        if (qs <= sts && ste <= qe) {
            return segmentTree[sIndex];
        }
        int d = (sts + ste) / 2;
        SegmentTreeNode left = rangeResult(qs, qe, 2 * sIndex, sts, d);
        SegmentTreeNode right = rangeResult(qs, qe, 2 * sIndex + 1, d + 1, ste);
        return new SegmentTreeNode(left.sum + right.sum);
    }


    private void pushPendingSum(int sIndex, int sts, int ste) {

        segmentTree[sIndex].sum += (ste - sts + 1) * segmentTree[sIndex].pendingSumUpdate;

        if (sts < ste) {
            segmentTree[sIndex * 2].pendingSumUpdate = segmentTree[sIndex].pendingSumUpdate;
            segmentTree[sIndex * 2 + 1].pendingSumUpdate = segmentTree[sIndex].pendingSumUpdate;
        }
        segmentTree[sIndex].pendingSumUpdate = 0;
    }

    private static int nextPowerOf2(int n) {
        int highestOneBit = Integer.highestOneBit(n);
        return highestOneBit == n ? highestOneBit : highestOneBit << 1;
    }
}
