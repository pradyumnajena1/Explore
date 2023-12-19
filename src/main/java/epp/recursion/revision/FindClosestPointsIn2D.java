package epp.recursion.revision;

import epp.Triplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class FindClosestPointsIn2D {


    public static void main(String[] args) {
        List<Point> points = generateRandomPoints(50, 0, 100, 0, 100);
        Triplet<Point, Point, Integer> closestPair = findClosestPoints(points);
        System.out.println(closestPair);
    }

    private static Triplet<Point, Point, Integer> findClosestPoints(List<Point> points) {
        return findClosestPoints(points, 0, points.size() - 1);
    }

    private static Triplet<Point, Point, Integer> findClosestPoints(List<Point> points, int start, int end) {
        System.out.println(start + " " + end);
        if (end - start <= 2) {
            return findClosestPointsBruteforce(points, start, end);
        }
        int mid = (start + end) / 2;
        Triplet<Point, Point, Integer> leftResult = findClosestPoints(points, start, mid);
        Triplet<Point, Point, Integer> rightResult = findClosestPoints(points, mid + 1, end);
        Triplet<Point, Point, Integer> minResult = leftResult.getThird() < rightResult.getThird() ? rightResult : leftResult;


        Triplet<Point, Point, Integer> finalMinResult = minResult;
        List<Point> remaining =
                points.stream().filter((Point p) -> Math.abs(p.x - points.get(mid).x) <= finalMinResult.getThird()).collect(Collectors.toList());
        Triplet<Point, Point, Integer> closestInRemaining = findClosestPointsInRemaining(remaining);
        minResult = closestInRemaining.getThird() < minResult.getThird() ? closestInRemaining : minResult;


        return minResult;
    }

    private static Triplet<Point, Point, Integer> findClosestPointsInRemaining(List<Point> remaining) {
        remaining.sort(Comparator.comparingInt(Point::getY));
        Triplet<Point, Point, Integer> minPair = new Triplet<>(null, null, Integer.MAX_VALUE);

        for (int i = 0; i < remaining.size(); i++) {
            for (int j = i + 1; j < remaining.size() && remaining.get(j).y - remaining.get(i).y < minPair.getThird(); j++) {
                int distance = getDistance(remaining.get(i), remaining.get(j));
                minPair = distance < minPair.getThird() ? new Triplet<>(remaining.get(i), remaining.get(j), distance) : minPair;

            }
        }
        return minPair;
    }

    private static Triplet<Point, Point, Integer> findClosestPointsBruteforce(List<Point> points, int start, int end) {

        Triplet<Point, Point, Integer> res = new Triplet<>(null, null, Integer.MAX_VALUE);
        for (int i = start; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int distance = getDistance(points.get(i), points.get(j));
                if (distance < res.getThird()) {

                    res.setFirst(points.get(i));
                    res.setSecond(points.get(j));
                }
            }
        }
        return res;
    }

    private static int getDistance(Point a, Point b) {
        return (int) Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static List<Point> generateRandomPoints(int size, int minX, int maxX, int minY, int maxY) {
        List<Point> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Point point = new Point((int) (minX + (maxX - minX) * Math.random()), (int) (minY + (maxY - minY) * Math.random()));
            result.add(point);
        }
        return result;
    }


    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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
