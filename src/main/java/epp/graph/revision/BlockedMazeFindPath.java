package epp.graph.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class BlockedMazeFindPath {
    public static void main(String[] args) {
        int[][] maze = ArrayUtils.createRandomMNMatrix(5,5,0,2);
        ArrayUtils.print2DArray(maze);
        Pair<Integer, Integer> source = new Pair<>(0, 0);
        Pair<Integer, Integer> destination = new Pair<>(maze.length-1, maze[0].length-1);
        List<Pair<Integer,Integer>> canReach = findPath(maze, source, destination);
        System.out.println(canReach);
    }

    private static List<Pair<Integer,Integer>> findPath(int[][] maze, Pair<Integer, Integer> source, Pair<Integer, Integer> destination) {
        Queue<Pair<Integer,Integer>> bfsQueue = new ArrayDeque<>();
        Map<Pair<Integer,Integer>,Pair<Integer,Integer>> parentMap = new HashMap<>();
        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        bfsQueue.offer(source);
        visitedSet.add(source);
        boolean success = false;
        while (!bfsQueue.isEmpty()){
            Pair<Integer, Integer> polled = bfsQueue.poll();
            if(destination.equals(polled)){
                success=true;
                break;
            }
          List<Pair<Integer,Integer>> children =   getNeighbours(maze,polled);
            for (int i = 0; i < children.size(); i++) {
                Pair<Integer, Integer> child = children.get(i);
                if(!visitedSet.contains(child)){
                    bfsQueue.offer(child);
                    visitedSet.add(child);
                    parentMap.put(child,polled);
                }

            }
        }
        if(success){
            List<Pair<Integer,Integer>> path = new ArrayList<>();
            Pair<Integer,Integer> current = destination;
            do{
                path.add(current);
                current = parentMap.get(current);
            }while (current!=source);
            return path;
        }


        return null;
    }

    private static List<Pair<Integer, Integer>> getNeighbours(int[][] maze, Pair<Integer, Integer> node) {
        List<Pair<Integer, Integer>> neighbours = ArrayUtils.getNeighbours(maze.length - 1, maze[0].length - 1, node, true);
        List<Pair<Integer, Integer>> validNeighbours = neighbours.stream().filter(x -> maze[x.getFirst()][x.getSecond()] == 0).collect(Collectors.toList());
        return validNeighbours;
    }
}
