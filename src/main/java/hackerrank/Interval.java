package hackerrank;

import hackerrank.medium.IntervalTree;

public class Interval<T extends Comparable<T>> implements Comparable<Interval<T>> {
    public T low;
    public T high;

    public Interval(T low, T high) {
        this.low = low;
        this.high = high;
    }

    public String toString() {
        return "[" + this.low + "," + this.high + "]";
    }

    @Override
    public int compareTo(Interval<T> o) {
        int compare = this.low.compareTo(o.low);
        return compare!=0?compare:this.high.compareTo(o.high);
    }

    public boolean isContained(Interval<T> x) {
        return (x.low.compareTo(low) >= 0 && x.low.compareTo(high) <= 0) && (x.high.compareTo(low) >= 0 && x.high.compareTo(high) <= 0);
    }

    public boolean isOverLapping(Interval<T> x) {
        return (x.low.compareTo(low) >= 0 && x.low.compareTo(high) <= 0) || (x.high.compareTo(low) >= 0 && x.high.compareTo(high) <= 0);
    }
}
