package epp.graph.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlipColors {
    public static void main(String[] args) {
        int[][] maze = ArrayUtils.createRandomMNMatrix(5,5,0,2);
        ArrayUtils.print2DArray(maze);
        Pair<Integer, Integer> source = new Pair<>(2, 3);
        flipColor(maze,source);
        ArrayUtils.print2DArray(maze);
    }

    private static void flipColor(int[][] maze, Pair<Integer, Integer> source) {
        Queue<Pair<Integer,Integer>> bfsQueue = new ArrayDeque<>();

        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        while (!bfsQueue.isEmpty()){

            Pair<Integer, Integer> polled = bfsQueue.poll();
          List<Pair<Integer,Integer>> neighbours =   getNeighbours(maze,polled);
            for (int i = 0; i < neighbours.size(); i++) {
                Pair<Integer, Integer> neighbour = neighbours.get(i);
                if(!visitedSet.contains(neighbour)){
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                }
            }
        }
        for(Pair<Integer,Integer> node:visitedSet){
            maze[node.getFirst()][node.getSecond()] = (maze[node.getFirst()][node.getSecond()]+1)%2;
        }
    }

    private static List<Pair<Integer, Integer>> getNeighbours(int[][] maze, Pair<Integer, Integer> node) {
        List<Pair<Integer, Integer>> neighbours = ArrayUtils.getNeighbours(maze.length - 1, maze[0].length - 1, node, false);
        Predicate<Pair<Integer, Integer>> pairPredicate = x -> maze[node.getFirst()][node.getSecond()] == maze[x.getFirst()][x.getSecond()];
        neighbours =
                neighbours.stream().filter(pairPredicate).collect(Collectors.toList());
        return neighbours;
    }
}
