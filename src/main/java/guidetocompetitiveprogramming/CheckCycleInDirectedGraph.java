package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

public class CheckCycleInDirectedGraph {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(2)));
        System.out.println(checkCycleDirected(adjList, 1));

    }

    private static boolean checkCycleDirected(List<List<Integer>> adjList, int source) {
        boolean[] visitedSet = new boolean[adjList.size()];
        boolean[] pathStack = new boolean[adjList.size()];
        return checkCycleDirected(adjList, source, pathStack, visitedSet);
    }



    private static boolean checkCycleDirected(List<List<Integer>> adjList, int source, boolean[] pathStack, boolean[] visitedSet) {

        visitedSet[source] = true;
        pathStack[source] = true;
        for (Integer n : adjList.get(source)) {
            if(!visitedSet[n]){
                if (checkCycleDirected(adjList, n, pathStack, visitedSet)) {
                    return true;
                }
            }else if(pathStack[n]){
                return true;
            }

        }
        pathStack[source] = false;
        return false;
    }


}
