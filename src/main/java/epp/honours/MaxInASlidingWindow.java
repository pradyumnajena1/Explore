package epp.honours;

import epp.stacknqueue.revision.QueueWithMax;
import java.util.ArrayList;
import java.util.List;

public class MaxInASlidingWindow {

  public static void main(String[] args) {
    TrafficElement[] trafficVolumes = new TrafficElement[6] ;
    trafficVolumes[0] = new TrafficElement(10, 1000);
    trafficVolumes[1] = new TrafficElement(15, 1100);
    trafficVolumes[2] = new TrafficElement(20, 1200);
    trafficVolumes[3] = new TrafficElement(25, 1300);
    trafficVolumes[4] = new TrafficElement(30, 1400);
    trafficVolumes[5] = new TrafficElement(35, 1500);
    System.out.println(maxInASlidingWindow(trafficVolumes, 3)); // Output: 1400.0
  }

  private static List<TrafficElement> maxInASlidingWindow(
          TrafficElement[] trafficElements, int windowSize) {
    QueueWithMax<TrafficElement> queueWithMax = new QueueWithMax<TrafficElement>();
    List<TrafficElement> result = new ArrayList<>();
    for (TrafficElement trafficElement : trafficElements) {
      queueWithMax.enqueue(trafficElement);
      while (!queueWithMax.isEmpty()
          && trafficElement.timestamp - queueWithMax.peek().timestamp > windowSize) {
        queueWithMax.dequeue();
      }
      result.add(new TrafficElement(queueWithMax.max().volume, trafficElement.timestamp));
    }
    return result;
  }

  private static class TrafficElement implements Comparable<TrafficElement> {
    double volume;
    long timestamp;

    public TrafficElement(double volume, long timestamp) {
      this.volume = volume;
      this.timestamp = timestamp;
    }

    @Override
    public int compareTo(TrafficElement o) {
      int volumeCmp = Double.compare(volume, o.volume);
      return volumeCmp != 0 ? volumeCmp : (int) (timestamp - o.timestamp);
    }
    @Override
    public String toString() {
      return "TrafficElement{" + "volume=" + volume + ", timestamp=" + timestamp + '}';
    }
  }
}
