package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClosestPairOfPoints {
    public static void main(String[] args) {
        List<Pair<Integer, Integer>> points = new ArrayList<>();
        IntStream.range(0, 1000000).forEach(i -> points.add(new Pair<>((int) (Math.random() * 100), (int) (Math.random() * 100))));
       // points.forEach(System.out::println);
        int closestDist = getMinimumDistance(points);
        System.out.println(closestDist);
    }

    private static int getMinimumDistance(List<Pair<Integer, Integer>> points) {
        points.sort(Comparator.comparingInt(Pair::getFirst));
        if (points.size() < 2) {
            return Integer.MAX_VALUE;
        }
        int min = getDistance(points.get(0), points.get(1));
        TreeSet<Pair<Integer,Integer>> treeSet = new TreeSet<>(Comparator.comparingInt((Pair<Integer,Integer> x) ->x.getFirst()).thenComparingInt(Pair::getSecond));
        treeSet.add(points.get(0));
        treeSet.add(points.get(1));
        for (int i = 2; i < points.size(); i++) {
            Pair<Integer, Integer> currentPoint = points.get(i);
            final int d = min;
            SortedSet<Pair<Integer, Integer>> sortedSet = treeSet.subSet(new Pair<>(currentPoint.getFirst() - d, currentPoint.getSecond() - d), currentPoint);
            List<Pair<Integer, Integer>> candidates = sortedSet.stream()
                    .filter(point -> point.getSecond() < currentPoint.getSecond() + d)
                    .collect(Collectors.toList());
            OptionalInt minCandidate = candidates.stream().mapToInt(x -> getDistance(x, currentPoint)).min();
            if (minCandidate.isPresent()) {
                min = Math.min(min, minCandidate.getAsInt());
            }
            treeSet.add(currentPoint);
        }
        return min;
    }

    private static int getDistance(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return (int) Math.sqrt(Math.pow(a.getFirst() - b.getFirst(), 2) + Math.pow(a.getSecond() - b.getSecond(), 2));
    }
}
