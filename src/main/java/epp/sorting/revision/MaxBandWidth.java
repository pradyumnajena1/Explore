package epp.sorting.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxBandWidth {
  public static void main(String[] args) {
    Usage[] usages =
        new Usage[] {
          new Usage(1, 5, 5),
          new Usage(3, 9, 9),
          new Usage(1, 5, 7),
          new Usage(4, 15, 15),
          new Usage(3, 5, 11),
          new Usage(2, 8, 7),
          new Usage(11, 15, 13),
          new Usage(10, 22, 6),
        };
    int bandwidth = findMaxBandWidth(usages);
    System.out.println(bandwidth);
  }

  private static int findMaxBandWidth(Usage[] usages) {
    List<EndPoint> endPoints = new ArrayList<EndPoint>();
    for (Usage usage : usages) {
      endPoints.add(new EndPoint(usage.start, false, usage.bandwidth));
      endPoints.add(new EndPoint(usage.end, true, usage.bandwidth));
    }
    Collections.sort(endPoints);
    int currentBandWidth = 0;
    int maxBandWidth = 0;
    for (EndPoint endPoint : endPoints) {
      if (!endPoint.isEnd) {
        currentBandWidth += endPoint.bandwidth;
        maxBandWidth = Math.max(currentBandWidth, maxBandWidth);
      } else {
        currentBandWidth -= endPoint.bandwidth;
      }
    }
    return maxBandWidth;
  }

  private static class EndPoint implements Comparable<EndPoint> {
    int time;
    boolean isEnd;
    int bandwidth;

    public EndPoint(int time, boolean isEnd, int bandwidth) {
      this.time = time;
      this.isEnd = isEnd;
      this.bandwidth = bandwidth;
    }

    @Override
    public int compareTo(EndPoint o) {
      int compare = Integer.compare(this.time, o.time);
      if (compare != 0) {
        return compare;
      }
      return Boolean.compare(this.isEnd, o.isEnd);
    }
  }

  private static class Usage {
    int start;
    int end;
    int bandwidth;

    public Usage(int start, int end, int bandwidth) {
      this.start = start;
      this.end = end;
      this.bandwidth = bandwidth;
    }
  }
}
