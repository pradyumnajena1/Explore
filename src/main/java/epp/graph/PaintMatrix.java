package epp.graph;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class PaintMatrix {
    public static void main(String[] args) {
        int numVertices = 10;
        int[][] maze = ArrayUtils.createRandomMNMatrix(numVertices, numVertices,0,2);
        Location source  = new Location(3,4);
        source.setValue(maze);
        ArrayUtils.print2DArray(maze);
        flipColor(maze,source);
        System.out.println();
        ArrayUtils.print2DArray(maze);
    }

    private static void flipColor(int[][] maze, Location source) {
        Queue<Location> bfsQueue = new ArrayDeque<>();
        Set<Location> visitedSet = new HashSet<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        while (!bfsQueue.isEmpty()){
            Location current = bfsQueue.poll();
            List<Location> neighbours = getNeighbours(maze, current);
            neighbours.stream().filter(location -> !visitedSet.contains(location)).forEach(location -> {
                visitedSet.add(location);
                bfsQueue.offer(location);
            });
            current.setValue(maze,0);
        }
    }

    private static List<Location> getNeighbours(int[][] maze, Location current) {
        List<Location> neighboursUpDownLeftRight = current.getNeighboursUpDownLeftRight(maze);
        return neighboursUpDownLeftRight.stream().filter(location -> location.getValue(maze)==1).collect(Collectors.toList());
    }
}
