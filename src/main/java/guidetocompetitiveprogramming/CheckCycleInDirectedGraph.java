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
        System.out.println(checkCycleDirected2(adjList, 1));

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
            if (!visitedSet[n]) {
                if (checkCycleDirected(adjList, n, pathStack, visitedSet)) {
                    return true;
                }
            } else if (pathStack[n]) {
                return true;
            }

        }
        pathStack[source] = false;
        return false;
    }

    private static boolean checkCycleDirected2(List<List<Integer>> adjList, int source) {
        byte[] color = new byte[adjList.size()];

        return checkCycleDirected2(adjList, source, color);
    }

    private static boolean checkCycleDirected2(List<List<Integer>> adjList, int source, byte[] color) {

        if (color[source] == 2) {
            return false;
        }
        if (color[source] == 1) {
            return true;
        }

        color[source] = 1;
        for (Integer n : adjList.get(source)) {
            if (checkCycleDirected2(adjList, n, color)) {
                return true;
            }
        }
        color[source] = 2;
        return false;
    }


}
