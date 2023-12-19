package epp;

import java.util.Comparator;
import java.util.StringJoiner;

public class Interval implements Comparable<Interval>{

    public static final Comparator<Interval> startComparator = Comparator.comparing((Interval x) -> x.getStart()).thenComparing((Interval x) -> x.getEnd());
    public static final Comparator<Interval> endComparator = Comparator.comparing((Interval x) -> x.getEnd()).thenComparing((Interval x) -> x.getStart());
    private int start;
    private boolean startOpen;
    private int end;
    private boolean endOpen;

    public static Interval getInterval(int start, boolean startOpen, int end, boolean endOpen) {
       return new Interval(start, startOpen, end, endOpen);
    }

    public static Interval getClosedInterval(int start,  int end ) {
        return new Interval(start, false, end, false);
    }

    public static Interval getOpenInterval(int start,  int end ) {
        return new Interval(start, true, end, true);
    }


    public Interval(int start, boolean startOpen, int end, boolean endOpen) {
        this.start = Math.min(start, end);
        this.startOpen = startOpen;
        this.end = Math.max(start, end);
        this.endOpen = endOpen;
    }

    public static void main(String[] args) {
        Interval a = new Interval(5, false, 8, true);
        Interval b = new Interval(3, true, 5, false);
        System.out.println(a.isOverlapping(b));
    }

    public int getStart() {
        return start;
    }

    public boolean isStartOpen() {
        return startOpen;
    }

    public int getEnd() {
        return end;
    }

    public boolean isEndOpen() {
        return endOpen;
    }

    public boolean isOverlapping(Interval other) {
        boolean notOverlapping =
                           this.end < other.start
                        || this.end == other.start && endOpen && other.startOpen
                        || this.start > other.end
                        || this.start == other.end && startOpen && other.endOpen;

        return !(notOverlapping);
    }

    public void merge(Interval other) {
        if (this.isOverlapping(other)) {
            int newStart = Math.min(start, other.start);
            int newEnd = Math.max(end, other.end);

            boolean newStartOpen = start == other.start ? startOpen && other.startOpen : (newStart == start ? startOpen : other.startOpen);
            boolean newEndOpen = end == other.end ? endOpen && other.endOpen : (newEnd == end ? endOpen : other.endOpen);
            start = newStart;
            end = newEnd;
            startOpen = newStartOpen;
            endOpen = newEndOpen;

        }

    }

    public Interval clone() {
        return new Interval(start, startOpen, end, endOpen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interval interval = (Interval) o;

        if (start != interval.start) return false;
        if (startOpen != interval.startOpen) return false;
        if (end != interval.end) return false;
        return endOpen == interval.endOpen;
    }

    @Override
    public int hashCode() {
        int result = start;
        result = 31 * result + (startOpen ? 1 : 0);
        result = 31 * result + end;
        result = 31 * result + (endOpen ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",  "", "")
                .add(  ( startOpen?"(":"[") + start)
                .add(  end + ( endOpen?")":"]") )
                .toString();
    }

    @Override
    public int compareTo(Interval other) {
        if(this.isOverlapping(other)){
            return 0;
        }
        return this.end<=other.start?-1:1;
    }
}
