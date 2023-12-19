package hackerrank.medium;

import epp.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class RedKnightShortestPath {

    public static void main(String[] args) {
        printShortestPath(7, 6, 6, 0, 1);
    }

    public static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.
        Queue<Pair<Integer, Integer>> bfsQueue = new ArrayDeque<>();
        Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parentMap = new HashMap<>();
        Pair<Integer, Integer> source = new Pair<>(i_start, j_start);
        Pair<Integer, Integer> destination = new Pair<>(i_end, j_end);
        bfsQueue.offer(source);
        visitedSet.add(source);
        parentMap.put(source, null);
        boolean success = false;
        while (!bfsQueue.isEmpty()) {
          //  System.out.println(bfsQueue);
            Pair<Integer, Integer> polled = bfsQueue.poll();
            if (polled.equals(destination)) {
                success = true;
                break;
            }
            List<Pair<Integer, Integer>> neighbours = getKnightMoves(n - 1, n - 1, polled);
            for (Pair<Integer, Integer> neighbour : neighbours) {

                if (!visitedSet.contains(neighbour)) {
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                    parentMap.put(neighbour, polled);
                }
            }
        }
        if (success) {
            List<Pair<Integer, Integer>> path = getPath(source, destination, parentMap);
          //  System.out.println(path);
            List<String> pathString = new ArrayList<>();
            for (int i = 0; i < path.size()-1; i++) {
                String move = getMoveString(path.get(i ), path.get(i+1));
                pathString.add(move);
            }
            System.out.println(path.size()-1);
            System.out.println(String.join(" ", pathString));

        } else {
            System.out.println("Impossible");
        }

    }

    private static String getMoveString(Pair<Integer, Integer> current, Pair<Integer, Integer> next) {
        if (current.getFirst() == next.getFirst()) {
            return current.getSecond() > next.getSecond() ? "L" : "R";

        } else if (current.getFirst() < next.getFirst()) {
            //lower
            return current.getSecond() > next.getSecond() ? "LL" : "LR";

        } else {
            //upper
            return current.getSecond() > next.getSecond() ? "UL" : "UR";

        }

    }

    private static List<Pair<Integer, Integer>> getPath(Pair<Integer, Integer> source, Pair<Integer, Integer> destination, Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parentMap) {
        List<Pair<Integer, Integer>> path = new ArrayList<>();
        Pair<Integer, Integer> current = destination;
        while (current != source) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(source);
         reverse(path);
        return path;
    }

    public static <T> void reverse(List<T> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            T copy = list.get(start);
            list.set(start, list.get(end));
            list.set(end, copy);
            start++;
            end--;
        }

    }


    private static List<Pair<Integer, Integer>> getKnightMoves(int maxRow, int maxCol,
                                                               Pair<Integer, Integer> currentPos) {
        return getKnightMoves(maxRow, maxCol, currentPos, 1, 2);
    }

    private static List<Pair<Integer, Integer>> getKnightMoves(int maxRow, int maxCol,
                                                               Pair<Integer, Integer> currentPos, int a, int b) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        Integer currentX = currentPos.getFirst();
        Integer currentY = currentPos.getSecond();

        result.add(new Pair<>(currentX - b, currentY - a));
        result.add(new Pair<>(currentX - b, currentY + a));
        result.add(new Pair<>(currentX, currentY + b));
        result.add(new Pair<>(currentX + b, currentY + a));
        result.add(new Pair<>(currentX + b, currentY - a));
        result.add(new Pair<>(currentX, currentY - b));

          result = result.stream().filter(x -> x.getFirst() >= 0 && x.getFirst() <= maxRow)
                .filter(x -> x.getSecond() >= 0 && x.getSecond() <= maxCol).collect(Collectors.toList());
        return result;
    }
}
