package epp.graph;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class ComputeEnclosedRegions {
    public static void main(String[] args) {
        int numVertices = 10;
        int[][] maze = ArrayUtils.createRandomMNMatrix(numVertices, numVertices,0,2);
        ArrayUtils.print2DArray(maze);

      List<Location> enclosedRegions =  computeEnclosedRegions(maze);
        System.out.println(enclosedRegions);
    }

    private static List<Location> computeEnclosedRegions(int[][] maze) {
        Queue<Location> bfsQueue = new ArrayDeque<>();
        Set<Location> visitedSet = new HashSet<>();

        List<Location> borders =  getBorderLocations(maze);
        borders.stream().forEach(location -> {
            bfsQueue.offer(location);
            visitedSet.add(location);
        });
        while (!bfsQueue.isEmpty()){

            Location current = bfsQueue.poll();
            List<Location> neighbours =getNeighbours(current,maze);
            neighbours.stream().filter(location -> !visitedSet.contains(location)).forEach(location -> {
                visitedSet.add(location);
                bfsQueue.offer(location);
            });

        }
        List<Location> result = new ArrayList<>();
        for(int i=0;i< maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                Location location = new Location(i,j);
                if(location.getValue(maze)==1 && !visitedSet.contains(location)){
                    //location.resetValue(maze);
                    result.add(location);
                }
            }
        }

        return result;
    }

    private static List<Location> getNeighbours(Location current, int[][] maze) {
        return current.getNeighboursUpDownLeftRight(maze).stream().filter(location -> location.getValue(maze)==1).collect(Collectors.toList());
    }

    private static List<Location> getBorderLocations(int[][] maze) {
        List<Location> result = new ArrayList<>();
        for(int col = 0;col<maze[0].length;col++){
            result.add(new Location(0,col));
            result.add(new Location(maze.length-1,col));
        }
        for(int row = 0;row<maze.length;row++){
            result.add(new Location(row,0));
            result.add(new Location(row,maze.length-1));
        }
        return result.stream().filter(location -> location.getValue(maze)==1).collect(Collectors.toList());
    }
}
