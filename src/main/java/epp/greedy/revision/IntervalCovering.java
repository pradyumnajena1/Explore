package epp.greedy.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IntervalCovering {
  public static void main(String[] args) {
    List<Interval> tasks = new ArrayList<Interval>();
    tasks.add(new Interval(1, 2));
    tasks.add(new Interval(2, 3));
    tasks.add(new Interval(2, 3));
    tasks.add(new Interval(3, 4));
    tasks.add(new Interval(3, 4));
    tasks.add(new Interval(4, 5));
    List<Integer> result = findMinimumVisits(tasks);
    System.out.println(result);
  }

  private static List<Integer> findMinimumVisits(List<Interval> tasks) {
    if(tasks.size()==0){
      return Collections.emptyList();  // Return an empty list if there are no tasks.
    }
    Collections.sort(tasks, Comparator.comparingInt(Interval::getEnd));
    List<Integer> result = new ArrayList<>();
    int lastVisitTime = tasks.get(0).end;
    result.add(lastVisitTime);
    for (Interval interval : tasks) {
      if (interval.start > lastVisitTime) {
        lastVisitTime = interval.end;
        result.add(lastVisitTime); // Add the visit time to the result list.
      }
    }
    return result;
  }

  private static class Interval {
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
  }
}
