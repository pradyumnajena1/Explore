package hackerrank.medium;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedCell {
    public static void main(String[] args) {

        ArrayList<List<Integer>> matrix = new ArrayList<>();
        matrix.add(List.of(0, 1, 0, 0, 0, 0, 1, 1, 0));
        matrix.add(List.of(1, 1, 0, 0, 1, 0, 0, 0, 1));
        matrix.add(List.of(0, 0, 0, 0, 1, 0, 1, 0, 0));
        matrix.add(List.of(0, 1, 1, 1, 0, 1, 0, 1, 1));
        matrix.add(List.of(0, 1, 1, 1, 0, 0, 1, 1, 0));
        matrix.add(List.of(0, 1, 0, 1, 1, 0, 1, 1, 0));
        matrix.add(List.of(0, 1, 0, 0, 1, 1, 0, 1, 1));
        matrix.add(List.of(1, 0, 1, 1, 1, 1, 0, 0, 0));
        System.out.println(connectedCell(matrix));
    }

    public static int connectedCell(List<List<Integer>> matrix) {
        // Write your code here
        int numRows = matrix.size();
        int numCols = matrix.get(0).size();
        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        int maxCells = 0;
        for(int i=0;i<numRows;i++){
            for(int j=0;j<numCols;j++){
                if(matrix.get(i).get(j)==1){
                    Pair<Integer,Integer> source = new Pair<>(i, j);
                    if(!visitedSet.contains(source)){

                        int numCells =   doBFS(matrix, source,visitedSet);
                        maxCells = Math.max(maxCells,numCells);
                    }
                }
            }
        }

      return maxCells;
    }

    private static int doBFS(List<List<Integer>> matrix, Pair<Integer, Integer> source, Set<Pair<Integer, Integer>> visitedSet) {
        int numRows = matrix.size();
        int numCols = matrix.get(0).size();
        Queue<Pair<Integer,Integer>> bfsQueue = new ArrayDeque<>();
        int numConnectedCells= 0;
        bfsQueue.offer(source);
        visitedSet.add(source);
        numConnectedCells++;
        while (!bfsQueue.isEmpty()){

            Pair<Integer, Integer> polled = bfsQueue.poll();
            List<Pair<Integer, Integer>> neighbours =  getNeighbours(numRows-1, numCols-1, polled,true);
            List<Pair<Integer, Integer>> validNeighbours =
                    neighbours.stream()
                            .filter(x -> !visitedSet.contains(x))
                            .filter(x -> matrix.get(x.getFirst()).get(x.getSecond()) == 1)
                            .collect(Collectors.toList());

            for(Pair<Integer,Integer> validNeigh:validNeighbours){
                bfsQueue.offer(validNeigh);
                visitedSet.add(validNeigh);
                numConnectedCells++;
            }
        }

        return numConnectedCells;
    }
    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, Pair<Integer, Integer> source) {

        return getNeighbours(maxRow,maxCol, source , false);
    }
    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, Pair<Integer, Integer> source,boolean includeCorners) {

        return getNeighbours(maxRow,maxCol, source.getFirst(), source.getSecond(), includeCorners);
    }

    public static List<Pair<Integer, Integer>> getNeighbours(int maxRow,int maxCol, int row, int col,
                                                             boolean includeCorners) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (row + i >= 0 && row + i <= maxRow && col + j >= 0 && col + j <= maxCol) {
                    if (!(i == 0 && j == 0) && (includeCorners || Math.abs(i * j) != 1)) {

                        result.add(new Pair<>(row + i, col + j));
                    }
                }
            }
        }
        return result;
    }
}
