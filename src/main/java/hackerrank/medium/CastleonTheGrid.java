package hackerrank.medium;

import epp.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class CastleonTheGrid {
    public static void main(String[] args) {
        System.out.println(minimumMoves(new ArrayList<>(List.of("...", ".X.", "...")), 0, 0, 1, 2));
        System.out.println(minimumMoves(new ArrayList<>(List.of("...", ".X.", ".X.")), 2, 0, 2, 2));
    }

    public static int minimumMoves(List<String> grid, int startX, int startY, int goalX, int goalY) {
        // Write your code here
        int maxRow = grid.size()-1;
        int maxCol = grid.get(0).length()-1;

        Queue<Pair<Integer, Integer>> bfsQueue = new ArrayDeque<>();
        Pair<Integer, Integer> start = new Pair<>(startX, startY);
        Pair<Integer, Integer> goal = new Pair<>(goalX, goalY);
        Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
        Map<Pair<Integer, Integer>, Pair<Integer, Integer>> parentmap = new HashMap<>();
        bfsQueue.offer(start);
        visitedSet.add(start);
        parentmap.put(start, null);

        boolean success = false;
        while (!bfsQueue.isEmpty()) {

            Pair<Integer, Integer> polled = bfsQueue.poll();
            if (polled.equals(goal)) {
                success = true;
                break;
            }

            List<Pair<Integer, Integer>> neighbours = getNeighbours(maxRow, maxCol, polled,grid);

            List<Pair<Integer, Integer>> validNeighbours = neighbours.stream().filter(cell -> grid.get(cell.getFirst()).charAt(cell.getSecond()) == '.').collect(Collectors.toList());
            for (Pair<Integer, Integer> neighbour : validNeighbours) {
                if (!visitedSet.contains(neighbour)) {
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                    parentmap.put(neighbour, polled);
                }
            }
        }
        if (success) {
            int numSteps = 0;
            Pair<Integer, Integer> current = goal;
            while (current != start) {
               //  System.out.println(current);
                current = parentmap.get(current);
                numSteps++;
            }
            return numSteps;
        }

        return -1;

    }



    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow, int maxCol, Pair<Integer, Integer> source ,List<String> grid) {

        return getNeighbours(maxRow, maxCol, source.getFirst(), source.getSecond(),grid);
    }

    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow, int maxCol, int row, int col
                                                              ,List<String> grid) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        for(int i = col+1;i<=maxCol && grid.get(row).charAt(i)=='.';i++){
            result.add(new Pair<>(row,i));
        }
        for(int i = col-1;i>=0 && grid.get(row).charAt(i)=='.';i--){
            result.add(new Pair<>(row,i));
        }
        for(int i = row+1;i<=maxRow && grid.get(i).charAt(col)=='.' ;i++){
            result.add(new Pair<>(i,col));
        }
        for(int i = row-1;i>=0  && grid.get(i).charAt(col)=='.' ;i--){
            result.add(new Pair<>(i,col));
        }
        return result;
    }

}
