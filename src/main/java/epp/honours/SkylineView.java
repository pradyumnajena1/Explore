package epp.honours;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SkylineView {

  public static void main(String[] args) {
    List<Building> buildings = new ArrayList<Building>();
    buildings.add(new Building(0, 3, 1));
    buildings.add(new Building(1, 6, 3));
    buildings.add(new Building(4, 8, 4));
    buildings.add(new Building(5, 9, 2));
    buildings.add(new Building(7, 14, 3));
    buildings.add(new Building(10, 12, 6));
    buildings.add(new Building(11, 17, 1));
    buildings.add(new Building(13, 16, 2));
    List<Building> skyline = getSkyline(buildings);
    System.out.println(skyline);

    skyline = getSkyline(buildings);
    System.out.println(skyline);
  }

  public static List<Building> getSkyline(List<Building> buildings) {
    int minStart = Integer.MAX_VALUE;
    int maxRight = Integer.MIN_VALUE;
    for (Building building : buildings) {
      minStart = Math.min(minStart, building.start);
      maxRight = Math.max(maxRight, building.end);
    }
    List<Integer> heights = new ArrayList<>();
    for (int x = minStart; x <= maxRight; x++) {
      heights.add(0);
    }
    for (Building building : buildings) {
      for (int x = building.start; x <= building.end; x++) {
        int index = x - minStart;
        heights.set(index, Math.max(heights.get(index), building.height));
      }
    }
    List<Building> result = new ArrayList<>();
    int left = 0;
    for (int i = 1; i < heights.size(); i++) {
      if (heights.get(i) != heights.get(i - 1)) {
        Building building = new Building(left + minStart, i - 1 + minStart, heights.get(i - 1));
        result.add(building);
        left = i;
      }
    }
    result.add(new Building(left + minStart, maxRight, heights.get(heights.size() - 1)));
    return result;
  }

  public static List<Building> getSkyline2(List<Building> buildings) {
    int minStart = Integer.MAX_VALUE;
    int maxEnd = Integer.MIN_VALUE;
    List<EndPoint> endPoints = new ArrayList<>();
    for (Building building : buildings) {
      minStart = Math.min(minStart, building.start);
      maxEnd = Math.max(maxEnd, building.end);
      endPoints.add(new EndPoint(building.start, false, building));
      endPoints.add(new EndPoint(building.end, true, building));
    }
    endPoints.sort(Comparator.naturalOrder());
    TreeSet<Building> tree = new TreeSet<>();
    List<Integer> maxHeights = new ArrayList<>();
    List<Integer> indices = new ArrayList<>();
    for (EndPoint p : endPoints) {
      if (p.isEnd) {
        int maxHeight = tree.last().height;
        tree.remove(p.building);
        indices.add(p.x);
        maxHeights.add(maxHeight);
      } else {
        tree.add(p.building);
        int maxHeight = tree.last().height;
        indices.add(p.x);
        maxHeights.add(maxHeight);
      }
    }
    List<Building> result = new ArrayList<>();
    int left = minStart;
    for (int i = 1; i < indices.size(); i++) {
      if (maxHeights.get(i) != maxHeights.get(i - 1)) {
        result.add(new Building(left, indices.get(i), maxHeights.get(i - 1)));
        left = i;
      }
    }
    return result;
  }

  private static class Building implements Comparable<Building> {
    int start;
    int end;
    int height;

    public Building(int start, int end, int height) {
      this.start = start;
      this.end = end;
      this.height = height;
    }

    @Override
    public int compareTo(Building o) {
      int compare = Integer.compare(this.height, o.height);
      if (compare != 0) {
        return compare;
      }
      compare = Integer.compare(this.end, o.end);
      if (compare != 0) {
        return compare;
      }
      return Integer.compare(this.start, o.start);
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Building.class.getSimpleName() + "[", "]")
          .add("start=" + start)
          .add("end=" + end)
          .add("height=" + height)
          .toString();
    }
  }

  private static class EndPoint implements Comparable<EndPoint> {
    int x;
    boolean isEnd;
    Building building;

    public EndPoint(int x, boolean isEnd, Building building) {
      this.x = x;
      this.isEnd = isEnd;
      this.building = building;
    }

    @Override
    public int compareTo(EndPoint o) {
      int compare = Integer.compare(x, o.x);
      return compare != 0 ? compare : Boolean.compare(isEnd, o.isEnd);
    }
  }
}
