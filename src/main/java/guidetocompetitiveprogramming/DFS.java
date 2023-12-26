package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static void main(String[] args) {

        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>(List.of(1, 3)));
        adjList.add(new ArrayList<>(List.of(0,2,4)));
        adjList.add(new ArrayList<>(List.of(1,5)));
        adjList.add(new ArrayList<>(List.of(0)));
        adjList.add(new ArrayList<>(List.of(1,5)));
        adjList.add(new ArrayList<>(List.of(2,4)));

        System.out.println(adjList);
        depthFirstSearch(adjList, 0);

    }

    private static void depthFirstSearch(List<List<Integer>> adjList, int source) {
        boolean[] visited = new boolean[adjList.size()];
        depthFirstSearch(adjList, source, visited);
    }

    private static void depthFirstSearch(List<List<Integer>> adjList, int source, boolean[] visited) {

        if (!visited[source]) {
            visited[source] = true;
            System.out.println(source);
            for (int neighbour : adjList.get(source)) {
                depthFirstSearch(adjList, neighbour, visited);
            }

        }


    }
}
