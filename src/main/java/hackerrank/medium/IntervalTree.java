package hackerrank.medium;

import hackerrank.Interval;

public class IntervalTree<T extends Comparable<T>> {

    public static <T extends Comparable<T>> IntervalNode<T> insert(IntervalNode<T> root, Interval<T> x) {
        if (root == null) {
            return new IntervalNode<>(x, x.high);
        }
        if (x.low.compareTo(root.interval.low) < 0) {
            root.left = insert(root.left, x);
        } else {
            root.right = insert(root.right, x);
        }
        if (root.max.compareTo(x.high) < 0) {
            root.max = x.high;
        }
        return root;
    }

    public static <T extends Comparable<T>> Interval<T> isOverlapping(IntervalNode<T> root, Interval<T> x) {
        if (root == null) {
            // return a dummy interval range
            return new Interval(-1, -1);
        }
        // if x overlaps with root's interval
        if (root.interval.isOverLapping(x)) {
            return root.interval;
        } else if (root.left != null && root.left.max.compareTo(x.low) > 0) {
            // the overlapping node may be present in left
            // child
            return isOverlapping(root.left, x);
        } else {
            return isOverlapping(root.right, x);
        }
    }


    public static <T extends Comparable<T>> Interval<T> isContained(IntervalNode<T> root, Interval<T> x) {
        if (root == null) {
            // return a dummy interval range
            return new Interval(-1, -1);
        }
        // if x overlaps with root's interval
        if (root.interval.isContained(x)) {
            return root.interval;
        } else if (root.left != null && root.left.max.compareTo(x.low) > 0) {
            // the containing node may be present in left
            // child
            return isOverlapping(root.left, x);
        } else {
            return isOverlapping(root.right, x);
        }
    }


    public static class IntervalNode<T extends Comparable<T>> implements Comparable<IntervalNode<T>> {
        Interval<T> interval;
        T max;
        IntervalNode<T> left;
        IntervalNode<T> right;

        public IntervalNode(Interval<T> interval, T max) {
            this.interval = interval;
            this.max = max;
        }

        @Override
        public int compareTo(IntervalNode<T> o) {
            return this.interval.compareTo(o.interval);
        }
    }
}
