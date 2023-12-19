package epp.graph.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RevertAllZerosToOneThatCantReachBoundary {
    public static void main(String[] args) {
        int[][] maze = ArrayUtils.createRandomMNMatrix(5, 5, 0, 2);
        maze = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 0, 0}};
        ArrayUtils.print2DArray(maze);
        changeAllZerosToOneThatCantReachBoundary(maze);
        ArrayUtils.print2DArray(maze);

    }

    private static void changeAllZerosToOneThatCantReachBoundary(int[][] maze) {
        Set<Pair<Integer, Integer>> visitedSet = new HashSet<>();
        List<Pair<Integer, Integer>> borderCells = new ArrayList<>();
        borderCells.addAll(ArrayUtils.getRowCells(maze, List.of(0, maze.length - 1)));
        borderCells.addAll(ArrayUtils.getColCells(maze, List.of(0, maze[0].length - 1)));
        borderCells =
                borderCells.stream().filter(x -> maze[x.getFirst()][x.getSecond()] == 0).collect(Collectors.toList());

        for (Pair<Integer, Integer> borderCell : borderCells) {
            if (!visitedSet.contains(borderCell)) {
                doBFS(maze, borderCell, visitedSet);
            }
        }
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 0 && !visitedSet.contains(new Pair<>(i, j))) {
                    maze[i][j] = 1;
                }
            }
        }


    }

    private static void doBFS(int[][] maze, Pair<Integer, Integer> node,
                              Set<Pair<Integer, Integer>> visitedSet) {
        Queue<Pair<Integer, Integer>> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(node);
        visitedSet.add(node);
        while (!bfsQueue.isEmpty()) {
            Pair<Integer, Integer> polled = bfsQueue.poll();
            List<Pair<Integer, Integer>> neighbours = getNeighbours(maze, polled);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                if (!visitedSet.contains(neighbour)) {
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                }
            }
        }
    }

    private static List<Pair<Integer, Integer>> getNeighbours(int[][] maze, Pair<Integer, Integer> node) {
        List<Pair<Integer, Integer>> neighbours = ArrayUtils.getNeighbours(maze.length - 1, maze[0].length - 1, node, false);
        neighbours = neighbours.stream().filter(x -> maze[x.getFirst()][x.getSecond()] == 0).collect(Collectors.toList());
        return neighbours;
    }
}
