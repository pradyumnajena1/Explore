package epp.graph;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SearchMaze {
    public static void main(String[] args) {
        int numVertices = 10;
        int[][] maze = ArrayUtils.createRandomMNMatrix(numVertices, numVertices,0,2);
        Location source  = new Location(0,0);
        Location destination  = new Location(numVertices-1,numVertices-1);
        source.setValue(maze);
        destination.setValue(maze);
        ArrayUtils.print2DArray(maze);
        List<Location> path =  findPath(maze,source,destination);
        System.out.println(path);
    }

    private static List<Location> findPath(int[][] maze, Location source, Location destination) {
        Queue<Location> bfsQueue = new ArrayDeque<>();
        Set<Location> visitedSet = new HashSet<>();
        Map<Location,Location> parent = new HashMap<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        boolean found = false;
        while (bfsQueue.size()>0){
            Location current = bfsQueue.poll();
            if(current.equals(destination)){
                found = true;
                break;
            }
            List<Location> neighbours  = getNeighbours(maze, current);
            neighbours.stream().filter(location -> !visitedSet.contains(location)) .forEach(location -> {
                bfsQueue.offer(location);
                visitedSet.add(location);
                parent.put(location,current);
            });
        }
        System.out.println(found);
        List<Location> path = null;
        if(found){
          path=  buildPath(parent,destination);
        }

        return path;
    }

    private static List<Location> buildPath(Map<Location, Location> parent, Location destination) {
        List<Location> path = new ArrayList<>();
        Location current  = destination;
        while (current!=null){
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private static List<Location> getNeighbours(int[][] maze, Location current) {
        return current.getNeighbours(maze).stream().filter(location -> location.getValue(maze)==1).collect(Collectors.toList());
    }


}
