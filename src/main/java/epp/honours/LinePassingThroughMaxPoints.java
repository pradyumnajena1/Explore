package epp.honours;

import java.math.BigInteger;
import java.util.*;

public class LinePassingThroughMaxPoints {
  public static void main(String[] args){
    Line line1 = new Line(new Point(1, 1),new Point(2, 2));
    Line line2 = new Line(new Point(1, 1),new Point(3, 3));
    System.out.println(line1);
    System.out.println(line2);

    List<Point> points = new ArrayList<Point>( );
    points.add(new Point(1, 1));
    points.add(new Point(1, 2));
    points.add(new Point(2, 1));
    points.add(new Point(2, 2));
    points.add(new Point(3, 3));
    Line maxLine = findLineWithMostPoints(points);
    System.out.println(maxLine);
  }

  private static Line findLineWithMostPoints(List<Point> points) {

    if (points.size() < 2) {
      throw new IllegalArgumentException("At least two points are needed to find a line.");
    }
    Map<Line, Set<Point>> map = new HashMap<>(points.size());
    for(int i=0;i<points.size();i++) {
      for(int j=i+1;j<points.size();j++) {
        Point a = points.get(i);
        Point b = points.get(j);

        Line line = new Line(a, b);
        Set<Point> pointSet = map.getOrDefault(line, new HashSet<>());
        pointSet.add(a);
        pointSet.add(b);
        map.put(line, pointSet);
      }
    }
    System.out.println(map);
    Map.Entry<Line, Set<Point>> max = Collections.max(map.entrySet(),
            Comparator.comparingInt(o -> o.getValue().size()));
    System.out.println(max.getValue());
    return max.getKey();
  }


  private static class Line {
    private Rational slope;
    private Rational yIntercept;

    public Line(Point a, Point b) {
      if (a.x == b.x) {
        slope = Rational.getCanonicalRational(1, 0);
        yIntercept = Rational.getCanonicalRational(a.x, 1);
      } else {
        slope = Rational.getCanonicalRational(b.y - a.y, b.x - a.x);
        yIntercept = Rational.getCanonicalRational(b.x * a.y - a.x * b.y, b.x - a.x);
      }
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Line line = (Line) o;

      if (!Objects.equals(slope, line.slope)) return false;
        return Objects.equals(yIntercept, line.yIntercept);
    }

    @Override
    public int hashCode() {
     return Objects.hash(slope , yIntercept );
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Line.class.getSimpleName() + "[", "]")
              .add("slope=" + slope)
              .add("yIntercept=" + yIntercept)
              .toString();
    }

    private static class Rational {
      int numerator;
      int denominator;

      private Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
      }

      public static Rational getCanonicalRational(int numerator, int denominator) {
        int gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue();
        numerator = numerator / gcd;
        denominator = denominator / gcd;
        if (denominator < 0) {
          return new Rational(-numerator, -denominator);
        }
        return new Rational(numerator, denominator);
      }

      @Override
      public String toString() {
        return new StringJoiner(", ", Rational.class.getSimpleName() + "[", "]")
                .add("numerator=" + numerator)
                .add("denominator=" + denominator)
                .toString();
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (numerator != rational.numerator) return false;
          return denominator == rational.denominator;
      }

      @Override
      public int hashCode() {
        return Objects.hash(numerator, denominator);
      }
    }
  }

  private static class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Point point = (Point) o;

      if (x != point.x) return false;
      return y == point.y;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      return result;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Point.class.getSimpleName() + "[", "]")
              .add("x=" + x)
              .add("y=" + y)
              .toString();
    }
  }
}
