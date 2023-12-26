package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

public class CheckCycleInUnDirectedGraph {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(1)));
        System.out.println(checkCycleUnDirected(adjList, 1));


    }



    private static boolean checkCycleUnDirected(List<List<Integer>> adjList, int source) {
        boolean[] visitedSet = new boolean[adjList.size()];

        return checkCycleUnDirected(adjList, source, -1, visitedSet);
    }



    private static boolean checkCycleUnDirected(List<List<Integer>> adjList, int source, int parent, boolean[] visitedSet) {
        visitedSet[source] = true;
        for (Integer n : adjList.get(source)) {
            if (!visitedSet[n]) {
                if (checkCycleUnDirected(adjList, n, source, visitedSet)) {
                    return true;
                }
            } else if (n != parent) {
                return true;
            }
        }
        return false;

    }
}
