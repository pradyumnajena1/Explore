package epp.sorting.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeIntervals {
  public static void main(String[] args) {
    List<Interval> intervals =
        new ArrayList<>(
            List.of(
                new Interval(-4, -1),
                new Interval(0, 2),
                new Interval(3, 6),
                new Interval(7, 9),
                new Interval(11, 12),
                new Interval(14, 17)));
    List<Interval> merged = mergeIntervals(intervals, new Interval(1, 8));
    System.out.println(merged);
  }

  private static List<Interval> mergeIntervals(List<Interval> intervals, Interval newInterval) {
    Collections.sort(intervals);
    List<Interval> merged = new ArrayList<Interval>();
    int i = 0;
    while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
      merged.add(intervals.get(i));
      i++;
    }

    while (i < intervals.size() && newInterval.end >= intervals.get(i).start) {
      newInterval =
          new Interval(
              Math.min(newInterval.start, intervals.get(i).start),
              Math.max(newInterval.end, intervals.get(i).end));
      i++;
    }
    merged.add(newInterval);
    while (i < intervals.size()) {
      merged.add(intervals.get(i));
      i++;
    }
    return merged;
  }

  private static class Interval implements Comparable<Interval> {
    int start;
    int end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Interval o) {
      return Integer.compare(start, o.start);
    }
    @Override
    public String toString() {
      return "Interval{" + "start=" + start + ", end=" + end + '}';
    }
  }
}
