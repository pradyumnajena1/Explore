package epp.honours;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Runners numbered from 0 to n - 1 race on a straight one-way road to a common finish line. The
 * runners have different (constant)speeds and start at different distances from the finish line.
 * Specifically, Runner i has a speed s, and begins at a distance d, from the finish line. Each
 * runner stops at the finish line, and the race ends when all runners have reached the finish line.
 * How many times does one runner pass another
 */
public class RunnerCrossing {

  public static void main(String[] args) {
    List<Integer> speeds = List.of(3, 7, 5, 4, 2, 1, 10);
    List<Integer> distances = List.of(12, 15, 25, 24, 14, 8, 30);
    int numCrossing = countNumCrossing(speeds, distances);
    System.out.println(numCrossing);
  }

  private static int countNumCrossing(List<Integer> speeds, List<Integer> distances) {
    List<Runner> runners = new ArrayList<>();
    for(int i = 0; i <speeds.size();i++) {
      runners.add(new Runner(i, speeds.get(i),distances.get(i)));
    }
    runners.sort(Comparator.naturalOrder());
    List<Integer> orderedDistances = runners.stream().map(x -> x.distance).collect(Collectors.toList());
    System.out.println(orderedDistances);
    int countInversions = CountInversions.countInversions(orderedDistances);
    return countInversions;
  }

  private static class Runner implements Comparable<Runner> {
    int index;
    int distance;
    int speed;
    int timeToFinish;

    public Runner(int index, int speed, int distance ) {
      this.index = index;
      this.distance = distance;
      this.speed = speed;
      this.timeToFinish = distance / speed;
    }

    @Override
    public int compareTo(Runner o) {
      return Integer.compare(this.timeToFinish, o.timeToFinish);
    }
  }
}
