package epp.sorting.revision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * given a set of disjoint closed interval add one more closed interval and merge
 */
public class AddAClosedInterval {
    public static void main(String[] args) {
        List<Interval> intervals = List.of(
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 7),
                new Interval(9, 12)
        );
        intervals = new ArrayList<>(intervals);
        Interval newInterval = new Interval(1, 8);
        System.out.println(intervals);
        System.out.println(newInterval);
        List<Interval> merged = merge(intervals, newInterval);
        System.out.println(merged);
    }

    private static List<Interval> merge(List<Interval> intervals, Interval newInterval) {

        intervals.sort(Comparator.comparing(Interval::getStart));
        List<Interval> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i));
            i++;
        }
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
            newInterval = newInterval.merge(intervals.get(i));
            i++;
        }
        result.add(newInterval);
        while (i < intervals.size()) {
            result.add(intervals.get(i));
            i++;
        }


        return result;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Interval.class.getSimpleName() + "[", "]")
                    .add("start=" + start)
                    .add("end=" + end)
                    .toString();
        }

        public boolean isOverlapping(Interval other) {
            return !(this.end < other.start || this.start > other.end);
        }

        public Interval merge(Interval other) {
            Interval result = null;
            if (isOverlapping((other))) {
                result = new Interval(Math.min(this.start, other.start), Math.max(this.end, other.end));

            }
            return result;
        }
    }

}
