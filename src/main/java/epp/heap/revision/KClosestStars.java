package epp.heap.revision;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class KClosestStars {

  public static void main(String[] args) throws IOException {

    BufferedReader reader =
        Files.newBufferedReader(
            new File(
                    "C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src"
                        + "\\main"
                        + "\\java"
                        + "\\epp\\heap\\revision\\stars.txt")
                .toPath(),
            Charset.defaultCharset());
    List<Star> closestStars = findClosestStars(reader, 3 );
    System.out.println(closestStars);
    reader.close();
  }

  private static List<Star> findClosestStars(BufferedReader reader, int numStars )
      throws IOException {
    PriorityQueue<Star> priorityQueue = new PriorityQueue<>(Star.starComparator.reversed());
    List<Star> stars = parseStarts(reader);

    for (Star star : stars) {

      if (priorityQueue.size() < numStars) {
        priorityQueue.offer(star);
      } else {
        if (priorityQueue.peek().compareTo(star) > 0) {
          priorityQueue.poll();
          priorityQueue.offer(star);
        }
      }
    }
    List<Star> orderedStars = new ArrayList<>(priorityQueue);
    Collections.sort(orderedStars);
    return orderedStars;
  }

  private static List<Star> parseStarts(BufferedReader reader) throws IOException {
    List<Star> stars = new ArrayList<>();
    String line = null;
    while ((line = reader.readLine()) != null) {
      Star star = Star.parseStar(line);
      stars.add(star);
    }
    return stars;
  }

  private static class Star implements Comparable<Star> {
    private static Comparator<Star> starComparator =
        Comparator.comparingInt((Star x) -> x.getPoint().distance());

    String name;
    Point point;

    public Star(String name, Point point) {
      this.name = name;
      this.point = point;
    }

    public static Star parseStar(String line) {
      String[] split = line.split(",");
      String name1 = split[0].trim();
      int x = Integer.parseInt(split[1].trim());
      int y = Integer.parseInt(split[2].trim());
      int z = Integer.parseInt(split[3].trim());
      return new Star(name1, new Point(x, y, z));
    }

    @Override
    public int compareTo(Star o) {

      return starComparator.compare(this, o);
    }

    public String getName() {
      return name;
    }

    public Point getPoint() {
      return point;
    }

    @Override
    public String toString() {
      final StringBuffer sb = new StringBuffer("Star{");
      sb.append("name='").append(name).append('\'');
      sb.append(", point=").append(point);
      sb.append('}');
      return sb.toString();
    }
  }

  private static class Point {
    int x, y, z;

    public Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public int distance() {
      return distance(new Point(0, 0, 0));
    }

    private int distance(Point point) {
      return (int)
          Math.sqrt(Math.pow(point.x - x, 2) + Math.pow(point.y - y, 2) + Math.pow(point.z - z, 2));
    }

    @Override
    public String toString() {
      final StringBuffer sb = new StringBuffer("Point{");
      sb.append("x=").append(x);
      sb.append(", y=").append(y);
      sb.append(", z=").append(z);
      sb.append('}');
      return sb.toString();
    }
  }
}
