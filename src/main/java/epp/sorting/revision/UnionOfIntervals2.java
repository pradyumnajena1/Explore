package epp.sorting.revision;

import epp.Interval;

import java.util.*;

public class UnionOfIntervals2 {

  public static void main(String[] args) {
    List<Interval> intervals =
        new ArrayList<Interval>(
            List.of(
                new Interval(new EndPoint(1, true), new EndPoint(5, false)),
                new Interval(new EndPoint(2, false), new EndPoint(4, true)),
                new Interval(new EndPoint(6, true), new EndPoint(9, true)),
                new Interval(new EndPoint(7, false), new EndPoint(12, false))));
    System.out.println(intervals);
    List<Interval> union = unionOfIntervals(intervals);
    System.out.println(union);
  }

  private static List<Interval> unionOfIntervals(List<Interval> intervals) {
    if (intervals == null || intervals.size() == 0) {
      return Collections.emptyList();
    }
    Collections.sort(intervals);
    List<Interval> result = new ArrayList<>();
    Interval last = intervals.get(0);
    result.add(last);
    for (int i = 1; i < intervals.size(); i++) {
      Interval current = intervals.get(i);
      if (current.start.value < last.end.value
          || (current.start.value == last.end.value
              && (!current.start.isOpen || !last.end.isOpen))) {

        if (current.end.value > last.end.value
            || (current.end.value == last.end.value && !current.end.isOpen)) {
          last.end.value = current.end.value;
          last.end.isOpen = current.end.isOpen;
        }
      } else {
        result.add(current);
        last = current;
      }
    }

    return result;
  }

  public static class Interval implements Comparable<Interval> {
    private EndPoint start;
    private EndPoint end;

    public Interval(EndPoint start, EndPoint end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Interval o) {
      int compare = Integer.compare(this.start.getValue(), o.start.getValue());
      if (compare != 0) {
        return compare;
      }
      return Boolean.compare(this.start.isOpen, o.start.isOpen);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Interval interval = (Interval) o;

      return this.start.value == interval.start.value && this.start.isOpen == interval.start.isOpen;
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.start.value, this.start.isOpen);
    }

    @Override
    public String toString() {
      return new StringJoiner(
              ", ",
              Interval.class.getSimpleName() + (start.isOpen ? "(" : "["),
              (end.isOpen ? ")" : "]"))
          .add("start=" + start.value)
          .add("end=" + end.value)
          .toString();
    }
  }

  private static class EndPoint {
    private int value;
    private boolean isOpen;

    public EndPoint(int value, boolean isOpen) {
      this.value = value;
      this.isOpen = isOpen;
    }

    public int getValue() {
      return value;
    }

    public boolean isOpen() {
      return isOpen;
    }
  }
}
