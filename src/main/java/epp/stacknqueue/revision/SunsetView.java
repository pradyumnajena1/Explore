package epp.stacknqueue.revision;



import java.util.*;

public class SunsetView {
  public static void main(String[] args) {
    List<Building> buildingHeights =
        Arrays.asList(
            new Building(1, 2),
            new Building(1, 11),
            new Building(1, 2),
            new Building(1, 5),
            new Building(1, 3),
            new Building(1, 6),
            new Building(1, 7),
            new Building(1, 9),
            new Building(1, 4),
            new Building(1, 4),
            new Building(1, 1));
    List<Building> buildingWithSunsetView = getBuildingsWithSunsetView(buildingHeights.iterator());
    buildingWithSunsetView.stream().map(x->x.height).forEach(System.out::println);

     Collections.reverse(buildingHeights);
    buildingWithSunsetView = getBuildingsWithSunsetViewWestToEast(buildingHeights.iterator());
    buildingWithSunsetView.stream().map(x->x.height).forEach(System.out::println);
  }

  private static List<Building> getBuildingsWithSunsetViewWestToEast(Iterator<Building> buildings) {
    List<Building> result = new ArrayList<>();
    while (buildings.hasNext()){
      Building next = buildings.next();
      if(result.isEmpty() || result.get(result.size()-1).height<next.height){
        result.add(next);
      }
    }
    return result;
  }

  public static List<Building> getBuildingsWithSunsetView(Iterator<Building> buildings) {
    Deque<Building> stack = new LinkedList<>();
    while (buildings.hasNext()) {
      Building next = buildings.next();
      while (!stack.isEmpty() && stack.peek().height <= next.height) {
        stack.pop();
      }
      stack.push(next);
    }
    List<Building> result = new ArrayList<>();
    Iterator<Building> iterator = stack.descendingIterator();
    while (iterator.hasNext()) {
      result.add(iterator.next());
    }
    return result;
  }

  private static class Building {
    int id;
    int height;

    public Building(int id, int height) {
      this.id = id;
      this.height = height;
    }
  }
}
