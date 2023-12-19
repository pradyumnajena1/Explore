package hackerrank.medium;

import epp.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class KnightOnAChessboard {


    public static void main(String[] args) {
        List<List<Integer>> lists = knightlOnAChessboard(5);
    }

    public static List<List<Integer>> knightlOnAChessboard(int n) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        for (int a = 1; a < n; a++) {
            List<Integer> minimumPaths = new ArrayList<>();

            for(int b=1;b<n;b++){
                int minPath = knightlOnAChessboard(n, a, b);
                minimumPaths.add(minPath);
            }
            System.out.println(minimumPaths);
            result.add(minimumPaths);

        }
        return result;
    }

    private static int knightlOnAChessboard(int n, int a, int b) {
        Queue<Pair<Integer,Integer>> bfsQueue = new ArrayDeque<>();
        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        Map<Pair<Integer,Integer>,Pair<Integer,Integer>> parentMap = new HashMap<>();

        Pair<Integer, Integer> source = new Pair<>(0,0);
        Pair<Integer, Integer> destination = new Pair<>(n-1,n-1);
        bfsQueue.offer(source);
        visitedSet.add(source);
        parentMap.put(source,null);

        while (!bfsQueue.isEmpty()){

            Pair<Integer, Integer> polled = bfsQueue.poll();


            if(polled.equals(destination)){
                return getNumSteps(parentMap,destination,source) ;
            }
            List<Pair<Integer, Integer>> neighbours =  getKnightMoves(n-1, n-1, polled,a,b);
            List<Pair<Integer, Integer>> validNeighbours =
                    neighbours.stream().filter(x -> !visitedSet.contains(x)) .collect(Collectors.toList());

            for(Pair<Integer,Integer> validNeigh:validNeighbours){
                bfsQueue.offer(validNeigh);
                visitedSet.add(validNeigh);
                parentMap.put(validNeigh,polled);
            }
        }

        return -1;
    }

    private static int getNumSteps(Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parentMap, Pair<Integer, Integer> current, Pair<Integer, Integer> source) {
      int numSteps=0;
       while (current!=source){
           numSteps++;
           current = parentMap.get(current);
       }
        return numSteps;
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

        for (int x = -a; x <= a; x += 2 * a) {
            for (int y = -b; y <= b; y += 2 * b) {

                if (currentX + x >= 0 && currentX + x <= maxRow) {
                    if (currentY + y >= 0 && currentY + y <= maxCol) {
                        result.add(new Pair<>(currentX + x, currentY + y));
                    }
                }
            }
        }
        for (int x = -b; x <= b; x += 2 * b) {
            for (int y = -a; y <= a; y += 2 * a) {

                if (currentX + x >= 0 && currentX + x <= maxRow) {
                    if (currentY + y >= 0 && currentY + y <= maxCol) {
                        result.add(new Pair<>(currentX + x, currentY + y));
                    }
                }
            }
        }
        return result;
    }
}
